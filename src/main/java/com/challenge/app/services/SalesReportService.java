package com.challenge.app.services;

import com.challenge.app.dto.Product;
import com.challenge.app.dto.SalesReport;
import com.challenge.app.models.Company;
import com.challenge.app.repositories.SalesRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.challenge.app.utils.AppConstants.SalesReportService.*;
import static com.challenge.app.utils.AppUtils.convertPrice;
import static com.challenge.app.utils.AppUtils.convertToEuroFormat;

@Service
public class SalesReportService {

    private final ValidationService validationService;
    private final ExchangeRateService exchangeRateService;
    private final SalesRepository salesRepository;

    public SalesReportService(ValidationService validationService, SalesRepository salesRepository, ExchangeRateService exchangeRateService) {
        this.validationService = validationService;
        this.salesRepository = salesRepository;
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * This methods fetches all sales related to a given company
     * @param companyName
     * @return
     */
    @Transactional(readOnly = true)
    public List<SalesReport> getSalesByCompany(String companyName) {
        Company company = validationService.validateCompany(companyName);
        List<Map<String, Object>> queryResult = salesRepository.findSalesByCompany(company.getId());
        Map<String, SalesReport> resultMap = new HashMap<>();
        JsonNode exchangeRates = exchangeRateService.getConversionRate();
        Product product;
        SalesReport salesReport;
        String orderKey;
        BigDecimal salePrice;

        for(Map<String, Object> saleItem: queryResult) {
            orderKey = saleItem.get(ORDER_NUMBER).toString() + saleItem.get(ORDER_DATE).toString();
            product = new Product();
            product.setProductName(saleItem.get(PRODUCT_NAME).toString());
            product.setQuantity(Long.valueOf(saleItem.get(QUANTITY).toString()));
            salePrice = convertPrice(saleItem.get(SALE_PRICE).toString(), saleItem.get(SALE_CURRENCY).toString(), exchangeRates);
            product.setSalePrice(convertToEuroFormat(salePrice));
            salePrice = salePrice.multiply(BigDecimal.valueOf(product.getQuantity()));

            if(resultMap.containsKey(orderKey)) {
                salesReport = resultMap.get(orderKey);
                salesReport.setTotalAmount(salesReport.getTotalAmount().add(salePrice));
            } else {
                salesReport = new SalesReport();
                salesReport.setOrderDate(saleItem.get(ORDER_DATE).toString());
                salesReport.setOrderNumber(saleItem.get(ORDER_NUMBER).toString());
                salesReport.setTotalAmount(salePrice);
            }

            salesReport.setTotal(convertToEuroFormat(salesReport.getTotalAmount()));
            salesReport.getProducts().add(product);
            resultMap.put(orderKey, salesReport);
        }

        return new ArrayList<>(resultMap.values());
    }

}
