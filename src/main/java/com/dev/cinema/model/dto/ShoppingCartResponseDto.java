package com.dev.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private List<Long> ticketIds;
    private Long userId;

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
