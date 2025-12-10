package com.planilla_DAWI.cibertec.Service.Impl;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;
import com.planilla_DAWI.cibertec.Dto.ChatMessageResponse;
import com.planilla_DAWI.cibertec.Service.WatsonAssistantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class WatsonAssistantServiceImpl implements WatsonAssistantService {

    private static final Logger logger = LoggerFactory.getLogger(WatsonAssistantServiceImpl.class);
    
    @Value("${watson.assistant.api-key:}")
    private String apiKey;
    
    @Value("${watson.assistant.service-url:}")
    private String serviceUrl;
    
    @Value("${watson.assistant.assistant-id:}")
    private String assistantId;
    
    private Assistant assistant;
    private boolean isConfigured = false;

    @PostConstruct
    public void init() {
        // Verificar si las credenciales están configuradas
        if (apiKey == null || apiKey.isEmpty() || 
            apiKey.equals("TU_API_KEY_AQUI") ||
            serviceUrl == null || serviceUrl.isEmpty() ||
            serviceUrl.equals("https://api.us-south.assistant.watson.cloud.ibm.com") ||
            assistantId == null || assistantId.isEmpty() ||
            assistantId.equals("TU_ASSISTANT_ID_AQUI")) {
            
            logger.warn("Watson Assistant no está configurado. Las credenciales no han sido establecidas.");
            logger.warn("El proyecto funcionará normalmente, pero el asistente virtual no estará disponible.");
            logger.warn("Para habilitar el asistente, configure las credenciales en application.properties");
            isConfigured = false;
            return;
        }
        
        try {
            IamAuthenticator authenticator = new IamAuthenticator(apiKey);
            
            assistant = new Assistant("2021-11-27", authenticator);
            assistant.setServiceUrl(serviceUrl);
            
            isConfigured = true;
            logger.info("Watson Assistant inicializado correctamente");
        } catch (Exception e) {
            logger.error("Error al inicializar Watson Assistant: {}", e.getMessage(), e);
            logger.warn("El proyecto continuará funcionando sin el asistente virtual");
            isConfigured = false;
        }
    }
    
    public boolean isConfigured() {
        return isConfigured && assistant != null;
    }

    @Override
    public ChatMessageResponse sendMessage(String message, String sessionId) {
        ChatMessageResponse response = new ChatMessageResponse();
        
        try {
            if (!isConfigured()) {
                response.setSuccess(false);
                response.setErrorMessage("El asistente virtual no está configurado. Por favor, configure las credenciales de IBM Watson Assistant en el archivo application.properties. Consulte la guía WATSON_ASSISTANT_SETUP.md para más información.");
                return response;
            }

            // Si no hay sessionId, crear una nueva sesión
            if (sessionId == null || sessionId.isEmpty()) {
                sessionId = createSession();
            }

            // Crear el input del mensaje
            MessageInput input = new MessageInput.Builder()
                    .messageType("text")
                    .text(message)
                    .build();

            // Crear la solicitud
            MessageOptions messageOptions = new MessageOptions.Builder()
                    .assistantId(assistantId)
                    .sessionId(sessionId)
                    .input(input)
                    .build();

            // Enviar el mensaje
            MessageResponse messageResponse = assistant.message(messageOptions).execute().getResult();

            // Extraer la respuesta
            String responseText = "";
            if (messageResponse.getOutput() != null && 
                messageResponse.getOutput().getGeneric() != null &&
                !messageResponse.getOutput().getGeneric().isEmpty()) {
                
                responseText = messageResponse.getOutput().getGeneric().get(0).text();
            } else {
                responseText = "Lo siento, no pude procesar tu consulta. Por favor, intenta de nuevo.";
            }

            // Preparar el contexto si existe
            Map<String, Object> context = new HashMap<>();
            if (messageResponse.getContext() != null) {
                context.put("context", messageResponse.getContext());
            }

            response.setResponse(responseText);
            response.setSessionId(sessionId);
            response.setContext(context);
            response.setSuccess(true);

            logger.info("Mensaje procesado exitosamente. SessionId: {}", sessionId);

        } catch (Exception e) {
            logger.error("Error al enviar mensaje a Watson Assistant: {}", e.getMessage(), e);
            response.setSuccess(false);
            response.setErrorMessage("Error al procesar el mensaje: " + e.getMessage());
            response.setSessionId(sessionId);
        }

        return response;
    }

    @Override
    public String createSession() {
        try {
            if (!isConfigured()) {
                logger.warn("No se puede crear sesión: Watson Assistant no está configurado");
                return null;
            }

            CreateSessionOptions options = new CreateSessionOptions.Builder()
                    .assistantId(assistantId)
                    .build();

            SessionResponse sessionResponse = assistant.createSession(options).execute().getResult();
            String sessionId = sessionResponse.getSessionId();
            
            logger.info("Nueva sesión creada: {}", sessionId);
            return sessionId;

        } catch (Exception e) {
            logger.error("Error al crear sesión de Watson Assistant: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void deleteSession(String sessionId) {
        try {
            if (assistant == null || sessionId == null || sessionId.isEmpty()) {
                return;
            }

            DeleteSessionOptions options = new DeleteSessionOptions.Builder()
                    .assistantId(assistantId)
                    .sessionId(sessionId)
                    .build();

            assistant.deleteSession(options).execute();
            logger.info("Sesión eliminada: {}", sessionId);

        } catch (Exception e) {
            logger.error("Error al eliminar sesión de Watson Assistant: {}", e.getMessage(), e);
        }
    }
}

