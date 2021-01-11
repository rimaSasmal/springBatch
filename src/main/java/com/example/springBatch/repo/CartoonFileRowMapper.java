package com.example.springBatch.repo;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CartoonFileRowMapper implements FieldSetMapper<CartoonDTO> {
    @Override
    public CartoonDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        CartoonDTO cartoonDTO=new CartoonDTO();
        cartoonDTO.setId(fieldSet.readInt("id"));
        cartoonDTO.setShowName(fieldSet.readString("showName"));
        cartoonDTO.setChannel(fieldSet.readString("channel"));

        return cartoonDTO;
    }
}
