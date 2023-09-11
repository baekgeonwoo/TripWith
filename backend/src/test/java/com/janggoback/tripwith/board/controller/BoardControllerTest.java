package com.janggoback.tripwith.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;
import com.janggoback.tripwith.board.repository.BoardReplyRepository;
import com.janggoback.tripwith.board.repository.BoardRepository;
import com.janggoback.tripwith.board.request.BoardEdit;
import com.janggoback.tripwith.board.request.BoardReplyWrite;
import com.janggoback.tripwith.board.request.BoardWrite;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    BoardReplyRepository boardReplyRepository;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
        boardRepository.deleteAll();
        boardReplyRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 전부 가져오기")
    void test1() throws Exception {
        List<Board> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Board.builder()
                        .title("글 제목 " + i)
                        .content("글 내용 " + i)
                        .build())
                .collect(Collectors.toList());
        boardRepository.saveAll(requestPosts);

        mockMvc.perform(get("/api/boards?page=1&size=10&sort=id,DESC")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(11))) // content + pageable
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 1개 조회")
    void test2() throws Exception{
        Member member = Member.builder().email("test@test.test").build();
        memberRepository.save(member);
        Board board = Board.builder().title("제목").content("내용").build();
        board.setWriter(member);
        boardRepository.save(board);
        boardReplyRepository.save(BoardReply.builder().writer(memberRepository.findByEmail("test@test.test").get()).board(boardRepository.findAll().get(0)).content("댓글").build());

        mockMvc.perform(get("/api/boards/{board_id}", boardRepository.findAll().get(0).getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @Test
//    @DisplayName("게시글 쓰기 성공 확인")
//    void test3()throws Exception{
//        BoardWriteRequest writeRequest = BoardWriteRequest.builder().title("제목").content("내용").build();
//        memberRepository.save(Member.builder().email("test@test.test").build());
//        String json = objectMapper.writeValueAsString(writeRequest);
//
//        mockMvc.perform(post("/api/boards")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//        System.out.println(boardRepository.findAll().get(0).toString());
//    }

//    @Test
//    @DisplayName("게시글 수정 성공 확인")
//    void test4() throws  Exception{
//        Member member = Member.builder().email("test@test.test").build();
//        memberRepository.save(member);
//        Board board = Board.builder().title("글 제목").content("글 내용").build();
//        board.setWriter(member);
//        boardRepository.save(board);
//
//        BoardEditRequest edit = BoardEditRequest.builder().title("글 제목 수정").content("글 내용 수정").build();
//        String json = objectMapper.writeValueAsString(edit);
//
//        mockMvc.perform(put("/api/boards/{boar_id}", board.getId())
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//        log.info(boardRepository.findAll().get(0).toString());
//
//    }

//    @Test
//    @DisplayName("게시글 삭제 성공 확인")
//    void test5() throws  Exception{
//        Member member = Member.builder().email("test@test.test").build();
//        memberRepository.save(member);
//        Board board = Board.builder().title("글 제목").content("글 내용").build();
//        board.setWriter(member);
//        boardRepository.save(board);
//
//        assertEquals(1L, boardRepository.count());
//        mockMvc.perform(delete("/api/boards/{boar_id}", board.getId())
//                        .contentType(APPLICATION_JSON)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//        assertEquals(0L, boardRepository.count());
//
//    }

//    @Test
//    @DisplayName("댓글 작성 성공 확인")
//    void test5() throws  Exception{
//        Member member = Member.builder().email("test@test.test").build();
//        memberRepository.save(member);
//        Board board = Board.builder().title("글 제목").content("글 내용").build();
//        board.setWriter(member);
//        boardRepository.save(board);
//
//        BoardReplyWriteRequest reply = BoardReplyWriteRequest.builder().boardId(board.getId()).content("댓글").build();
//        String json = objectMapper.writeValueAsString(reply);
//        mockMvc.perform(post("/api/boards/{board_id}/replies", boardRepository.findAll().get(0).getId())
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//        log.info("댓글:"+boardReplyRepository.findAll().get(0).getContent());
//    }

//    @Test
//    @DisplayName("댓글 삭제 성공 확인")
//    void test6() throws  Exception{
//        Member member = Member.builder().email("test@test.test").build();
//        memberRepository.save(member);
//        Board board = Board.builder().title("글 제목").content("글 내용").build();
//        board.setWriter(member);
//        boardRepository.save(board);
//
//        BoardReply reply = BoardReply.builder().writer(member).board(board).content("댓글").build();
//        boardReplyRepository.save(reply);
//
//        assertEquals(1L, boardReplyRepository.count());
//        mockMvc.perform(delete("/api/boards/{board_id}/replies/{reply_id}", board.getId(),reply.getId())
//                        .contentType(APPLICATION_JSON)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//        assertEquals(0L, boardReplyRepository.count());
//    }
}