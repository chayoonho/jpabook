package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속관계
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    /*
    * Business Login
    * */

    //  엔티티 자체가 해결할 수 있으면 엔티티에서 해결해야지 객체지향적 -> 도메인주도설계
    
    // stock 증가
    public void addStock(int quantity){
        this.stockQuantity =+ quantity;
    }

    // stock 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0 ){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
