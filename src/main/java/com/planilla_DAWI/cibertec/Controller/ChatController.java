package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.ChatMessageRequest;
import com.planilla_DAWI.cibertec.Dto.ChatMessageResponse;
import com.planilla_DAWI.cibertec.Service.WatsonAssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private WatsonAssistantService watsonAssistantService;

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessageRequest request) {
        try {
            if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "El mensaje no puede estar vacío");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            ChatMessageResponse response = watsonAssistantService.sendMessage(
                    request.getMessage(), 
                    request.getSessionId()
            );

            // Siempre retornamos 200 OK para que el frontend pueda manejar el mensaje de error
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al procesar el mensaje: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/session")
    public ResponseEntity<?> createSession() {
        try {
            String sessionId = watsonAssistantService.createSession();
            
            if (sessionId != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("sessionId", sessionId);
                return ResponseEntity.ok(response);
            } else {
                // Si no se puede crear la sesión, probablemente Watson no está configurado
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Watson Assistant no está configurado. Configure las credenciales en application.properties");
                response.put("sessionId", null);
                // Retornamos 200 OK pero con success=false para que el frontend pueda manejar el caso
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al crear sesión: " + e.getMessage());
            errorResponse.put("sessionId", null);
            return ResponseEntity.ok(errorResponse); // Retornamos 200 para que el frontend pueda mostrar el mensaje
        }
    }

    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable String sessionId) {
        try {
            watsonAssistantService.deleteSession(sessionId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Sesión eliminada correctamente");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al eliminar sesión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

