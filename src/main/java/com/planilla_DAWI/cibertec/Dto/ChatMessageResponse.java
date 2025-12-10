package com.planilla_DAWI.cibertec.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponse {
    private String response;
    private String sessionId;
    private Map<String, Object> context; // Contexto adicional si es necesario
    private boolean success;
    private String errorMessage;
}

