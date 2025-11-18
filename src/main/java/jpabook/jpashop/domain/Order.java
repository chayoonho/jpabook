package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "member_id") //연관관계주인은 FK있는 쪽으로 주는 것이 좋음
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    
    //java 8에서는 hibernate mappping 해줌
    private LocalDateTime orderData;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 (ORDER, CANCEL)
}
