package com.sena.Order.VO;

import com.sena.Order.model.Order;
import lombok.*;

@Data
@AllArgsConstructor
public class ResponseTemplateVO {
    private Order order;
    private Pelanggan pelanggan;
    private Produk produk;
}
