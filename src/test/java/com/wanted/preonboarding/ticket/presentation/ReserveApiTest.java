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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ReserveApiTest extends BaseApiTest {
    @Autowired
    private TicketSeller ticketSeller;

    @Test
    @DisplayName("예약하기 API 성공")
    public void reservation() throws Exception {
        PerformanceInfo performanceInfo = ticketSeller.getPerformanceInfoDetail("레베카");
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
