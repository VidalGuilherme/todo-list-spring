package dev.vidal.todos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoListDto(
        @NotBlank
        String name,
        @NotNull
        Boolean repeat,
        @NotNull
        String repeatDays
) {
}
