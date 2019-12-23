package com.challenge.app.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    private static final String ID = "Id";
    public static final String PRODUCTS = "products";
    public static final String SALE = "Sale";
    public static final String PRICE = "Price";
    public static final String COMPANY = "Company";
    public static final String ORDER = "Order";
    public static final String TOTAL = "Total";
    public static final String PRODUCT = "Product";
    public static final String PRODUCT_ID = PRODUCT + " " + ID;
    public static final String NAME = "Name";
    public static final String PRODUCT_NAME = PRODUCT + " " + NAME;
    public static final String COMPANY_NAME = COMPANY + " " + NAME;
    public static final String RATES = "rates";
    public static final String EXCEPTION = "Exception : ";
    public static final String SALE_PRICE = "SALE_PRICE";
    public static final String SALE_CURRENCY = "SALE_CURRENCY";
    public static final String ORDER_DATE = "ORDER_DATE";
    public static final String QUANTITY = "QUANTITY";
    public static final String INTERNAL_SERVER_ERROR_MSG = "Sorry, something went wrong!";
    private static final String NUMBER = "Number";
    private static final String DATE = "Date";
    public static final String COMPANY_NAME_EMPTY = COMPANY_NAME + " can't be empty";
    public static final String COMPANY_NAME_HOLDER = "<company_name>";
    private static final String NOT_EXISTS = "doesn't exists";
    public static final String COMPANY_NOT_EXISTS = COMPANY_NAME + " " + COMPANY_NAME_HOLDER + " " + NOT_EXISTS;

    public static final class FileProcessService {
        public static final String PRODUCT_NAME_HOLDER = "<product_name>";
        public static final String PRODUCT_ID_HOLDER = "<product_id>";
        private static final String ALREADY_EXISTS = "already exists";
        public static final String PRODUCT_NAME_EXISTS = PRODUCT_NAME + " " + PRODUCT_NAME_HOLDER + " " + ALREADY_EXISTS;
        public static final String PRODUCT_ID_EXISTS = PRODUCT_ID + " " + PRODUCT_ID_HOLDER + " " + ALREADY_EXISTS;
        public static final String PRODUCT_ID_NOT_EXISTS = PRODUCT_ID + " " + PRODUCT_ID_HOLDER + " " + NOT_EXISTS;
        public static final String COMPANY_NAME_EMPTY = AppConstants.COMPANY_NAME_EMPTY;
        public static final String COMPANY_NOT_EXISTS = AppConstants.COMPANY_NOT_EXISTS;
        public static final String COMPANY_NAME_HOLDER = "<company_name>";
        public static final String COMPANY_NAME_EXISTS = COMPANY_NAME + " already exists";
    }

    public static final class SalesReportService {
        public static final String PRODUCT_NAME = "PRODUCT_NAME";
        public static final String ORDER_NUMBER = "ORDER_NUMBER";
        public static final String ORDER_DATE = AppConstants.ORDER_DATE;
        public static final String SALE_PRICE = AppConstants.SALE_PRICE;
        public static final String SALE_CURRENCY = AppConstants.SALE_CURRENCY;
        public static final String QUANTITY = AppConstants.QUANTITY;
    }

    public static final class AppUtils {
        public static final List<String> PRODUCT_ID_LIST = Arrays.asList(new String[]{"Prod. Id", "Id", "Product Id"});
        public static final List<String> CURRENCY_LIST = Arrays.asList(new String[]{"Currency"});
        public static final List<String> PRODUCT_NAME_LIST = Arrays.asList(new String[]{"Description", "Product", "Name"});
        public static final List<String> PRODUCT_PRICE_LIST = Arrays.asList(new String[]{"Purchase price", "Assembly cost", "Build cost"});
        public static final String EMPTY_FILE = "Empty file, can't be process";
        public static final String INVALID_FILE = "Invalid File, can't be process";
        public static final String CSV_EXTENSION = ".csv";
        public static final String COMPANY = "Company";
        public static final String ORDER_DATE = "Order date";
        public static final String ORDER_NUMBER = "Order number";
        public static final String DATE_FORMAT = "MM/dd/yy";
        public static final String CURRENCY = "Currency";
        public static final String QUANTITY = "Quantity";
        public static final String SALE_PRICE = "Sale price";
        public static final String PRODUCT_ID = "Product Id";
        public static final String RESPONSE = "response";
        public static final String ERROR = "error";
        public static final String DATA = "data";
        public static final String MESSAGE = "message";
        public static final String EURO = "EUR";
        public static final String INTERNAL_SERVER_ERROR_MSG = AppConstants.INTERNAL_SERVER_ERROR_MSG;
        public static final String RATES = AppConstants.RATES;
    }

    public static final class SwaggerConfig {
        public static final String CTRL_PACKAGE = "com.challenge.app.controllers";
        public static final String TITLE = "Creditshelf AG";
        public static final String DESCRIPTION = "An application that enables user to easily see their sales and financial results";
        public static final String VERSION = "1.0.0";
    }

    public static final class APIController {
        public static final String API = "/api/v1.0/";
        public static final String FILE = "file";
        public static final String FILE_TYPE = FILE + "Type";
        public static final String UPLOAD = "upload";
        public static final String INFO = "Information";
        public static final String UPLOAD_SUCCESS = UPLOAD + "ed successfully";
        public static final String PRODUCTS = AppConstants.PRODUCTS;
        public static final String SALES = "sales";
        public static final String COMPANY = "company";
        public static final String COMPANY_PARAM = "{" + COMPANY + "}";
        public static final String YEAR = "year";
        public static final String SALES_BY_COMPANY = "sales/" + COMPANY_PARAM;
        public static final String REVENUE_BY_COMPANY = "revenues/";
        public static final String NET_PROFIT_BY_COMPANY = "netprofit/";
        public static final String PRODUCT_INFO_UPLOAD_SUCCESS = PRODUCT + "s " + INFO + " " + UPLOAD_SUCCESS;
        public static final String SALES_INFO_UPLOAD_SUCCESS = SALE + "s " + INFO + " " + UPLOAD_SUCCESS;
        public static final String INVALID_FILE = "Invalid File, can't be process";

        public static final String REPORTS = "Report Services";
        public static final String END_POINTS = "End Points";
        public static final String UPLOAD_VALUE = "An endpoint read products and sales file and transform them into useful information";
        public static final String UPLOAD_NOTES = "The 'fileType' must be either 'products' or 'sales', please ensure that uploading file with correct csv headers. \n File name will be your company name for 'products' filetype";
        public static final String SALES_REPORT_VALUE = "An endpoint to see all the sales by company";
        public static final String SALES_REPORT_NOTES = "Please ensure your products file related to the company uploaded";
        public static final String REVENUE_REPORT_VALUE = "An endpoint to see company's revenues report";
        public static final String NETPROFIT_REPORT_VALUE = "An endpoint to see company's net profit report";
        public static final String VALID_PARAMS = "Please ensure correct company name and year";
    }

    public static final class Product {
        public static final String PRODUCTS = AppConstants.PRODUCTS;
        public static final String COMPANY_ID = "company_id";
    }

    public static final class Company {
        public static final String COMPANY = "company";
        public static final String COMPANIES = "companies";
    }

    public static final class Sale {
        public static final String SALE = "sale";
        public static final String SALES = SALE + "s";
    }

    public static final class SaleItem {
        public static final String SALE_ITEMS = "sale_items";
        public static final String SALE_ID = SALE + "_id";
    }

    public static final class SalesRepository {
        public static final String SALES_BY_COMPANY_QUERY = "SELECT sales.order_number, sales.order_date, sale_items.currency AS sale_currency, products.name AS product_name, sale_items.quantity, sale_items.sale_price FROM sale_items LEFT OUTER JOIN products ON sale_items.product_id=products.id LEFT OUTER JOIN sales ON sales.id=sale_items.sale_id AND sales.company_id=products.company_id WHERE sales.company_id=?1";

        public static final String REVENUE_BY_COMPANY_QUERY = "SELECT sales.order_date, sale_items.quantity, sale_items.sale_price, sale_items.currency AS sale_currency FROM sale_items LEFT OUTER JOIN sales ON sales.id=sale_items.sale_id WHERE sales.company_id=?1 AND sales.order_date>=?2 AND sales.order_date<=?3 ORDER BY sales.order_date ASC";

        public static final String NET_PROFIT_BY_COMPANY_QUERY = "SELECT sales.order_date, sale_items.quantity, sale_items.sale_price, sale_items.currency AS sale_currency, products.purchase_price, products.currency AS purchase_currency FROM sale_items LEFT OUTER JOIN sales ON sales.id=sale_items.sale_id LEFT OUTER JOIN products ON products.id=sale_items.product_id WHERE sales.company_id=?1 AND sales.order_date>=?2 AND sales.order_date<=?3 ORDER BY sales.order_date ASC";
    }

    public static final class SalesReport {
        public static final String ORDER_NUMBER = ORDER + " " + NUMBER;
        public static final String ORDER_DATE = ORDER + " " + DATE;
        public static final String PRODUCTS = AppConstants.PRODUCTS;
        public static final String TOTAL = AppConstants.TOTAL;
    }

    public static final class ProductDTO {
        public static final String PRODUCT_NAME = PRODUCT + " " + NAME;
        public static final String QUANTITY = "Quantity";
        public static final String SALE_PRICE = SALE + " " + PRICE;
    }

    public static final class RevenueReportService {
        public static final String ORDER_DATE = AppConstants.ORDER_DATE;
        public static final String SALE_PRICE = AppConstants.SALE_PRICE;
        public static final String SALE_CURRENCY = AppConstants.SALE_CURRENCY;
        public static final String QUANTITY = AppConstants.QUANTITY;
        public static final String TOTAL_REVENUE = "TOTAL REVENUE";
        public static final String YEAR = "year";
        public static final String MONTHLY = "monthly";
        public static final String START_DATE_HOLDER = YEAR + "-01-01";
        public static final String END_DATE_HOLDER = YEAR + "-12-31";
    }

    public static final class NetProfitService {
        public static final String ORDER_DATE = AppConstants.ORDER_DATE;
        public static final String SALE_PRICE = AppConstants.SALE_PRICE;
        public static final String SALE_CURRENCY = AppConstants.SALE_CURRENCY;
        public static final String QUANTITY = AppConstants.QUANTITY;
        public static final String PURCHASE_PRICE = "PURCHASE_PRICE";
        public static final String PURCHASE_CURRENCY = "PURCHASE_CURRENCY";
        public static final String YEAR = "year";
        public static final String MONTHLY = "monthly";
        public static final String START_DATE_HOLDER = YEAR + "-01-01";
        public static final String END_DATE_HOLDER = YEAR + "-12-31";
        public static final String NET_PROFIT = "NET PROFIT";
    }

    public static final class ExchangeRateService {
        public static final String EXCHANGE_RATE_URL = "https://api.exchangeratesapi.io/latest";
        public static final String INTERNAL_SERVER_ERROR_MSG = AppConstants.INTERNAL_SERVER_ERROR_MSG;
        public static final String EXCEPTION = AppConstants.EXCEPTION;
    }

    public static final class ValidationService {
        public static final String COMPANY_NAME_EMPTY = AppConstants.COMPANY_NAME_EMPTY;
        public static final String COMPANY_NOT_EXISTS = AppConstants.COMPANY_NOT_EXISTS;
        public static final String COMPANY_NAME_HOLDER = AppConstants.COMPANY_NAME_HOLDER;
    }
}
