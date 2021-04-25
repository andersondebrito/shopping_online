package com.abo.shopping.repository;

import com.abo.shopping.dto.ShopReportDTO;
import com.abo.shopping.model.Shop;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository {

    public List<Shop> getShopByFilters(
            Date startDate,
            Date endDate,
            Float mininumValue);

    public ShopReportDTO getReportByDate(
            Date startDate,
            Date endDate);
}
