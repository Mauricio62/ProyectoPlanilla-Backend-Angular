package com.planilla_DAWI.cibertec.Utils.Enums;

public enum TipoAccion {
    NINGUNO(0),
    NUEVO(1),
    MODIFICAR(2),
    ELIMINAR(3),
    CAMBIAR_ESTADO(4);

    private final int valor;

    TipoAccion(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
