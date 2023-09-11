package com.janggoback.tripwith.board.service;

import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;
import com.janggoback.tripwith.board.request.BoardEdit;
import com.janggoback.tripwith.board.request.BoardReplyWrite;
import com.janggoback.tripwith.board.request.BoardWrite;
import com.janggoback.tripwith.board.response.BoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
	Board writeBoard(String writerEmail, BoardWrite boardWriteRequest);
	void deleteBoard(Long id, String email);
	BoardResponse getBoard(Long id);
	Board editBoard(Long id, BoardEdit boardEditRequest, String email);
	Page<Board> getBoardList(Pageable pageable);

	BoardReply writeReply(BoardReplyWrite boardReplyWriteRequest, String email);
	void deleteReply(Long replyId, String email);
	List<BoardReply> getBoardReplyByBoardId(Long boardId);
}
