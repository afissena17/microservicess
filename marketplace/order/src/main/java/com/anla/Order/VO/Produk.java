package com.anla.Order.VO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produk {
    private Long id;
    private String nama;
    private String satuan;
    private String harga;
}
