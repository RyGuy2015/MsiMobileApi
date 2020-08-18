package com.dpslink.mobileapi.service;

import com.dpslink.mobileapi.domain.DealerStop;
import com.dpslink.mobileapi.domain.Delivery;
import com.dpslink.mobileapi.domain.Stop;
import com.dpslink.mobileapi.domain.UnparsedDelivery;
import com.dpslink.mobileapi.repository.DealerStopRepository;
import com.dpslink.mobileapi.repository.DeliveryRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class JdbcDeliveryImportService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DealerStopRepository dealerStopRepository;
//	private static final Logger log = Log

    public JdbcDeliveryImportService() {

    }


    public void createMobileDeliveries(JdbcTemplate jdbcTemplate, DeliveryRepository deliveryRepository, DealerStopRepository dealerStopRepository) {
        List<UnparsedDelivery> stops = getUnparsedDeliveries(jdbcTemplate);
        List<Delivery> deliveries = parseDeliveriesAndStops(stops, deliveryRepository, dealerStopRepository);
//        addDeliveriesToDatabase(deliveries, deliveryRepository);
    }

    private void addDeliveriesToDatabase(List<Delivery> deliveries, DeliveryRepository deliveryRepository) {
        deliveryRepository.saveAll(deliveries);
    }


    private List<Delivery> parseDeliveriesAndStops(List<UnparsedDelivery> rawDeliveries, DeliveryRepository deliveryRepository, DealerStopRepository dealerStopRepository) {
        List<Delivery> parsedDeliveries = new ArrayList<>();
        Set<Stop> currentStops = new HashSet<>();
        String currentDeliveryNumber = "";
        Delivery currentDelivery = new Delivery();
        Boolean writeObject = false;

        //rawDeliveries.forEach(key->System.out.println(key));

        for(UnparsedDelivery rawDelivery: rawDeliveries) {
            DealerStop currentStop = new DealerStop();
//            System.out.println(rawDelivery);
            if(!rawDelivery.getDeliverynumber().equals(currentDeliveryNumber)) {
                if(writeObject) {
//                    currentDelivery.setStops(currentStops);
//                    parsedDeliveries.add(currentDelivery);
                    Delivery savedDelivery = deliveryRepository.save(currentDelivery);
//TODO: add delivery id to dealerstop as foreign key
//                    savedDelivery.getDealerstops().forEach();
                    dealerStopRepository.saveAll(savedDelivery.getDealerstops());
                    savedDelivery.getDealerstops().forEach(key->System.out.println(key));
                }
                writeObject = true;
//                currentDelivery = resetDelivery(currentDelivery);
                currentDelivery = new Delivery();
                currentDeliveryNumber = rawDelivery.getDeliverynumber();
                currentDelivery.setDeliveryNumber(rawDelivery.getDeliverynumber());
                currentDelivery.setDeliveryStatus(rawDelivery.getStatus());
                currentDelivery.setDeliveryWarehouse(rawDelivery.getWarehouse());
            }
            currentStop.setStopNumber(rawDelivery.getRoutestop());
            currentStop.setRouteNumber(rawDelivery.getRoute());
            currentStop.setSalesRepCode(rawDelivery.getSalesrepcode());
            currentStop.setCustomerName(rawDelivery.getCustomername());
            currentStop.setCustomerNumber1(rawDelivery.getCustomerpart1());
            currentStop.setCustomerNumber2(rawDelivery.getCustomerpart2());
            currentStop.setCustomerAddress(rawDelivery.getAddressline1().trim() + " " + rawDelivery.getAddressline2().trim() +
                " " + rawDelivery.getCity().trim() + ", " + rawDelivery.getState() + " " + rawDelivery.getZip());
            currentStop.setSequenceNumber(rawDelivery.getRoutestop());
            currentStop.setStatus("Active");
//            currentStops.add(currentStop);
            currentDelivery.getDealerstops().add(currentStop);
//            currentStop = resetStop(currentStop);
//            currentDelivery.getStops().forEach(key->System.out.println(key));
        }

        // catch the final loop of deliveries and stops
//        currentDelivery.setStops(currentStops);
//        parsedDeliveries.add(currentDelivery);

//        currentStops.forEach(key->System.out.println(key));
//        parsedDeliveries.forEach(key->System.out.println(key));

        return parsedDeliveries;
    }


    private Delivery resetDelivery(Delivery delivery) {
        delivery.setDeliveryNumber("");
        delivery.setDeliveryStatus("");
        delivery.setDeliveryWarehouse("");

        return delivery;
    }


    private Stop resetStop(Stop stop) {
        stop.setStopNumber(0);
        stop.setRouteNumber(0);
        stop.setCustomerName("");
        stop.setCustomerAddress("");
        stop.setStatus("");
        return stop;
    }


    private List<UnparsedDelivery> getUnparsedDeliveries(JdbcTemplate jdbcTemplate) {
        List<UnparsedDelivery> stops = new ArrayList<>(jdbcTemplate.query(
            "SELECT * FROM logistics", new Object[]{},
            (rs, rowNum) -> new UnparsedDelivery(rs.getString("status"), rs.getString("warehouse"), rs.getString("deliverynumber"),
                rs.getInt("route"), rs.getInt("routestop"), rs.getString("salesrepcode"), rs.getInt("customerpart1"),
                rs.getInt("customerpart2"), rs.getString("customername"), rs.getString("addressline1"), rs.getString("addressline2"), rs.getString("city"),
                rs.getString("state"), rs.getString("zip"), rs.getString("overname"), rs.getString("overaddressline1"),
                rs.getString("overaddressline2"), rs.getString("overcity"), rs.getString("overstate"), rs.getString("overzip"))));
        for (UnparsedDelivery stop : stops) {
            //System.out.println(stop.getDeliverynumber());
        }
        return stops;
    }

//        public void testUnparsedDeliveries(JdbcTemplate jdbcTemplate) {
//            List<UnparsedDelivery> list = new ArrayList<>();
//            list.addAll(jdbcTemplate.query(
//                "SELECT * FROM logistics", new Object[]{},
//                (rs, rowNum) -> new UnparsedDelivery(rs.getString("status"), rs.getString("warehouse"), rs.getString("deliverynumber"),
//                    rs.getInt("route"), rs.getInt("routestop"), rs.getInt("customerpart1"), rs.getInt("customerpart2"),
//                    rs.getString("customername"), rs.getString("addressline1"), rs.getString("addressline2"), rs.getString("city"),
//                    rs.getString("state"), rs.getString("zip"), rs.getString("overname"), rs.getString("overaddressline1"),
//                    rs.getString("overaddressline2"), rs.getString("overcity"), rs.getString("overstate"), rs.getString("overzip"))));
//            for (UnparsedDelivery myList : list) {
//                System.out.println(myList.getDeliverynumber());
//            }
//    }
}


