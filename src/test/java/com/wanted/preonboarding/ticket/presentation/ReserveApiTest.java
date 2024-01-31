package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.presentation.common.BaseApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ReserveApiTest extends BaseApiTest {
    @Autowired
    private TicketSeller ticketSeller;

    @Test
    @DisplayName("예약하기 조회 API 성공")
    public void getReserveInfoDetail() throws Exception {
        mockMvc.perform(get("/reserve/detail")
                        .param("name", "유진호")
                        .param("phoneNumber", "010-1234-1234"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약하기 API 성공")
    public void reservation() throws Exception {
        List<PerformanceInfo> performanceInfos = ticketSeller.getAllPerformanceInfoList();
        PerformanceInfo performanceInfo = performanceInfos.get(0);
        ReserveInfo reserveInfo = ReserveInfo.builder()
                .performanceId(performanceInfo.getPerformanceId())
                .reservationName("유진호")
                .reservationPhoneNumber("010-1234-1234")
                .reservationStatus(performanceInfo.getIsReserve())
                .amount(110000)
                .round(1)
                .line('A')
                .seat(4)
                .build();
        mockMvc.perform(post("/reserve/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserveInfo)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
