package ca.zhoozhoo.experimental.reactive.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public record Employee(@Id UUID id, String name) {
}
