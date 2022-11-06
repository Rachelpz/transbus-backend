package cu.edu.cujae.backend.core.dto;
//import Services.BrandServiceImpl;

public class BrandDto {
    private Integer brand_id;
    private String brand_name;
    private Integer seats_numb;
    private Double fuel_consumption;
    private String fuel;
    private FuelDto fuel_type;


    public BrandDto() {
        super();
    }

    public BrandDto(Integer brand_id, String brand_name, Integer seats_numb, Double fuel_consumption, String fuel) {
        super();
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.seats_numb = seats_numb;
        this.fuel_consumption = fuel_consumption;
        this.fuel = fuel;

    }

    public BrandDto(Integer brand_id, String brand_name, Integer seats_numb, Double fuel_consumption, FuelDto fuel_type) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.seats_numb = seats_numb;
        this.fuel_consumption = fuel_consumption;
        this.fuel_type = fuel_type;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getSeats_numb() {
        return seats_numb;
    }

    public void setSeats_numb(Integer seats_numb) {
        this.seats_numb = seats_numb;
    }

    public Double getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(Double fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
    public void setFuel_type(FuelDto fuel_type) {
        this.fuel = fuel;
    }

    public FuelDto getFuel_type() {
        return fuel_type;
    }

}
