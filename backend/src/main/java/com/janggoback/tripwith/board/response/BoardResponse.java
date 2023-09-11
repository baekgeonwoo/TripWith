package com.janggoback.tripwith.board.response;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ApiModel
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long writerId;
	private String title;
	private String content;
	private Long viewCount;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	private List<BoardReply> replies;

	@Builder
	public BoardResponse(Long id, Long writerId, String title, String content, Long viewCount, LocalDateTime createdAt, List<BoardReply> replies) {
		this.id = id;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.createdAt = createdAt;
		this.replies = replies;
	}
	
	public static BoardResponse from(Board board, List<BoardReply> replies) {
		return BoardResponse.builder()
				.id(board.getId())
				.writerId(board.getWriter().getId())
				.title(board.getTitle())
				.content(board.getContent())
				.viewCount(board.getViewCount())
				.createdAt(board.getCreatedAt())
				.replies(replies)
				.build();
	}
	
}
