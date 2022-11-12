package cu.edu.cujae.backend.core.dto;

public class GroupDto {

    private Integer group_id;
    private String group_name;
    private Integer paxamount;
    private CountryDto country;


    public GroupDto() {
        super();
    }

    public GroupDto(Integer group_id, String group_name, Integer paxamount, CountryDto country) {
        super();
        this.group_id = group_id;
        this.group_name = group_name;
        this.paxamount = paxamount;
        this.country = country;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getPaxamount() {
        return paxamount;
    }

    public void setPaxamount(Integer paxamount) {
        this.paxamount = paxamount;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
