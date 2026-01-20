package com.sena.Peminjaman.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return "<html>" +
            "<head><meta charset='UTF-8'><title>Peminjaman Service</title></head>" +
            "<body style='font-family:monospace;padding:20px;max-width:800px;margin:0 auto'>" +
            "<h1 style='font-size:24px'>📖 Peminjaman Service API (Aggregate)</h1>" +
            "<p style='color:green;font-weight:bold'>✓ Running</p>" +
            "<h3>Available Endpoints:</h3>" +
            "<table style='border-collapse:collapse;width:100%'>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>GET</b></td><td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/api/peminjaman'>/api/peminjaman</a></td><td style='padding:8px;border:1px solid #ddd'>Get all peminjaman</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>GET</b></td><td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/{id}</td><td style='padding:8px;border:1px solid #ddd'>Get peminjaman by ID</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>GET</b></td><td style='padding:8px;border:1px solid #ddd;background:#ffffcc'><a href='" + baseUrl + "/api/peminjaman/detail/1'>/api/peminjaman/detail/{id}</a></td><td style='padding:8px;border:1px solid #ddd'><b>Aggregate with Anggota, Buku, Pengembalian</b></td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>GET</b></td><td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/denda/{id}</td><td style='padding:8px;border:1px solid #ddd'>Calculate denda</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>POST</b></td><td style='padding:8px;border:1px solid #ddd'>/api/peminjaman</td><td style='padding:8px;border:1px solid #ddd'>Create new peminjaman</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>PUT</b></td><td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/{id}</td><td style='padding:8px;border:1px solid #ddd'>Update peminjaman</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>DELETE</b></td><td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/{id}</td><td style='padding:8px;border:1px solid #ddd'>Delete peminjaman</td></tr>" +
            "</table>" +
            "</body>" +
            "</html>";
    }
}
