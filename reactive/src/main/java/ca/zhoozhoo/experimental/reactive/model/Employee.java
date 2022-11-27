package ca.zhoozhoo.experimental.reactive.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "employees")
public record Employee(@Id UUID id, String name) {
}
