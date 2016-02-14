package com.mycompany.hr.model;

public class HolidayServiceResult {

    public static enum Status {APPROVED, REJECTED};
    
    private Status status;
    private String detail;
    
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }    
    
}
