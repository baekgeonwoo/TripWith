package com.janggoback.tripwith.board.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;

import com.janggoback.tripwith.member.domain.Member;

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
public class Board {

	@Id @Column(name = "board_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;

	@Lob
	private String content;

	private Long viewCount;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "member_id")
	private Member writer;

	@JsonManagedReference
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<BoardReply> replies = new ArrayList<>();

	@Builder
	public Board(Long id, String title, String content, LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.viewCount = 0L;
		this.createdAt = createdAt;
	}

	public void edit(String title, String content){
		this.title =title;
		this.content= content;
	}

	public void setWriter(Member writer) {
		this.writer = writer;
	}

	public void view() { this.viewCount += 1;}
}
