package com.janggoback.tripwith.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.janggoback.tripwith.member.domain.Member;
import org.springframework.data.annotation.CreatedDate;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@ApiModel
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@CreatedDate
	private LocalDateTime createdAt;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "board_id")
	private Board board;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "member_id")
	private Member writer;
	
	@Builder
	public BoardReply(Member writer, String content, Board board) {
		this.writer = writer;
		this.content = content;
		this.board = board;
	}
	
}
