package com.challenge.app.services;

import com.challenge.app.models.Company;
import com.challenge.app.repositories.SalesRepository;
import com.challenge.app.utils.AppUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.challenge.app.utils.AppConstants.RevenueReportService.*;
import static com.challenge.app.utils.AppUtils.convertPrice;
import static com.challenge.app.utils.AppUtils.convertToEuroFormat;

@Service
public class RevenueReportService {

    private final SalesRepository salesRepository;
    private final ValidationService validationService;
    private final ExchangeRateService exchangeRateService;

    public RevenueReportService(SalesRepository salesRepository, ValidationService validationService, ExchangeRateService exchangeRateService) {
        this.salesRepository = salesRepository;
        this.validationService = validationService;
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * This method calculate revenue report for each month and overall for given year and company
     *
     * @param companyName
     * @param year
     * @return
     */
    @Transactional(readOnly = true)
    public JsonNode generateRevenueReport(String companyName, Long year) {
        Company company = validationService.validateCompany(companyName);
        String startDate = START_DATE_HOLDER.replace(YEAR, year.toString());
        String endDate = END_DATE_HOLDER.replace(YEAR, year.toString());

        List<Map<String, Object>> queryResult = salesRepository.findRevenueByCompany(company.getId(), startDate, endDate);

        JsonNode exchangeRates = exchangeRateService.getConversionRate();
        BigDecimal totalRevenue = BigDecimal.ZERO;
        Map<Month, BigDecimal> resultMap = new HashMap<>();
        Map<Month, String> monthlyRevenueMap = new HashMap<>();
        Month month;
        BigDecimal tempTotal;

        for (Map<String, Object> sale : queryResult) {
            month = LocalDate.parse(sale.get(ORDER_DATE).toString()).getMonth();
            tempTotal = convertPrice(sale.get(SALE_PRICE).toString(), sale.get(SALE_CURRENCY).toString(), exchangeRates);
            tempTotal = tempTotal.multiply(new BigDecimal(sale.get(QUANTITY).toString()));
            totalRevenue = totalRevenue.add(tempTotal);

            if (resultMap.containsKey(month)) {
                resultMap.put(month, resultMap.get(month).add(tempTotal));
            } else {
                resultMap.put(month, tempTotal);
            }
        }

        resultMap.forEach((key, value) -> monthlyRevenueMap.put(key, convertToEuroFormat(value)));

        ObjectNode result = AppUtils.mapper.createObjectNode();
        result.put(TOTAL_REVENUE, convertToEuroFormat(totalRevenue));
        result.put(YEAR, year);
        result.putPOJO(MONTHLY, monthlyRevenueMap);

        return result;
    }

}
