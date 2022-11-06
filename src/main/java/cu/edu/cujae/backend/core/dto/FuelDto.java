package cu.edu.cujae.backend.core.dto;

public class FuelDto {
    private Integer fuel_id;
    private String fuel_name;


    public FuelDto() {
        super();
    }

    public FuelDto(Integer fuel_id, String fuel_name) {

        this.fuel_id = fuel_id;
        this.fuel_name = fuel_name;
    }

    public FuelDto(String fuel_name) {
        this.fuel_name = fuel_name;
    }

    public Integer getFuel_id() {
        return fuel_id;
    }

    public void setFuel_id(Integer fuel_id) {
        this.fuel_id = fuel_id;
    }

    public String getFuel_name() {
        return fuel_name;
    }

    public void setFuel_name(String fuel_name) {
        this.fuel_name = fuel_name;
    }


}
