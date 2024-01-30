package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.ticket.presentation.common.BaseApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ReserveApiTest extends BaseApiTest {

    @Test
    @DisplayName("예약하기")
    public void getReservationTest() throws Exception {

        mockMvc.perform(post("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
