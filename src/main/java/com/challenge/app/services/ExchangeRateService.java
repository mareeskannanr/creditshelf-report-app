package com.challenge.app.services;

import com.challenge.app.exceptions.AppException;
import com.challenge.app.utils.AppUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.challenge.app.utils.AppConstants.ExchangeRateService.*;


@Service
public class ExchangeRateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesReportService.class);
    private final RestTemplate restTemplate;
    private static JsonNode cacheValue;

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * This methods fetches currency exchange rate from external service
     * @return
     */
    public JsonNode getConversionRate() {
        try {
            ResponseEntity<String> response = restTemplate.exchange(EXCHANGE_RATE_URL, HttpMethod.GET, null, String.class);
            cacheValue = AppUtils.mapper.readTree(response.getBody());
        } catch (Exception e) {
            LOGGER.error(EXCEPTION, e);
            //Solution for ResourceAccessException, handshake_failure
            if(cacheValue == null) {
                throw new AppException(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return cacheValue;
    }

}
