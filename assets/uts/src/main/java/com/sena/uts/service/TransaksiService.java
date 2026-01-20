package com.sena.uts.service;

import com.sena.uts.model.Transaksi;
import com.sena.uts.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaksiService {
    
    @Autowired
    private TransaksiRepository transaksiRepository;
    
    // dibuek
    public Transaksi tambahTransaksi(Transaksi transaksi) {
        transaksi.hitungPemakaianDanTotal();
        return transaksiRepository.save(transaksi);
    }
    
    // baco
    public List<Transaksi> getAllTransaksi() {
        return transaksiRepository.findAll();
    }
    
    // baco
    public Optional<Transaksi> getTransaksiById(Long id) {
        return transaksiRepository.findById(id);
    }
    
    // perbarui
    public Transaksi updateTransaksi(Long id, Transaksi transaksiDetails) {
        Transaksi transaksi = transaksiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan dengan id: " + id));
        
        transaksi.setPelanggan(transaksiDetails.getPelanggan());
        transaksi.setMeterBulanIni(transaksiDetails.getMeterBulanIni());
        transaksi.setMeterBulsenalu(transaksiDetails.getMeterBulsenalu());
        transaksi.setTarifPerMeter(transaksiDetails.getTarifPerMeter());
        transaksi.hitungPemakaianDanTotal();
        
        return transaksiRepository.save(transaksi);
    }
    
    public void deleteTransaksi(Long id) {
        transaksiRepository.deleteById(id);
    }
}