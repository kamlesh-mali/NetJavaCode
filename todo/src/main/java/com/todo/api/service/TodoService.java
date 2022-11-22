package com.todo.api.service;

import java.util.List;

import com.todo.api.exception.TodoCollectionException;
import com.todo.api.model.TodoDTO;

public interface TodoService {
	public List<TodoDTO> getAllTodos();

	public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

	public void createTodo(TodoDTO todo) throws TodoCollectionException;

	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;

	public void deleteTodoById(String id) throws TodoCollectionException;

	public String findMaxId();
}
