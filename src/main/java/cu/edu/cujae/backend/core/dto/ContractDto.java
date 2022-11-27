package cu.edu.cujae.backend.core.dto;

import java.util.Date;

public class ContractDto {
    private Integer contract_id;
    private Date start_date;
    private Date end_date;
    private Float km_traveled;
    private Float amount_charged;
    private RequestDto request;

    public ContractDto() {
        super();
    }

    public ContractDto(Integer contract_id, Date start_date, Date end_date, Float km_traveled, Float amount_charged, RequestDto request) {
        this.contract_id = contract_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.km_traveled = km_traveled;
        this.amount_charged = amount_charged;
        this.request = request;
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Float getKm_traveled() {
        return km_traveled;
    }

    public void setKm_traveled(Float km_traveled) {
        this.km_traveled = km_traveled;
    }

    public Float getAmount_charged() {
        return amount_charged;
    }

    public void setAmount_charged(Float amount_charged) {
        this.amount_charged = amount_charged;
    }

    public RequestDto getRequest() {
        return request;
    }

    public void setRequest(RequestDto request) {
        this.request = request;
    }
}