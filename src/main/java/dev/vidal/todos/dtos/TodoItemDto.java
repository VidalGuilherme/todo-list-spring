package dev.vidal.todos.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TodoItemDto(
        @NotBlank
        String description,
        @NotNull
        UUID todoListId,
        @NotNull
        Boolean completed
) {
}
