package com.hitpixel.payment.domain.VO;


import com.hitpixel.payment.domain.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Client client;
//    private Department department;
}
