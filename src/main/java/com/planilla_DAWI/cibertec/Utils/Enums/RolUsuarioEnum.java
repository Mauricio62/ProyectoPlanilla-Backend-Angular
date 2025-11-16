package com.planilla_DAWI.cibertec.Utils.Enums;

public enum RolUsuarioEnum {
    USUARIO("Usuario estándar"),
    ADMINISTRADOR("Administrador del sistema"),
    RRHH("Recursos Humanos"),
    CONTABILIDAD("Contabilidad"),
    GERENTE("Gerente");

    private final String description;

    RolUsuarioEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static RolUsuarioEnum fromString(String text) {
        for (RolUsuarioEnum role : RolUsuarioEnum.values()) {
            if (role.name().equalsIgnoreCase(text)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Rol no válido: " + text);
    }
}
