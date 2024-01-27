package dev.vidal.todos.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="TB_TODO_ITEMS")
public class TodoItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTodoItem;

    @Column(nullable = false)
    private String description;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name="id_todo_list", nullable=false)
    private TodoList todoList;

    public UUID getIdTodoItem() {
        return idTodoItem;
    }

    public void setIdTodoItem(UUID idTodoItem) {
        this.idTodoItem = idTodoItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }
}
