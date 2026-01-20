package com.sena.Produk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @JsonProperty("id")
    private Long id;
    private String nama;
    private String satuan;
    private String harga;
}