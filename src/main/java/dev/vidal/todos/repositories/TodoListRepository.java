package dev.vidal.todos.repositories;

import dev.vidal.todos.models.TodoListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoListRepository extends JpaRepository<TodoListModel, UUID> {
}
