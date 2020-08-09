package org.example.config;

import lombok.AllArgsConstructor;
import org.example.model.Item;
import org.example.model.Order;
import org.example.model.Trade;
import org.example.service.OrderService;
import org.example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@AllArgsConstructor
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    OrderService orderService;

    @Autowired
    TradeService tradeService;


    @Scheduled(fixedRate = 5000)
    public void repeater() {
        // сравниваем ордеры если подходит продавец покупателю
        // убираем у ордера продавца количество купленного (update)
        // создаем и сохраняем трэйд

        for (Order buyer:orderService.getAllBuyersWithOpenStatus()) {
            for (Order seller : orderService.getAllSellersWithOpenStatus()) {
                if (seller.getPrice().toString().equals(buyer.getPrice().toString())){
                    checkTrade(seller,buyer);
                }
            }
        }
    }

    public void checkTrade(Order seller, Order buyer){
        Integer thisAmount;
        if (seller.getAmount() > buyer.getAmount()){
            seller.setAmount(seller.getAmount() - buyer.getAmount());
            buyer.setAmount(0);
            buyer.setStatus("close");
            Trade trade = Trade.builder().
                    item(buyer.getItem()).
                    price(buyer.getPrice()).
                    amount(buyer.getAmount()).
                    time(LocalDate.now()).
                    build();

            try {
                orderService.update(seller);
                orderService.update(buyer);
                tradeService.save(trade);
            } catch (Exception e) {

            }
        }else if (seller.getAmount() < buyer.getAmount()){
            seller.setAmount(0);
            buyer.setAmount(buyer.getAmount() - seller.getAmount());
            seller.setStatus("close");
            Trade trade = Trade.builder().
                    item(buyer.getItem()).
                    price(buyer.getPrice()).
                    amount(buyer.getAmount()).
                    time(LocalDate.now()).
                    build();
            try {
                orderService.update(seller);
                orderService.update(buyer);
                tradeService.save(trade);
            } catch (Exception e) {

            }
        }else if (seller.getAmount() == buyer.getAmount()){
            seller.setAmount(0);
            buyer.setAmount(0);
            seller.setStatus("close");
            buyer.setStatus("close");
            Trade trade = Trade.builder().
                    item(buyer.getItem()).
                    price(buyer.getPrice()).
                    amount(buyer.getAmount()).
                    time(LocalDate.now()).
                    build();
            try {
                orderService.update(seller);
                orderService.update(buyer);
                tradeService.save(trade);
            } catch (Exception e) {

            }
        }
    }
}
