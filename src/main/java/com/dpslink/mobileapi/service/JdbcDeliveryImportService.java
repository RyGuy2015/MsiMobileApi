package com.dpslink.mobileapi.service;

import com.dpslink.mobileapi.domain.UnparsedDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcDeliveryImportService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDeliveryImportService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
//	private static final Logger log = Log
// This Works
    public List<String> getItemsAsString() {
        List<String> list = new ArrayList<>();
        list.add("Table data...");
        jdbcTemplate.query(
            "SELECT * FROM logistics", new Object[]{},
            (rs, rowNum) -> new UnparsedDelivery(rs.getString("status"), rs.getString("warehouse"), rs.getString("deliverynumber"),
                rs.getInt("route"), rs.getInt("routestop"), rs.getInt("customerpart1"), rs.getInt("customerpart2"),
                rs.getString("customername"), rs.getString("addressline1"), rs.getString("addressline2"), rs.getString("city"),
                rs.getString("state"), rs.getString("zip"), rs.getString("overname"), rs.getString("overaddressline1"),
                rs.getString("overaddressline2"), rs.getString("overcity"), rs.getString("overstate"), rs.getString("overzip")))
            .forEach(line -> list.add(line.toString()));
        for (String myList : list) {
            System.out.println(myList);
        }
        return list;
    }
}


