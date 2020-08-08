package org.example.service.impl;

import org.example.dao.TradeDAO;
import org.example.model.Trade;
import org.example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    TradeDAO tradeDAO;


    @Override
    public Trade save(Trade trade) throws Exception {
        if (trade.getId() != null && tradeDAO.findById(trade.getId()) != null){
            throw new Exception("trade with this id already exists");
        }
        return tradeDAO.save(trade);
    }

    @Override
    public Trade update(Trade trade) throws Exception {
        if (trade.getId() == null){
            throw new Exception("trade id not found");
        }
        return tradeDAO.save(trade);
    }

    @Override
    public Trade findById(Integer id) throws Exception {
        return tradeDAO.findById(id).orElseThrow(() -> new Exception("trade not found"));
    }

    @Override
    public void delete(Trade trade) {
        tradeDAO.delete(trade);
    }
}
