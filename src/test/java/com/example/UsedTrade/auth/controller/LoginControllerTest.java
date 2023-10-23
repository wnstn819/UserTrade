package com.example.UsedTrade.auth.controller;

import com.example.UsedTrade.auth.model.request.JoinRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    /**
     * 웹 API 테스트할 때 사용
     * 스프링 MVC 테스트의 시작점
     * HTTP GET,POST 등에 대해 API 테스트 가능
     * */
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("LoginController - 회원가입 테스트")
    void join_throwException() throws Exception {

        //given
        JoinRequest request =  new JoinRequest("test","1234");


        //when

        //then
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isOk());

    }

//    @Test
//    @DisplayName("로그인 확인")
//    void join() {
//
//        String id = "test";
//        String password = "1234";
//
//        // given
//        final UserEntity membership = UserEntity.builder()
//                .userId(id)
//                .password(password)
//                .build();
//
//        // when
//        //final UserEntity result = userRepository.save(membership);
//
//        // then
////        assertThat(result.getId()).isNotNull();
////        assertThat(result.getUserId()).isEqualTo(id);
////        assertThat(result.getPassword()).isEqualTo(password);
//    }



}