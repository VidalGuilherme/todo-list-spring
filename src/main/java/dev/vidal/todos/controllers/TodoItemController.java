package dev.vidal.todos.controllers;

import dev.vidal.todos.dtos.TodoItemDto;
import dev.vidal.todos.models.TodoItemModel;
import dev.vidal.todos.models.TodoListModel;
import dev.vidal.todos.repositories.TodoItemRepository;
import dev.vidal.todos.repositories.TodoListRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo_items")
public class TodoItemController {

    @Autowired
    private TodoItemRepository repository;

    @Autowired
    private TodoListRepository repositoryTodoList;

    @GetMapping
    public ResponseEntity<List<TodoItemModel>> getTodoItems(){
        List<TodoItemModel> todoItems = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(todoItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTodoItem(@PathVariable(value = "id") UUID id){
        Optional<TodoItemModel> todoItem = repository.findById(id);
        if(todoItem.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Item Not Found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(todoItem.get());
    }

    @PostMapping
    public ResponseEntity<Object> createTodoItem(@RequestBody @Valid TodoItemDto todoItemDto){
        Optional<TodoListModel> todoList = repositoryTodoList.findById(todoItemDto.todoListId());

        if(todoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo List Not Found");
        }

        TodoItemModel todoItemModel = new TodoItemModel(todoList.get());
        todoItemModel.setDescription(todoItemDto.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(todoItemModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodoItem(@PathVariable(value = "id") UUID id){
        Optional<TodoItemModel> todoItem = repository.findById(id);
        if(todoItem.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Item Not Found");
        }
        repository.delete(todoItem.get());
        return ResponseEntity.status(HttpStatus.OK).body("Todo Item deleted with success!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTodoItem(@PathVariable(value = "id") UUID id, @RequestBody @Valid TodoItemDto todoItemDto){
        Optional<TodoItemModel> todoItem = repository.findById(id);
        if(todoItem.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Item Not Found");
        }
        Optional<TodoListModel> todoList = repositoryTodoList.findById(todoItemDto.todoListId());
        if(todoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo List Not Found");
        }
        TodoItemModel todoItemModel = todoItem.get();
        todoItemModel.setDescription(todoItemDto.description());
        todoItemModel.setCompleted(todoItemDto.completed());
        todoItemModel.setTodoList(todoList.get());
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(todoItemModel));
    }

}
