package cu.edu.cujae.backend.core.dto;

public class VehicleDto {

    private Integer vehicle_id;
    private String plate_numb;
    private Float planned_fuel;
    private DriverDto first_driver;
    private DriverDto second_driver;
    private BrandDto brand;

    public VehicleDto() {
        super();
    }

    public VehicleDto(Integer vehicle_id, String plate_numb, Float planned_fuel, DriverDto first_driver, DriverDto second_driver, BrandDto brand) {
        this.vehicle_id = vehicle_id;
        this.plate_numb = plate_numb;
        this.planned_fuel = planned_fuel;
        this.first_driver = first_driver;
        this.second_driver = second_driver;
        this.brand = brand;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getPlate_numb() {
        return plate_numb;
    }

    public void setPlate_numb(String plate_numb) {
        this.plate_numb = plate_numb;
    }

    public Float getPlanned_fuel() {
        return planned_fuel;
    }

    public void setPlanned_fuel(Float planned_fuel) {
        this.planned_fuel = planned_fuel;
    }

    public DriverDto getFirst_driver() {
        return first_driver;
    }

    public void setFirst_driver(DriverDto first_driver) {
        this.first_driver = first_driver;
    }

    public DriverDto getSecond_driver() {
        return second_driver;
    }

    public void setSecond_driver(DriverDto second_driver) {
        this.second_driver = second_driver;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }
}
