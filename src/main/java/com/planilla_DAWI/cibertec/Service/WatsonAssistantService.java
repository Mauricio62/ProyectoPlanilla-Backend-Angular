package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.ChatMessageResponse;

public interface WatsonAssistantService {
    ChatMessageResponse sendMessage(String message, String sessionId);
    String createSession();
    void deleteSession(String sessionId);
}

