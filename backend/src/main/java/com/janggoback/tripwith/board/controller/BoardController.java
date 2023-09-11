package com.janggoback.tripwith.board.controller;

import com.janggoback.tripwith.auth.jwt.service.JwtService;
import com.janggoback.tripwith.board.request.BoardEdit;
import com.janggoback.tripwith.board.request.BoardReplyWrite;
import com.janggoback.tripwith.board.request.BoardWrite;
import com.janggoback.tripwith.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final JwtService jwtService;

	@GetMapping
	public ResponseEntity<?> getBoardList(Pageable pageable) {
		return ResponseEntity.ok(boardService.getBoardList(pageable));
	}
	
	@GetMapping("/{board_id}")
	public ResponseEntity<?> getBoard(@PathVariable("board_id") Long boardId) {
		return ResponseEntity.ok(boardService.getBoard(boardId));
	}
	
	@PostMapping
	public ResponseEntity<?> write(@RequestBody BoardWrite boardWriteRequest, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boardService.writeBoard(userDetails.getUsername(), boardWriteRequest);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{board_id}")
	public ResponseEntity<?> edit(@PathVariable("board_id") Long boardId, @RequestBody BoardEdit boardEditRequest, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boardService.editBoard(boardId, boardEditRequest, userDetails.getUsername());

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{board_id}")
	public ResponseEntity<?> deleteBoard(@PathVariable("board_id") Long boardId, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boardService.deleteBoard(boardId, userDetails.getUsername());

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/{board_id}/replies")
	public ResponseEntity<?> writeReply(@RequestBody BoardReplyWrite boardReplyWriteRequest, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boardService.writeReply(boardReplyWriteRequest, userDetails.getUsername());

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{board_id}/replies/{reply_id}")
	public ResponseEntity<?> editReply(@PathVariable("reply_id") Long replyId, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boardService.deleteReply(replyId, userDetails.getUsername());

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
