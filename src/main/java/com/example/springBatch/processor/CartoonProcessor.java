package com.example.springBatch.processor;

import com.example.springBatch.repo.Cartoon;
import com.example.springBatch.repo.CartoonDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CartoonProcessor implements ItemProcessor<CartoonDTO, Cartoon> {
    @Override
    public Cartoon process(CartoonDTO cartoonDTO) throws Exception {
        System.out.println("&&&&&&&&&&&& "+cartoonDTO);
        Cartoon cartoon=new Cartoon();
        cartoon.setId(cartoonDTO.getId());
        cartoon.setChannel(cartoonDTO.getChannel()+" Pro");
        cartoon.setShowName(cartoonDTO.getShowName());
        return cartoon;
    }
}
