import java.io.*;
import java.util.*;

public class PmdChecker {
    private static final String[] SERVICES = {
        "eureka",
        "cqrs",
        "marketplace/produk",
        "marketplace/pelanggan",
        "marketplace/order",
        "marketplace/api-gateway",
        "perpustakaan/buku",
        "perpustakaan/anggota",
        "perpustakaan/pengembalian",
        "perpustakaan/peminjaman",
        "perpustakaan/api-gateway"
    };

    public static void main(String[] args) {
        String baseDir = System.getProperty("user.dir");
        if (baseDir.endsWith("testing")) {
            baseDir = new File(baseDir).getParent();
        }
        
        System.out.println("=".repeat(80));
        System.out.println("PMD Check untuk 11 Microservices");
        System.out.println("=".repeat(80));
        System.out.println();

        int passed = 0;
        int failed = 0;
        List<String> failedServices = new ArrayList<>();

        for (String service : SERVICES) {
            String servicePath = baseDir + File.separator + service;
            String serviceName = service.substring(service.lastIndexOf('/') + 1);
            
            System.out.println("Checking: " + service);
            System.out.println("-".repeat(80));
            
            try {
                ProcessBuilder pb = new ProcessBuilder("./mvnw", "pmd:check");
                pb.directory(new File(servicePath));
                pb.redirectErrorStream(true);
                Process process = pb.start();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                boolean hasViolations = false;
                int violationCount = 0;
                
                while ((line = reader.readLine()) != null) {
                    if (line.contains("PMD Failure:")) {
                        hasViolations = true;
                        violationCount++;
                        System.out.println("  " + line.trim());
                    }
                }
                
                int exitCode = process.waitFor();
                
                if (exitCode == 0) {
                    System.out.println("✅ SUKSES: " + service);
                    passed++;
                } else {
                    System.out.println("❌ GAGAL: " + service + " (" + violationCount + " violations)");
                    failed++;
                    failedServices.add(service);
                }
                
            } catch (Exception e) {
                System.out.println("❌ ERROR: " + service + " - " + e.getMessage());
                failed++;
                failedServices.add(service);
            }
            
            System.out.println();
        }

        System.out.println("=".repeat(80));
        System.out.println("RINGKASAN");
        System.out.println("=".repeat(80));
        System.out.println("Total Services: " + SERVICES.length);
        System.out.println("✅ Passed: " + passed);
        System.out.println("❌ Failed: " + failed);
        
        if (!failedServices.isEmpty()) {
            System.out.println("\nServices yang gagal:");
            for (String service : failedServices) {
                System.out.println("  - " + service);
            }
        }
        
        System.exit(failed > 0 ? 1 : 0);
    }
}
