package dev.vidal.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="TB_TODO_ITEMS")
public class TodoItemModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTodoItem;

    @Column(nullable = false)
    private String description;

    private boolean completed;

    @ManyToOne(targetEntity = TodoListModel.class)
    @JoinColumn(name="todo_list_id", nullable=false)
    @JsonIgnoreProperties("todoItems")
    private TodoListModel todoList;

    public TodoItemModel(){}

    public TodoItemModel(TodoListModel todoListModel){
        this.todoList = todoListModel;
    }

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

    public TodoListModel getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoListModel todoListModel) {
        this.todoList = todoListModel;
    }
}
