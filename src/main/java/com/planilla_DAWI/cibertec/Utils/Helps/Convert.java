package com.planilla_DAWI.cibertec.Utils.Helps;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Convert {
    // Métodos de ayuda para la conversión
    public static Date convertToDate(LocalDateTime localDateTime) {
        return localDateTime != null ?
                Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()) :
                null;
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date != null ?
                LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()) :
                null;
    }
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
