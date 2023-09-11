package com.janggoback.tripwith.board.service;

import com.janggoback.tripwith.auth.exception.UnAuthorizationException;
import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;
import com.janggoback.tripwith.board.repository.BoardReplyRepository;
import com.janggoback.tripwith.board.repository.BoardRepository;
import com.janggoback.tripwith.board.request.BoardEdit;
import com.janggoback.tripwith.board.request.BoardReplyWrite;
import com.janggoback.tripwith.board.request.BoardWrite;
import com.janggoback.tripwith.board.response.BoardResponse;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.repository.MemberRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BoardServiceImplTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardReplyRepository boardReplyRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean(){
        boardReplyRepository.deleteAll();
        boardRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        Board board = boardRepository.findAll().get(0);
        assertEquals("제목", board.getTitle());
        assertEquals("내용", board.getContent());
        assertEquals("test@test.test", board.getWriter().getEmail());
    }

    @Test
    @DisplayName("글 수정 성공")
    void test2(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardEdit edit = BoardEdit.builder().title("제목 수정").content("내용 수정").build();
        boardService.editBoard(boardRepository.findByTitleLike("제목").get(0).getId(), edit, "test@test.test");

        Board result = boardRepository.findAll().get(0);
        assertEquals("제목 수정", result.getTitle());
        assertEquals("내용 수정", result.getContent());
        assertEquals("test@test.test", result.getWriter().getEmail());
    }

    @Test
    @DisplayName("글 수정 실패")
    void test3(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardEdit edit = BoardEdit.builder().title("제목 수정").content("내용 수정").build();
        assertThrows(UnAuthorizationException.class, ()->{
            boardService.editBoard(boardRepository.findByTitleLike("제목").get(0).getId(), edit, "test1@test.test");
        });
    }

    @Test
    @DisplayName("글 삭제 성공")
    void test4(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        assertEquals(1L, boardRepository.count());
        boardService.deleteBoard(boardRepository.findByTitleLike("제목").get(0).getId(), "test@test.test");

        assertEquals(0L, boardRepository.count());
    }

    @Test
    @DisplayName("글 삭제 실패")
    void test5(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        assertEquals(1L, boardRepository.count());
        assertThrows(UnAuthorizationException.class, ()->{
            boardService.deleteBoard(boardRepository.findByTitleLike("제목").get(0).getId(), "test1@test.test");
        });
        assertEquals(1L, boardRepository.count());
    }

    @Test
    @DisplayName("댓글 작성")
    void test6(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardReplyWrite replyWriteRequest = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글입니다.").build();
        boardService.writeReply(replyWriteRequest, memberRepository.findAll().get(0).getEmail());

        assertEquals("댓글입니다.", boardReplyRepository.findAll().get(0).getContent());
    }

    @Test
    @DisplayName("댓글 삭제")
    void test7(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardReplyWrite replyWriteRequest = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글입니다.").build();
        boardService.writeReply(replyWriteRequest, memberRepository.findAll().get(0).getEmail());

        assertEquals("댓글입니다.", boardReplyRepository.findAll().get(0).getContent());

        boardService.deleteReply(boardReplyRepository.findAll().get(0).getId(),"test@test.test");

        assertEquals(0L, boardReplyRepository.count());
    }

    @Test
    @DisplayName("댓글 삭제 실패")
    void test8(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardReplyWrite replyWriteRequest = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글입니다.").build();
        boardService.writeReply(replyWriteRequest, memberRepository.findAll().get(0).getEmail());

        assertEquals("댓글입니다.", boardReplyRepository.findAll().get(0).getContent());

        assertThrows(UnAuthorizationException.class, ()->{
            boardService.deleteReply(boardReplyRepository.findAll().get(0).getId(),"test1@test.test");
        });
    }

    @Test
    @DisplayName("글 조회")
    void test9(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardReplyWrite replyWriteRequest = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글입니다.").build();
        boardService.writeReply(replyWriteRequest, memberRepository.findAll().get(0).getEmail());

        BoardResponse findBoard = boardService.getBoard(boardRepository.findAll().get(0).getId());
        assertEquals("제목", findBoard.getTitle());
        assertEquals("내용", findBoard.getContent());
        assertEquals("댓글입니다.", findBoard.getReplies().get(0).getContent());
        assertEquals("test@test.test", memberRepository.findById(findBoard.getWriterId()).get().getEmail());
    }

    @Test
    @DisplayName("글 삭제시 댓글도 삭제")
    void test10(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardReplyWrite replyWriteRequest = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글입니다.").build();
        boardService.writeReply(replyWriteRequest, memberRepository.findAll().get(0).getEmail());


        assertEquals(1L, boardReplyRepository.count());
        boardService.deleteBoard(boardRepository.findByTitleLike("제목").get(0).getId(), "test@test.test");

        assertEquals(0L, boardReplyRepository.count());
    }

    @Test
    @DisplayName("글 ID로 모든 댓글 조회")
    void test11(){
        BoardWrite writeRequest = BoardWrite.builder().title("제목").content("내용").build();
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);

        boardService.writeBoard(member.getEmail(), writeRequest);

        BoardReplyWrite replyWriteRequest1 = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글1입니다.").build();
        boardService.writeReply(replyWriteRequest1, memberRepository.findAll().get(0).getEmail());
        BoardReplyWrite replyWriteRequest2 = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글2입니다.").build();
        boardService.writeReply(replyWriteRequest2, memberRepository.findAll().get(0).getEmail());
        BoardReplyWrite replyWriteRequest3 = BoardReplyWrite.builder().boardId(boardRepository.findAll().get(0).getId()).content("댓글3입니다.").build();
        boardService.writeReply(replyWriteRequest3, memberRepository.findAll().get(0).getEmail());

        List<BoardReply> result = boardService.getBoardReplyByBoardId(boardRepository.findAll().get(0).getId());
        assertEquals(3L, result.size());
    }
}