package com.dpslink.mobileapi.service;

import com.dpslink.mobileapi.domain.Delivery;
import com.dpslink.mobileapi.domain.Stop;
import com.dpslink.mobileapi.domain.UnparsedDelivery;
import com.dpslink.mobileapi.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JdbcDeliveryImportService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DeliveryRepository deliveryRepository;
//	private static final Logger log = Log

    public JdbcDeliveryImportService() {

    }


    public void createMobileDeliveries(JdbcTemplate jdbcTemplate, DeliveryRepository deliveryRepository) {
        List<UnparsedDelivery> stops = getUnparsedDeliveries(jdbcTemplate);
        List<Delivery> deliveries = parseDeliveriesAndStops(stops);
        //addDeliveriesToDatabase(deliveries, deliveryRepository);
    }

    private void addDeliveriesToDatabase(List<Delivery> deliveries, DeliveryRepository deliveryRepository) {
        deliveryRepository.saveAll(deliveries);
    }


    private List<Delivery> parseDeliveriesAndStops(List<UnparsedDelivery> rawDeliveries) {
        List<Delivery> parsedDeliveries = new ArrayList<>();
        Delivery currentDelivery = new Delivery();
        Stop currentStop = new Stop();
        Set<Stop> currentStops = new HashSet<>();
        String currentDeliveryNumber = "";

        //rawDeliveries.forEach(key->System.out.println(key));

        for(UnparsedDelivery rawDelivery: rawDeliveries) {
            currentDelivery = resetDelivery(currentDelivery);
            currentStop = resetStop(currentStop);

            if(!rawDelivery.getDeliverynumber().equals(currentDeliveryNumber)) {
                currentDelivery.setStops(currentStops);
                parsedDeliveries.add(currentDelivery);
                currentDelivery = resetDelivery(currentDelivery);
                currentDeliveryNumber = rawDelivery.getDeliverynumber();
                currentDelivery.setDeliveryNumber(rawDelivery.getDeliverynumber());
                currentDelivery.setDeliveryStatus(rawDelivery.getStatus());
                currentDelivery.setDeliveryWarehouse(rawDelivery.getWarehouse());
            }
            currentStop.setStopNumber(rawDelivery.getRoutestop());
            currentStop.setRouteNumber(rawDelivery.getRoute());
            currentStop.setCustomerName(rawDelivery.getCustomername());
            currentStop.setCustomerAddress(rawDelivery.getAddressline1() + " " + rawDelivery.getAddressline2() +
                " " + rawDelivery.getCity() + "," + rawDelivery.getState() + " " + rawDelivery.getZip());
            currentStop.setSequenceNumber(rawDelivery.getRoutestop());
            currentStop.setStatus("Active");
            currentStops.add(currentStop);
            currentStop = resetStop(currentStop);
        }

        // catch the final loop of deliveries and stops
        currentDelivery.setStops(currentStops);
        parsedDeliveries.add(currentDelivery);

        parsedDeliveries.forEach(key->System.out.println(key));
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
                rs.getInt("route"), rs.getInt("routestop"), rs.getInt("customerpart1"), rs.getInt("customerpart2"),
                rs.getString("customername"), rs.getString("addressline1"), rs.getString("addressline2"), rs.getString("city"),
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


