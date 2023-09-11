package com.janggoback.tripwith.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.janggoback.tripwith.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	List<Board> findByTitleLike(String searchWord);
    List<Board> findByContentLike(String searchWord);
    void deleteById(Long id);
    Page<Board> findAll(Pageable pageable);
}
