package com.example.springBatch.processor;

import com.example.springBatch.repo.Cartoon;
import com.example.springBatch.repo.CartoonDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CartoonProcessor implements ItemProcessor<Cartoon, CartoonDTO> {
    @Override
    public CartoonDTO process(Cartoon cartoon) throws Exception {
        System.out.println("&&&&&&&&&&&& "+cartoon);
        CartoonDTO cartoonDTO=new CartoonDTO();
        cartoonDTO.setId(cartoon.getId());
        cartoonDTO.setChannel(cartoon.getChannel()+" Pro");
        cartoonDTO.setShowName(cartoon.getShowName());
        return cartoonDTO;
    }
}
