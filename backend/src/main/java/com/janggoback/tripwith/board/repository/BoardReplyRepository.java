package com.janggoback.tripwith.board.repository;

import com.janggoback.tripwith.board.domain.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long>{
	List<BoardReply> findByBoardId(Long boardId);
    void deleteById(Long id);
    Optional<BoardReply> findById(Long boardReplyId);
}
