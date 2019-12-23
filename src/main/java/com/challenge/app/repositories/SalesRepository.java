package com.challenge.app.repositories;

import com.challenge.app.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.challenge.app.utils.AppConstants.SalesRepository.*;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

    @Query(value = SALES_BY_COMPANY_QUERY, nativeQuery = true)
    List<Map<String, Object>> findSalesByCompany(Long companyId);

    @Query(value = REVENUE_BY_COMPANY_QUERY, nativeQuery = true)
    List<Map<String, Object>> findRevenueByCompany(Long companyId, String startDate, String endDate);

    @Query(value = NET_PROFIT_BY_COMPANY_QUERY, nativeQuery = true)
    List<Map<String, Object>> findNetProfitByCompany(Long companyId, String startDate, String endDate);

}
