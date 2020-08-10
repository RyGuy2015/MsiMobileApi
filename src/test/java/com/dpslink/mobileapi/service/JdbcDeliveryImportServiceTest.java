package com.dpslink.mobileapi.service;

import com.dpslink.mobileapi.domain.JdbcTest;
import com.dpslink.mobileapi.domain.UnparsedDelivery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcDeliveryImportServiceTest {

    @Autowired
    DataSource dataSource;

    @Autowired
	JdbcTemplate jdbcTemplate;

//	@Test
//    public void getItemsAsString() {
//        List<String> list = new ArrayList<>();
//        list.add("Table data...");
//        jdbcTemplate.query(
//            "SELECT * FROM logistics", new Object[]{},
//            (rs, rowNum) -> new UnparsedDelivery(rs.getString("status"), rs.getString("warehouse"), rs.getString("deliverynumber"),
//                rs.getInt("route"), rs.getInt("routestop"), rs.getInt("customerpart1"), rs.getInt("customerpart2"),
//                rs.getString("customername"), rs.getString("addressline1"), rs.getString("addressline2"), rs.getString("city"),
//                rs.getString("state"), rs.getString("zip"), rs.getString("overname"), rs.getString("overaddressline1"),
//                rs.getString("overaddressline2"), rs.getString("overcity"), rs.getString("overstate"), rs.getString("overzip")))
//            .forEach(line -> list.add(line.toString()));
//        for (String myList : list) {
//            System.out.println(myList);
//        }
//    }

    @Test
    public void getItemsAsString() {
        List<String> list = new ArrayList<>();
        list.add("Table data...");
        jdbcTemplate.query(
            "SELECT STATUS, WAREHOUSE, DELIVERYNUMBER FROM logistics", new Object[]{},
            (rs, rowNum) -> new JdbcTest(rs.getString("STATUS"), rs.getString("WAREHOUSE"), rs.getString("DELIVERYNUMBER")))
            .forEach(line -> list.add(line.toString()));
        for (String myList : list) {
            System.out.println(myList);
        }
    }
}
