package com.dpslink.mobileapi.service;

import com.dpslink.mobileapi.domain.Delivery;
import com.dpslink.mobileapi.domain.Stop;
import com.dpslink.mobileapi.domain.UnparsedDelivery;
import com.dpslink.mobileapi.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcDeliveryImportService {

    @Autowired
    JdbcTemplate jdbcTemplate;
//	private static final Logger log = Log

    private final DeliveryRepository deliveryRepository;

    public JdbcDeliveryImportService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void createMobileDeliveries() {
        List<UnparsedDelivery> stops;
        stops = getUnparsedDeliveries();

    }

    private List<Delivery> parseDeliveriesAndStops(List<UnparsedDelivery> rawDeliveries) {
        List<Delivery> parsedDeliveries = null;
        Delivery currentDelivery = new Delivery();
        Stop currentStop = new Stop();
        String currentDeliveryNumber = null;
        for(UnparsedDelivery rawDelivery: rawDeliveries) {
            if(!rawDelivery.getDeliverynumber().equals(currentDeliveryNumber)) {
                currentDeliveryNumber = rawDelivery.getDeliverynumber();
                currentDelivery.setDeliveryNumber(rawDelivery.getDeliverynumber());
                currentDelivery.setDeliveryStatus(rawDelivery.getStatus());
                currentDelivery.setDeliveryWarehouse(rawDelivery.getWarehouse());
            }
            currentStop.setStopNumber(rawDelivery.getRoutestop());
        }

        return parsedDeliveries;
    }

    private Delivery resetDelivery(Delivery delivery) {
        delivery.setDeliveryNumber("");

        return delivery;
    }

    private Stop resetStop(Stop stop) {
        stop.setStopNumber((long) 0);


        return delivery;
    }

//    private void generateDeliveriesAndStops(List<UnparsedDelivery> stops) {
//        for (UnparsedDelivery stop : stops) {
//            Delivery delivery = this.convertToJson(stop);
//        }
//        Delivery result = deliveryRepository.save(delivery);
//    }


    private List<UnparsedDelivery> getUnparsedDeliveries() {
        List<UnparsedDelivery> stops = new ArrayList<>();
        stops.addAll(jdbcTemplate.query(
            "SELECT * FROM logistics", new Object[]{},
            (rs, rowNum) -> new UnparsedDelivery(rs.getString("status"), rs.getString("warehouse"), rs.getString("deliverynumber"),
                rs.getInt("route"), rs.getInt("routestop"), rs.getInt("customerpart1"), rs.getInt("customerpart2"),
                rs.getString("customername"), rs.getString("addressline1"), rs.getString("addressline2"), rs.getString("city"),
                rs.getString("state"), rs.getString("zip"), rs.getString("overname"), rs.getString("overaddressline1"),
                rs.getString("overaddressline2"), rs.getString("overcity"), rs.getString("overstate"), rs.getString("overzip"))));
        for (UnparsedDelivery stop : stops) {
            System.out.println(stop.getDeliverynumber());
        }
        return stops;
    }

}


