package com.challenge.app.utils;

import com.challenge.app.exceptions.AppException;
import com.challenge.app.models.Product;
import com.challenge.app.models.Sale;
import com.challenge.app.models.SaleItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.challenge.app.utils.AppConstants.AppUtils.*;

public class AppUtils {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final CsvMapper csvMapper = new CsvMapper();

    public static void validateFile(MultipartFile file) {

        //check for file extension
        if(!file.getOriginalFilename().endsWith(CSV_EXTENSION)) {
            throw new AppException(INVALID_FILE);
        }

        //check for file content
        if(file.isEmpty()) {
            throw new AppException(EMPTY_FILE);
        }
    }

    public static List<Object> convertCSVToMap(MultipartFile file) {
        try {
            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            return csvMapper.readerFor(Map.class).with(csvSchema).readValues(file.getInputStream()).readAll();
        } catch (IOException e) {
            throw new AppException(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Product convertToProduct(Map<String, Object> productMap) {
        Product product = new Product();

        productMap.keySet().stream().forEach(key -> {
            if(PRODUCT_ID_LIST.indexOf(key) >= 0) {
                product.setProductId(Long.valueOf((String) productMap.get(key)));
            }

            if(CURRENCY_LIST.indexOf(key) >= 0) {
                product.setCurrency((String) productMap.get(key));
            }

            if(PRODUCT_NAME_LIST.indexOf(key) >= 0) {
                product.setName((String) productMap.get(key));
            }

            if(PRODUCT_PRICE_LIST.indexOf(key) >= 0) {
                String price = (String) productMap.get(key);//USD Currency format is dollar.cent 210.15 but in Acme Corporation.csv has 210,15 (EUR format)
                price = price.replace(",", ".");
                product.setPurchasePrice(new BigDecimal(price));
            }

        });

        return product;
    }

    public static Boolean isSaleItem(Map<String, Object> salesMap) {
        return ((String) salesMap.get(COMPANY)).isEmpty()
                && ((String) salesMap.get(ORDER_DATE)).isEmpty()
                && ((String) salesMap.get(ORDER_NUMBER)).isEmpty();
    }

    public static Sale convertToSale(Map<String, Object> saleMap) {
        Sale sale = new Sale();

        saleMap.keySet().stream().forEach(key -> {
            if(key.equals(COMPANY)) {
                sale.setCompanyName((String) saleMap.get(COMPANY));
            }

            if(key.equals(ORDER_NUMBER)) {
                sale.setOrderNumber(Long.valueOf((String) saleMap.get(ORDER_NUMBER)));
            }

            if(key.equals(ORDER_DATE)) {
                sale.setOrderDate(LocalDate.parse((String) saleMap.get(ORDER_DATE), DateTimeFormatter.ofPattern(DATE_FORMAT)));
            }

        });

        return sale;
    }

    public static SaleItem convertToSaleItem(Map<String, Object> saleItemMap) {
        SaleItem saleItem = new SaleItem();

        saleItemMap.keySet().stream().forEach(key -> {
            if(key.equals(QUANTITY)) {
                saleItem.setQuantity(Long.valueOf((String) saleItemMap.get(QUANTITY)));
            }

            if(key.equals(SALE_PRICE)) {
                saleItem.setSalePrice(new BigDecimal(saleItemMap.get(SALE_PRICE).toString()));
            }

            if(key.equals(PRODUCT_ID)) {
                saleItem.setProductId(Long.valueOf((String) saleItemMap.get(PRODUCT_ID)));
            }

            if(key.equals(CURRENCY)) {
                saleItem.setCurrency((String) saleItemMap.get(CURRENCY));
            }
        });

        return saleItem;
    }

    public static JsonNode generateResponse(Boolean error, Object data) {
        ObjectNode response = mapper.createObjectNode();
        ObjectNode info = mapper.createObjectNode();

        if(error) {
            info.put(ERROR, error);
        }

        if(data instanceof String) {
            info.put(MESSAGE, data.toString());
        } else {
            info.putPOJO(DATA, data);
        }

        response.set(RESPONSE, info);
        return response;
    }

    public static BigDecimal convertPrice(String price, String currency, JsonNode exchangeRates) {
        String result = BigDecimal.ONE.toString();
        if(!currency.equals(EURO)) {
            result = exchangeRates.get(RATES).get(currency).asText();
        }
        return new BigDecimal(price).divide(new BigDecimal(result), RoundingMode.HALF_EVEN);
    }

    public static String convertToEuroFormat(BigDecimal number) {
        return NumberFormat.getNumberInstance(Locale.GERMAN).format(number.doubleValue());
    }
}
