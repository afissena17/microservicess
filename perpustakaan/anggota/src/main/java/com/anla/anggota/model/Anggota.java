package com.anla.anggota.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anggota {
    @JsonProperty("id")
    private Long id;
    private String nim;
    private String nama;
    private String alamat;
    @JsonProperty("jenis_kelamin")
    private String jenisKelamin;
    private String email;
}