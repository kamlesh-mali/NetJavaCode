package com.todo.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.api.exception.TodoCollectionException;
import com.todo.api.model.TodoDTO;
import com.todo.api.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoRepository repositorTodoRepository;

	
	@Override
	public List<TodoDTO> getAllTodos() {
		List<TodoDTO> todos = repositorTodoRepository.findAll();
		if (todos.size() > 0) {
			return todos;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
		Optional<TodoDTO> todoOptional = repositorTodoRepository.findById(id);
		if (!todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		} else {
			return todoOptional.get();
		}
	}

	@Override
	public void createTodo(TodoDTO todo) throws TodoCollectionException {
		Optional<TodoDTO> todoOptional = repositorTodoRepository.findByTodo(todo.getTodo());
		if (todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
		} else {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			repositorTodoRepository.save(todo);
		}

	}

	@Override
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException {
		Optional<TodoDTO> todoWithId = repositorTodoRepository.findById(id);
		Optional<TodoDTO> todoWithSameName = repositorTodoRepository.findByTodo(todo.getTodo());
		if (todoWithId.isPresent()) {
			if (todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) {

				throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
			}
			TodoDTO todoToUpdate = todoWithId.get();

			todoToUpdate.setTodo(todo.getTodo());
			todoToUpdate.setDescription(todo.getDescription());
			todoToUpdate.setCompleted(todo.getCompleted());
			todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
			repositorTodoRepository.save(todoToUpdate);
		} else {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		}
	}

	@Override
	public void deleteTodoById(String id) throws TodoCollectionException {
		Optional<TodoDTO> todoOptional = repositorTodoRepository.findById(id);
		if (!todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		} else {
			repositorTodoRepository.deleteById(id);
		}

	}

	@Override
	public String findMaxId() {
		return null;
	}



}
