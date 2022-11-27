package cu.edu.cujae.backend.core.dto;

import java.util.Date;

public class ServiceDto {
    private Integer service_id;
    private String service_name;
    private String pickup_place;
    private Date pickup_time;
    private Float km_traveled;
    private Float spent_fuel;
    private String service_type;

    public ServiceDto() {
        super();
    }

    public ServiceDto(Integer service_id, String service_name, String pickup_place, Date pickup_time, Float km_traveled, Float spent_fuel, String service_type) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.pickup_place = pickup_place;
        this.pickup_time = pickup_time;
        this.km_traveled = km_traveled;
        this.spent_fuel = spent_fuel;
        this.service_type = service_type;
    }


    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getPickup_place() {
        return pickup_place;
    }

    public void setPickup_place(String pickup_place) {
        this.pickup_place = pickup_place;
    }

    public Date getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(Date pickup_time) {
        this.pickup_time = pickup_time;
    }

    public Float getKm_traveled() {
        return km_traveled;
    }

    public void setKm_traveled(Float km_traveled) {
        this.km_traveled = km_traveled;
    }

    public Float getSpent_fuel() {
        return spent_fuel;
    }

    public void setSpent_fuel(Float spent_fuel) {
        this.spent_fuel = spent_fuel;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }
}
