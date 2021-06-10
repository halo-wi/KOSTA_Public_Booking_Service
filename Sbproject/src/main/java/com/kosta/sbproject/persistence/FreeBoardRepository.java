package com.kosta.sbproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.sbproject.model.FreeBoard;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {
	List<FreeBoard> findByWriter(String writer);
}
