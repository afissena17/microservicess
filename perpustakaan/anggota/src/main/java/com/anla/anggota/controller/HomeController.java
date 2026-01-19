package com.anla.anggota.controller;

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
            "<head><meta charset='UTF-8'><title>Anggota Service</title></head>" +
            "<body style='font-family:monospace;padding:20px;max-width:800px;margin:0 auto'>" +
            "<h1 style='font-size:24px'>👥 Anggota Service API</h1>" +
            "<p style='color:green;font-weight:bold'>✓ Running</p>" +
            "<h3>Available Endpoints:</h3>" +
            "<table style='border-collapse:collapse;width:100%'>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>GET</b></td><td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/api/anggota'>/api/anggota</a></td><td style='padding:8px;border:1px solid #ddd'>Get all members</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>GET</b></td><td style='padding:8px;border:1px solid #ddd'>/api/anggota/{id}</td><td style='padding:8px;border:1px solid #ddd'>Get member by ID</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>POST</b></td><td style='padding:8px;border:1px solid #ddd'>/api/anggota</td><td style='padding:8px;border:1px solid #ddd'>Create new member</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>PUT</b></td><td style='padding:8px;border:1px solid #ddd'>/api/anggota/{id}</td><td style='padding:8px;border:1px solid #ddd'>Update member</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>DELETE</b></td><td style='padding:8px;border:1px solid #ddd'>/api/anggota/{id}</td><td style='padding:8px;border:1px solid #ddd'>Delete member</td></tr>" +
            "</table>" +
            "</body>" +
            "</html>";
    }
}
