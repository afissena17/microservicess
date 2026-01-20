package com.sena.Peminjaman.service;

import com.sena.Peminjaman.dto.PeminjamanDto;
import com.sena.Peminjaman.dto.PeminjamanMessage;
import com.sena.Peminjaman.model.Peminjaman;
import com.sena.Peminjaman.VO.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PeminjamanService {

    private final CqrsClientService cqrsClient;
    private final RestTemplate restTemplate;
    private final PeminjamanProducerService producer;
    private final AtomicLong idCounter = new AtomicLong(1);
    
    @Value("${service.anggota.url}")
    private String anggotaServiceUrl;
    
    @Value("${service.buku.url}")
    private String bukuServiceUrl;
    
    @Value("${service.pengembalian.url}")
    private String pengembalianServiceUrl;

    public List<Object> getAllPeminjaman() {
        return cqrsClient.findAll();
    }

    public Object getPeminjamanById(Long id) {
        return cqrsClient.findById(id.toString());
    }

    public Peminjaman createPeminjaman(Peminjaman p) {
        p.setId(idCounter.getAndIncrement());
        cqrsClient.save(p, p.getId().toString());
        producer.sendPeminjamanNotification(new PeminjamanMessage(p.getId(), p.getAnggotaId(), p.getBukuId()));
        return p;
    }

    public Peminjaman updatePeminjaman(Long id, Peminjaman p) {
        p.setId(id);
        cqrsClient.update(p, id.toString());
        return p;
    }

    public void deletePeminjaman(Long id) {
        cqrsClient.delete(id.toString());
    }

    public ResponseTemplateVO getPeminjamanWithDetailById(Long id) {
        Object obj = getPeminjamanById(id);
        if (obj == null) return null;
        
        @SuppressWarnings("unchecked")
        Map<String, Object> dataMap = (Map<String, Object>) obj;
        Peminjaman p = new Peminjaman();
        p.setId(((Number) dataMap.get("id")).longValue());
        p.setAnggotaId(dataMap.get("anggota_id") != null ? ((Number) dataMap.get("anggota_id")).longValue() : null);
        p.setBukuId(dataMap.get("buku_id") != null ? ((Number) dataMap.get("buku_id")).longValue() : null);
        
        Buku b = null;
        Anggota a = null;
        Pengembalian pg = null;
        
        try {
            if (p.getBukuId() != null) {
                b = restTemplate.getForObject(bukuServiceUrl + "/api/buku/" + p.getBukuId(), Buku.class);
            }
        } catch (Exception e) {
            System.err.println("Error fetching buku: " + e.getMessage());
        }
        
        try {
            if (p.getAnggotaId() != null) {
                a = restTemplate.getForObject(anggotaServiceUrl + "/api/anggota/" + p.getAnggotaId(), Anggota.class);
            }
        } catch (Exception e) {
            System.err.println("Error fetching anggota: " + e.getMessage());
        }
        
        try {
            pg = restTemplate.getForObject(pengembalianServiceUrl + "/api/pengembalian/" + id, Pengembalian.class);
        } catch (Exception e) {
            System.err.println("Error fetching pengembalian: " + e.getMessage());
        }
        
        // Hitung denda jika ada pengembalian dan terlambat
        if (pg != null && p.getTanggal_batas() != null && pg.getTanggalDikembalikan() != null) {
            long days = ChronoUnit.DAYS.between(p.getTanggal_batas(), pg.getTanggalDikembalikan());
            if (days > 0) {
                pg.setTerlambat((int) days);
                pg.setDenda(java.math.BigDecimal.valueOf(days * 1000));
            } else {
                pg.setTerlambat(0);
                pg.setDenda(java.math.BigDecimal.ZERO);
            }
        }
        
        return new ResponseTemplateVO(p, b, a, pg);
    }

    public PeminjamanDto getPeminjamanWithDenda(Long id) {
        Object obj = getPeminjamanById(id);
        return obj == null ? null : new PeminjamanDto(obj instanceof Peminjaman ? (Peminjaman) obj : new Peminjaman());
    }
}