package com.dpslink.mobileapi.repository;

import com.dpslink.mobileapi.domain.DealerStop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealerStopTest {

//    @Autowired
//    DataSource dataSource;

    @Autowired
    DealerStopRepository dealerStopRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

//    @Test
//    public void findByDeliveryId() {
////        List<DealerStop> dealerStops = dealerStopRepository.findAll();
//        List<DealerStop> dealerStops = dealerStopRepository.findByCurrentDelivery((long) 105);
//        for (DealerStop dealer: dealerStops){
//            System.out.println(dealer.toString());
//        }
//    }
//
//    @Test
//    public void findByCustomerNumber() {
//        List<DealerStop> dealerStops = dealerStopRepository.findByCustomerNumber(4077, 0);
//        for (DealerStop dealer: dealerStops){
//            System.out.println(dealer.toString());
//        }
//    }

    @Test
    public void findBySalesRepCode() {
        List<DealerStop> dealerStops = dealerStopRepository.findBySalesRepCode("CCS");
        for (DealerStop dealer: dealerStops){
            System.out.println(dealer.toString());
        }
    }



}
