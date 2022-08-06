package com.hitpixel.invoice.domain.VO;


import com.hitpixel.invoice.domain.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Invoice invoice;
//    private Department department;
}
