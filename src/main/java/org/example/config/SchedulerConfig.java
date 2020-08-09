package org.example.config;

import lombok.AllArgsConstructor;
import org.example.model.Item;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;

@Configuration
@AllArgsConstructor
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    OrderService orderService;

    private Item item;
    private BigDecimal price;

    @Scheduled(fixedRate = 1000)
    public void repeater() {
        // сравниваем ордеры если подходит продавец покупателю
        // убираем у ордера продавца количество купленного (update)
        // создаем и сохраняем трэйд
        }
}
