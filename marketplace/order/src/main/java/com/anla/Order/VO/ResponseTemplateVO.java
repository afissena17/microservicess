package com.anla.Order.VO;

import com.anla.Order.model.Order;
import lombok.*;

@Data
@AllArgsConstructor
public class ResponseTemplateVO {
    private Order order;
    private Pelanggan pelanggan;
    private Produk produk;
}
