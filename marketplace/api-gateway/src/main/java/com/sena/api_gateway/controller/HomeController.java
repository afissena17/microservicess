package com.sena.api_gateway.controller;

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
            "<head><meta charset='UTF-8'><title>Marketplace Gateway</title></head>" +
            "<body style='font-family:monospace;padding:20px;max-width:800px;margin:0 auto'>" +
            "<h1 style='font-size:24px'>🛒 Marketplace Gateway API</h1>" +
            "<p style='color:green;font-weight:bold'>✓ Running</p>" +
            "<h3>Available Routes:</h3>" +
            "<table style='border-collapse:collapse;width:100%'>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><b>Service</b></td><td style='padding:8px;border:1px solid #ddd'><b>Endpoint</b></td><td style='padding:8px;border:1px solid #ddd'><b>Description</b></td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'>Produk</td><td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/api/produk'>/api/produk</a></td><td style='padding:8px;border:1px solid #ddd'>Product management</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'>Pelanggan</td><td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/api/pelanggan'>/api/pelanggan</a></td><td style='padding:8px;border:1px solid #ddd'>Customer management</td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'>Order</td><td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/api/order'>/api/order</a></td><td style='padding:8px;border:1px solid #ddd'>Order transactions</td></tr>" +
            "</table>" +
            "<h3 style='margin-top:20px'>Monitoring:</h3>" +
            "<table style='border-collapse:collapse;width:100%'>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/actuator/health'>Health Check</a></td></tr>" +
            "<tr><td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/actuator/prometheus'>Prometheus Metrics</a></td></tr>" +
            "</table>" +
            "</body>" +
            "</html>";
    }
}
