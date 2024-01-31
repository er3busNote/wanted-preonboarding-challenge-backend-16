package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.presentation.common.BaseApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QueryApiTest extends BaseApiTest {
    @Autowired
    private TicketSeller ticketSeller;

    @Test
    @DisplayName("공연/전시 정보 목록 API 성공")
    public void getAllPerformanceInfoList() throws Exception {
        mockMvc.perform(get("/query/all/performance"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공연/전시 정보 상세 API 성공")
    public void getPerformanceInfoDetail() throws Exception {
        List<PerformanceInfo> performanceInfos = ticketSeller.getAllPerformanceInfoList();
        PerformanceInfo performanceInfo = performanceInfos.get(0);
        UUID uuid = performanceInfo.getPerformanceId();
        mockMvc.perform(get("/query/detail/performance/{uuid}", uuid))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
