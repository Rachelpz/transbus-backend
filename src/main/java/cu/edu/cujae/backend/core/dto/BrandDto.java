package cu.edu.cujae.backend.core.dto;
//import Services.BrandServiceImpl;

public class BrandDto {
    private Integer brand_id;
    private String brand_name;
    private Integer seats_numb;
    private Integer fuel_consumption;
    private FuelDto fuel_type;


    public BrandDto() {
        super();
    }

    public BrandDto(Integer brand_id, String brand_name, Integer seats_numb, Integer fuel_consumption, FuelDto fuel_type) {
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

    public Integer getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(Integer fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public FuelDto getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(FuelDto fuel_type) {
        this.fuel_type = fuel_type;
    }
}
