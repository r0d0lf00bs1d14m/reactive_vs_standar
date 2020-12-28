package com.example.reactive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PassthroughController {

    private final PassthroughHandler passthroughHandler;

    public PassthroughController(PassthroughHandler passthroughHandler) {
        this.passthroughHandler = passthroughHandler;
    }

    @RequestMapping("/passthrough/messages")
    public ResponseEntity<Mono<MessageAck>> handlePassthrough(@RequestBody Message message) {
        return ResponseEntity.ok(
                passthroughHandler.handlePassthrough(message)
        );
    }
}
