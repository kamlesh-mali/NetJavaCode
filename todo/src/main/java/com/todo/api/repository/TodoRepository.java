package com.todo.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.todo.api.model.TodoDTO;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<TodoDTO, String>, MongoRepository<TodoDTO, String> {

	@Query("{'todo': ?0}")
	Optional<TodoDTO> findByTodo(String todo);

	Page<TodoDTO> findAll(Pageable pageable);

	Page<TodoDTO> findByTodo(String todo, Pageable pageable);

	Page<TodoDTO> findByDescription(String description, Pageable pageable);

}
