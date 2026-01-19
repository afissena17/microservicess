package com.anla.Pengembalian.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pengembalian {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("tanggal_dikembalikan")
    private LocalDate tanggalDikembalikan;
    private int terlambat;
    private BigDecimal denda;
    @JsonProperty("peminjaman_id")
    private Long peminjamanId;
}
