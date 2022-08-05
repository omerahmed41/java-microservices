package com.hitpixel.payment.domain.VO;


import com.hitpixel.payment.domain.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Transaction transaction;
//    private Department department;
}
