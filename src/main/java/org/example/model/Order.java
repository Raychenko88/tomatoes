package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = Item.class)
    private Item item;
    private BigDecimal price;
    private Integer amount;
//    private LocalDate time;
    private Offer offer;
    private Status status;
//    private Integer idSTrade;
}
