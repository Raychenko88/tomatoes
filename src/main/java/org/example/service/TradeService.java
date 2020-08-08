package org.example.service;

import org.example.model.Trade;

public interface TradeService {

    Trade save(Trade trade) throws Exception;
    Trade update(Trade trade) throws Exception;
    Trade findById(Integer id) throws Exception;
    void delete(Trade trade);
}
