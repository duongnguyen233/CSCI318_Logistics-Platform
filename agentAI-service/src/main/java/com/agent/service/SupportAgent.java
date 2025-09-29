package com.agent.service;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface SupportAgent {

    @SystemMessage("""
            You are a helpful AI assistant for logistics.
            Always be concise and polite.
            """)
    Result<String> answer(@UserMessage String userMessage);
}
