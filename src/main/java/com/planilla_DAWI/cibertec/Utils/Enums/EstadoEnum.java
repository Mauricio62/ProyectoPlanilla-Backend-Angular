package com.planilla_DAWI.cibertec.Utils.Enums;

import lombok.Getter;

@Getter
public enum EstadoEnum {
    INACTIVO(0),
    ACTIVO(1),
    TODOS(2);

    private final int valor;

    EstadoEnum(int valor) {
        this.valor = valor;
    }

    public static EstadoEnum fromValor(int valor) {
        for (EstadoEnum estado : EstadoEnum.values()) {
            if (estado.valor == valor) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor no válido para EstadoEnum: " + valor);
    }
}
