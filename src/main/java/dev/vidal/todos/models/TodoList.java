package dev.vidal.todos.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="TB_TODO_LISTS")
public class TodoList implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTodoList;

    @Column(nullable = true)
    private String name;

    private boolean repeat = false;

    @Column(nullable = true)
    private String repeatDays;

    @ManyToOne
    @JoinColumn(name = "id_todo_list")
    private List<TodoItem> todoItems;

    public UUID getIdTodoList() {
        return idTodoList;
    }

    public void setIdTodoList(UUID idTodoList) {
        this.idTodoList = idTodoList;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    @Nullable
    public String getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(@Nullable String repeatDays) {
        this.repeatDays = repeatDays;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }
}
