package com.challenge.app.services;

import com.challenge.app.exceptions.AppException;
import com.challenge.app.models.Company;
import com.challenge.app.models.Product;
import com.challenge.app.models.Sale;
import com.challenge.app.models.SaleItem;
import com.challenge.app.repositories.CompanyRepository;
import com.challenge.app.repositories.ProductRepository;
import com.challenge.app.repositories.SalesRepository;
import com.challenge.app.utils.AppUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.challenge.app.utils.AppConstants.FileProcessService.*;

@Service
public class FileProcessService {

    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;

    public FileProcessService(CompanyRepository companyRepository, ProductRepository productRepository, SalesRepository salesRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.salesRepository = salesRepository;
    }

    /**
     * This method save company and products information by mapping different csv headers to a Produt
     *
     * @param file
     */
    @Transactional
    public void saveProducts(MultipartFile file) {
        String companyName = file.getOriginalFilename();
        companyName = companyName.substring(0, companyName.lastIndexOf("."));

        Company company = saveCompany(companyName);
        List<Object> productList = AppUtils.convertCSVToMap(file);
        Set<Long> idSet = new HashSet<>();

        productList.stream().forEach(productItem -> {
            Product product = AppUtils.convertToProduct((Map<String, Object>) productItem);

            //Considering Product Names are unique across all companies
            if (productRepository.existsByName(product.getName())) {
                throw new AppException(PRODUCT_NAME_EXISTS.replace(PRODUCT_NAME_HOLDER, product.getName()));
            }

            //All products of a company must have unique id
            if (idSet.contains(product.getProductId())) {
                throw new AppException(PRODUCT_ID_EXISTS.replace(PRODUCT_ID_HOLDER, product.getProductId().toString()));
            }

            idSet.add(product.getProductId());
            product.setCompany(company);
            productRepository.save(product);
        });
    }

    @Transactional
    public void saveSalesInfo(MultipartFile file) {
        List<Object> salesList = AppUtils.convertCSVToMap(file);

        //Considering first csv data line always have value for Company,Order date,Order number
        Map<String, Object> saleInfo = (Map<String, Object>) salesList.get(0);
        Sale sale = generateSale(saleInfo);

        for (int i = 1; i < salesList.size(); i++) {
            saleInfo = (Map<String, Object>) salesList.get(i);
            if (AppUtils.isSaleItem(saleInfo)) {
                setSaleItem(sale, saleInfo);
            } else {
                salesRepository.save(sale);
                sale = generateSale(saleInfo);
            }
        }
    }

    private Company saveCompany(String name) {
        if (name.trim().isEmpty()) {
            throw new AppException(COMPANY_NAME_EMPTY);
        }

        //Considering application allows only one time to upload a file with the company name
        if (companyRepository.existsByName(name)) {
            throw new AppException(COMPANY_NAME_EXISTS);
        }

        Company company = new Company(name);
        companyRepository.save(company);
        return company;
    }

    private Company getCompany(String name) {
        List<Company> companies = companyRepository.findByName(name);
        if (companies.isEmpty()) {
            throw new AppException(COMPANY_NOT_EXISTS.replace(COMPANY_NAME_HOLDER, name));
        }

        return companies.get(0);
    }

    private Sale generateSale(Map<String, Object> saleInfo) {
        Sale sale = AppUtils.convertToSale(saleInfo);
        sale.setCompany(getCompany(sale.getCompanyName()));
        setSaleItem(sale, saleInfo);
        return sale;
    }

    private void setSaleItem(Sale sale, Map<String, Object> saleInfo) {
        SaleItem saleItem = AppUtils.convertToSaleItem(saleInfo);
        saleItem.setProduct(getProduct(saleItem.getProductId(), sale.getCompany()));
        saleItem.setSale(sale);
        sale.getSaleItems().add(saleItem);
    }

    private Product getProduct(Long productId, Company company) {
        List<Product> products = productRepository.findByProductIdAndCompany(productId, company);
        if (products.isEmpty()) {
            throw new AppException(PRODUCT_ID_NOT_EXISTS.replace(PRODUCT_ID_HOLDER, productId.toString()));
        }

        return products.get(0);
    }

}
