package cu.edu.cujae.backend.core.dto;

public class DriverDto {
    private Integer driver_id;
    private String driver_name;
    private String dni;
    private String address;
    private String phone_number;
    private DistrictDto district;
    private BrandDto brand;

    public DriverDto() {
        super();
    }

    public DriverDto(Integer driver_id, String driver_name, String dni, String address, String phone_number, DistrictDto district, BrandDto brand) {
        this.driver_id = driver_id;
        this.driver_name = driver_name;
        this.dni = dni;
        this.address = address;
        this.phone_number = phone_number;
        this.district = district;
        this.brand = brand;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public DistrictDto getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDto district) {
        this.district = district;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }
}
