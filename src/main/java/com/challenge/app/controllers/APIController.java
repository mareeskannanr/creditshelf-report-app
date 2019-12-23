package com.challenge.app.controllers;

import com.challenge.app.exceptions.AppException;
import com.challenge.app.services.FileProcessService;
import com.challenge.app.services.NetProfitService;
import com.challenge.app.services.RevenueReportService;
import com.challenge.app.services.SalesReportService;
import com.challenge.app.utils.AppUtils;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

import java.util.Arrays;

import static com.challenge.app.utils.AppConstants.APIController.*;

/**
 * Expose all endpoints to users
 */
@RestController
@RequestMapping(API)
@Api(tags = { REPORTS }, description = END_POINTS)
public class APIController {

    private final FileProcessService fileProcessService;
    private final SalesReportService salesReportService;
    private final RevenueReportService revenueReportService;
    private final NetProfitService netProfitService;

    public APIController(FileProcessService fileProcessService, SalesReportService salesReportService, RevenueReportService revenueReportService, NetProfitService netProfitService) {
        this.fileProcessService = fileProcessService;
        this.salesReportService = salesReportService;
        this.revenueReportService = revenueReportService;
        this.netProfitService = netProfitService;
    }

    /**
     * Feature 1: This endpoints used for users to upload products and sales files
     * @param fileType
     * @param file
     * @return
     */
    @PostMapping(UPLOAD)
    @ApiOperation(value = UPLOAD_VALUE, notes = UPLOAD_NOTES)
    public ResponseEntity saveInformation(@RequestParam(name = FILE_TYPE) String fileType,  @RequestParam(FILE) MultipartFile file) {

        if(!fileType.toLowerCase().equals(PRODUCTS) && !fileType.toLowerCase().equals(SALES)) {
            throw new AppException(INVALID_FILE);
        }

        AppUtils.validateFile(file);
        String message;
        if(fileType.toLowerCase().equals(PRODUCTS)) {
            fileProcessService.saveProducts(file);
            message = PRODUCT_INFO_UPLOAD_SUCCESS;
        } else {
            fileProcessService.saveSalesInfo(file);
            message = SALES_INFO_UPLOAD_SUCCESS;
        }

        return ResponseEntity.ok(AppUtils.generateResponse(false, message));
    }

    /**
     * This endpoint expose service to see all the sales by company
     * @param name
     * @return
     */
    @GetMapping(SALES_BY_COMPANY)
    @ApiOperation(value = SALES_REPORT_VALUE, notes = SALES_REPORT_NOTES)
    public ResponseEntity salesByCompany(@PathVariable(COMPANY) String name) {
        return ResponseEntity.ok(AppUtils.generateResponse(false, salesReportService.getSalesByCompany(name)));
    }

    /**
     * This endpoint expose service to see company revenues report by year
     * @param name
     * @param year
     * @return
     */
    @GetMapping(REVENUE_BY_COMPANY)
    @ApiOperation(value = REVENUE_REPORT_VALUE, notes = VALID_PARAMS)
    public ResponseEntity revenueByCompany(@RequestParam(COMPANY) String name, @RequestParam(YEAR) Long year) {
        return ResponseEntity.ok(AppUtils.generateResponse(false, revenueReportService.generateRevenueReport(name, year)));
    }

    /**
     * This endpoint expose service to see company net profit report by year
     * @param name
     * @param year
     * @return
     */
    @GetMapping(NET_PROFIT_BY_COMPANY)
    @ApiOperation(value = NETPROFIT_REPORT_VALUE, notes = VALID_PARAMS)
    public ResponseEntity netProfitByCompany(@RequestParam(COMPANY) String name, @RequestParam(YEAR) Long year) {
        return ResponseEntity.ok(AppUtils.generateResponse(false, netProfitService.generateNetProfitReport(name, year)));
    }

}
