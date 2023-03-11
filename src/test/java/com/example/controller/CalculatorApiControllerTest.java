package com.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jUnit.component.Calculator;
import jUnit.component.DollarCalculator;
import jUnit.component.MarketApi;
import jUnit.controller.CalculatorApiController;
import jUnit.dto.Req;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorApiController.class)  // 테스트 할 클래스
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class}) // 테스트시 사용되는 클래스
public class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    // Get 방식 테스트
    @Test
    public void sumTest() throws Exception {
        // http://localhost:8080/api/sum

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(    // 예상하다 - 상태가 OK
                MockMvcResultMatchers.status().isOk()
        ).andExpect(    // 예상하다 - 내용이 "6000"
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print()); // 결과값을 출력
    }

    // Post 방식 테스트
    @Test
    public void minusTest() throws Exception {
        // http://localhost:8080/api/sum

        Req req = new Req();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.result").value("0")
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
                )
                .andDo(MockMvcResultHandlers.print());

    }

}


