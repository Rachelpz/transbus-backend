package cu.edu.cujae.backend.core.dto;

import java.util.Date;

public class RequestDto {
    private Integer request_id;
    private Date date;
    private String petitioner_name;
    private GroupDto group;
    private ServiceDto service;
    private VehicleDto vehicle;
    private String request_name;

    public RequestDto() {
        super();
    }

    public RequestDto(Integer request_id, Date date, String petitioner_name, GroupDto group, ServiceDto service, VehicleDto vehicle, String request_name) {
        this.request_id = request_id;
        this.date = date;
        this.petitioner_name = petitioner_name;
        this.group = group;
        this.service = service;
        this.vehicle = vehicle;
        this.request_name = request_name;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPetitioner_name() {
        return petitioner_name;
    }

    public void setPetitioner_name(String petitioner_name) {
        this.petitioner_name = petitioner_name;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }

    public VehicleDto getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
    }

    public String getRequest_name() {
        return request_name;
    }

    public void setRequest_name(String request_name) {
        this.request_name = request_name;
    }
}