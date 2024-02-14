package com.example.testCodeStudy.controller;

import com.example.testCodeStudy.dto.MemberResponseDto;
import com.example.testCodeStudy.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


// controller를 테스트할 수 있는 MebMvcTest
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    // controller를 테스트할 수 있는 mockMvc 객체
    @Autowired
    MockMvc mockMvc;

    // 테스트를 하기 위한 service mock 객체
    @MockBean
    MemberService memberService;

    // 멤버 리스트를 반환 받는 GET(/members) 요청 테스트
    @Test
    public void getMemberList() throws Exception {
        // given
        List<MemberResponseDto> list = List.of(new MemberResponseDto("asd", 10)
        , new MemberResponseDto("fsd", 12));

        Mockito.when(memberService.findAll()).thenReturn(list);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/members").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("fsd"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("asd"));
    }


    // 멤버 이름, 나이를 json으로 받아 멤버를 삽입하는 POST(/members) 요청 테스트
    @Test
    public void insertMember() throws Exception {
        // given
        String requestBody = "{\"name\": \"qwer\", \"age\": 6}";

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}