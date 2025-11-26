package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    // 상품 서비스(Service) 계층의 주요 역할은 리포지토리에 데이터 접근 작업 위임
    // 경우에 따라 contoller -> repository로 바로 호출해야할지 고민해 볼 필요 있음

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){

        // dirty checking 필수!!
        Item findItem = itemRepository.findOne(itemId);

        // 예제일 뿐 단발성 업데이트를 하면안됨
        // findItem.change(price, name, stockQuantity) 처럼 메서드를 생성해야 함
        // entity 레벨에서 변경되는걸 추적할 수 있어야함

        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        /*
        * 변경감지로 인해 update sql 실행
        * 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경할 수 있지만,
        * 병합을 사용할면 모든 속성이 변경 됨
        * 병합시 값이 없으면 null 로 업데이트 할 위험이 있음
        * */
    }
}