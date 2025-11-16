package com.planilla_DAWI.cibertec.Dto;

public class RoleDTO {
    private String value;
    private String description;

    public RoleDTO(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
