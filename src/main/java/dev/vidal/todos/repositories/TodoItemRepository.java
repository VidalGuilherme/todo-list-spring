package dev.vidal.todos.repositories;

import dev.vidal.todos.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, UUID> {
}
