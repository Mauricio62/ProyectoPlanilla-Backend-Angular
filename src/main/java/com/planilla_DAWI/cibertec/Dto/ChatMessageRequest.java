package com.planilla_DAWI.cibertec.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {
    private String message;
    private String sessionId; // Para mantener el contexto de la conversación
}

