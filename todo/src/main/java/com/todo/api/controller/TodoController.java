package com.todo.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.exception.TodoCollectionException;
import com.todo.api.model.TodoDTO;
import com.todo.api.repository.TodoRepository;
import com.todo.api.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoService serviceTodoService;

	@Autowired
	private TodoRepository repositorTodoRepository;

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
		try {
			serviceTodoService.createTodo(todo);
			return new ResponseEntity<>(todo, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos() {
		List<TodoDTO> todos = serviceTodoService.getAllTodos();
		return new ResponseEntity<>(todos, !todos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(serviceTodoService.getSingleTodo(id), HttpStatus.OK);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws TodoCollectionException {
		try {
			serviceTodoService.deleteTodoById(id);
			return new ResponseEntity<>("Successfully deleted todo with id " + id, HttpStatus.OK);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDTO todo) {
		try {
			serviceTodoService.updateTodo(id, todo);
			return new ResponseEntity<>("Updated movie with id " + id + "", HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// pagination and filter
	@GetMapping("/todosPages")
	public ResponseEntity<Map<String, Object>> getAllTodoPage(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

		try {
			List<TodoDTO> pages = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);

			Page<TodoDTO> pageTuts;
			if (title == null)
				pageTuts = repositorTodoRepository.findAll(paging);
			else
				pageTuts = repositorTodoRepository.findByDescription(title, paging);

			pages = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("todos", pages);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/todo/describe")
	public ResponseEntity<Map<String, Object>> findByDescribe(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {

		try {
			List<TodoDTO> todoDTOs = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);

			Page<TodoDTO> pageTuts = repositorTodoRepository.findByTodo("ITEm 50", paging);
			todoDTOs = pageTuts.getContent();

			if (todoDTOs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("todos", todoDTOs);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
