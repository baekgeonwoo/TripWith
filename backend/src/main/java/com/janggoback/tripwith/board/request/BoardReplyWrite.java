package com.janggoback.tripwith.board.request;

import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;

import com.janggoback.tripwith.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BoardReplyWrite {
	private String content;
	private Long boardId;
	
	@Builder
	public BoardReplyWrite(String content, Long boardId) {
		this.content = content;
		this.boardId = boardId;
	}
	
	public BoardReply toBoardReply(BoardReplyWrite boardReplyWriteRequest, Member writer, Board board) {
		return BoardReply.builder()
				.writer(writer)
				.content(boardReplyWriteRequest.getContent())
				.board(board)
				.build();
	}
}
