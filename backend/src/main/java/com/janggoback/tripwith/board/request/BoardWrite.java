package com.janggoback.tripwith.board.request;

import com.janggoback.tripwith.board.domain.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BoardWrite {
	private String title;
	private String content;
	
	@Builder
	public BoardWrite(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public Board toBoard() {
		return Board.builder()
				.title(this.getTitle())
				.content(this.getContent())
				.build();
	}
	
}
