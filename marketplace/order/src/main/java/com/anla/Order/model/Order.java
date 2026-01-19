package com.anla.Order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("pelanggan_id")
    private Long pelangganId;
    @JsonProperty("produk_id")
    private Long produkId;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("total_harga")
    private Double totalHarga;
    @JsonProperty("status")
    private String status;
    @JsonProperty("order_date")
    private LocalDate orderDate;
}