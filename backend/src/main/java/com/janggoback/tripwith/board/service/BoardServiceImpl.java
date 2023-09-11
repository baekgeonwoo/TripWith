package com.janggoback.tripwith.board.service;

import com.janggoback.tripwith.auth.exception.UnAuthorizationException;
import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;
import com.janggoback.tripwith.board.exception.BoardNotFoundException;
import com.janggoback.tripwith.board.exception.BoardReplyNotFoundException;
import com.janggoback.tripwith.board.repository.BoardReplyRepository;
import com.janggoback.tripwith.board.repository.BoardRepository;
import com.janggoback.tripwith.board.request.BoardEdit;
import com.janggoback.tripwith.board.request.BoardReplyWrite;
import com.janggoback.tripwith.board.request.BoardWrite;
import com.janggoback.tripwith.board.response.BoardResponse;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.exception.MemberNotFoundException;
import com.janggoback.tripwith.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;
	private final BoardReplyRepository boardReplyRepository;
	private final MemberRepository memberRepository;

	@Override
	public Board writeBoard(String writerEmail, BoardWrite boardWriteRequest) {
		Board board = boardWriteRequest.toBoard();
		Member writer = memberRepository.findByEmail(writerEmail).orElseThrow(MemberNotFoundException::new);
		board.setWriter(writer);
		
		return boardRepository.save(board);
	}

	@Override
	public Board editBoard(Long id, BoardEdit boardEditRequest, String email) {
		Board origin = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

		if (!origin.getWriter().getEmail().equals(email)) {
			throw new UnAuthorizationException();
		}
		origin.edit(boardEditRequest.getTitle(),boardEditRequest.getContent() );
		return origin;
	}

	@Override
	public void deleteBoard(Long id, String email) {
		Board origin = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

		if (!origin.getWriter().getEmail().equals(email)) {
			throw new UnAuthorizationException();
		}
		boardRepository.deleteById(id);
	}

	@Override
	public BoardResponse getBoard(Long id) {
		Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
		board.view();
		
		List<BoardReply> replies = boardReplyRepository.findByBoardId(id);
		
		return BoardResponse.from(board, replies);
	}

	@Override
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Override
	public BoardReply writeReply(BoardReplyWrite boardReplyWriteRequest, String email) {
		Board board = boardRepository.findById(boardReplyWriteRequest.getBoardId()).orElseThrow(BoardNotFoundException::new);
		BoardReply boardReply = boardReplyWriteRequest.toBoardReply(boardReplyWriteRequest, memberRepository.findByEmail(email).get(), board);

		return boardReplyRepository.save(boardReply);
	}

	@Override
	public void deleteReply(Long replyId, String email) {

		if (!memberRepository.findById(boardReplyRepository.findById(replyId).orElseThrow(BoardReplyNotFoundException::new).getWriter().getId()).orElseThrow(MemberNotFoundException::new).getEmail().equals(email)) {
			throw new UnAuthorizationException();
		}

		boardReplyRepository.deleteById(replyId);
	}

	@Override
	public List<BoardReply> getBoardReplyByBoardId(Long boardId) {
		return boardReplyRepository.findByBoardId(boardId);
	}
	
}
