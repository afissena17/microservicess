package com.sena.Order.VO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelanggan {
    private Long id;
    private String nama;
    private String email;
    private String alamat;
}
