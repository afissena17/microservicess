package com.sena.Peminjaman.VO;

import com.sena.Peminjaman.model.Peminjaman;
import lombok.*;

@Data
@AllArgsConstructor
public class ResponseTemplateVO {
    private Peminjaman peminjaman;
    private Buku buku;
    private Anggota anggota;
    private Pengembalian pengembalian;
}
