package dev.vidal.todos.controllers;

import dev.vidal.todos.dtos.TodoListDto;
import dev.vidal.todos.models.TodoListModel;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/todo_lists")
public class TodoListController {

    @Autowired
    private TodoListRepository repository;

    @GetMapping
    public ResponseEntity<List<TodoListModel>> getTodoLists(){
        List<TodoListModel> todoListModels = repository.findAll();
        if(!todoListModels.isEmpty()){
            for (TodoListModel todoListModel : todoListModels){
                UUID id = todoListModel.getIdTodoList();
                todoListModel.add(linkTo(methodOn(TodoListController.class).getOneTodoList(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoListModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTodoList(@PathVariable(value = "id") UUID id){
        Optional<TodoListModel> todoList = repository.findById(id);
        if(todoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo List Not Found");
        }
        todoList.get().add(linkTo(methodOn(TodoListController.class).getTodoLists()).withRel("Todo Lists All"));
        return ResponseEntity.status(HttpStatus.OK).body(todoList.get());
    }

    @PostMapping
    public ResponseEntity<TodoListModel> createTodoList(@RequestBody @Valid TodoListDto todoListDto){
        TodoListModel todoListModel = new TodoListModel();
        BeanUtils.copyProperties(todoListDto, todoListModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(todoListModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodoList(@PathVariable(value = "id") UUID id){
        Optional<TodoListModel> todoList = repository.findById(id);
        if(todoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo List Not Found");
        }
        repository.delete(todoList.get());
        return ResponseEntity.status(HttpStatus.OK).body("Todo List deleted with success!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTodoList(@PathVariable(value = "id") UUID id, @RequestBody @Valid TodoListDto todoListDto){
        Optional<TodoListModel> todoList = repository.findById(id);
        if(todoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo List Not Found");
        }
        TodoListModel todoListModel = todoList.get();
        BeanUtils.copyProperties(todoListDto, todoListModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(todoListModel));
    }
}
