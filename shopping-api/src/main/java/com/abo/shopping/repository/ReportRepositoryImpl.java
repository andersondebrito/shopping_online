package com.abo.shopping.repository;

import com.abo.shopping.dto.ShopReportDTO;
import com.abo.shopping.model.Shop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date startDate, Date endDate, Float mininumValue) {
        StringBuilder sb = new StringBuilder();
        sb.append("select s ");
        sb.append("from shop s ");
        sb.append("where s.date >= :startDate ");
        if (endDate != null) {
            sb.append("and s.date <= :endDate ");
        }
        if (mininumValue != null) {
            sb.append("and s.total <= :mininumValue ");
        }
        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("startDate", startDate);
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (mininumValue != null) {
            query.setParameter("mininumValue", mininumValue);
        }
        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(Date startDate, Date endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
        sb.append("from shopping.shop sp ");
        sb.append("where  sp.date >= :startDate ");
        sb.append("and sp.date <= :endDate ");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((BigInteger) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setMean((Double) result[2]);
        return shopReportDTO;
    }
}
