package com.agent.controller;

import com.agent.service.SupportAgent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final SupportAgent supportAgent;

    public ChatController(SupportAgent supportAgent) {
        this.supportAgent = supportAgent;
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return supportAgent.answer(request.getMessage()).content();
    }

    // DTO for incoming request
    public static class ChatRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
