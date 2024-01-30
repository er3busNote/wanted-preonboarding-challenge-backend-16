package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.ticket.presentation.common.BaseApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QueryApiTest extends BaseApiTest {

    @Test
    @DisplayName("모든 전시 목록 확인하기")
    public void reservation() throws Exception {
        mockMvc.perform(get("/query/all/performance"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
