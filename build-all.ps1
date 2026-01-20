# Build All Microservices Docker Images
# This script builds all microservices JAR files and Docker images

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Building All Microservices" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# List of services to build (path:image_name)
$services = @(
    @{Path="eureka"; Image="itssena/eureka:latest"},
    @{Path="cqrs"; Image="itssena/cqrs:latest"},
    @{Path="perpustakaan\buku"; Image="itssena/buku:latest"},
    @{Path="perpustakaan\anggota"; Image="itssena/anggota:latest"},
    @{Path="perpustakaan\peminjaman"; Image="itssena/peminjaman:latest"},
    @{Path="perpustakaan\pengembalian"; Image="itssena/pengembalian:latest"},
    @{Path="perpustakaan\api-gateway"; Image="itssena/perpustakaan-gateway:latest"},
    @{Path="marketplace\produk"; Image="itssena/produk:latest"},
    @{Path="marketplace\pelanggan"; Image="itssena/pelanggan:latest"},
    @{Path="marketplace\order"; Image="itssena/order:latest"},
    @{Path="marketplace\api-gateway"; Image="itssena/marketplace-gateway:latest"}
)

$total = $services.Count
$current = 0

foreach ($service in $services) {
    $current++
    Write-Host "[$current/$total] Building $($service.Image)..." -ForegroundColor Yellow
    Write-Host "Path: $($service.Path)" -ForegroundColor Gray
    
    # Build JAR with Maven
    Write-Host "  -> Building JAR..." -ForegroundColor Green
    Push-Location $service.Path
    & .\mvnw.cmd clean package -DskipTests -q
    if ($LASTEXITCODE -ne 0) {
        Write-Host "  ERROR: Maven build failed for $($service.Path)" -ForegroundColor Red
        Pop-Location
        continue
    }
    
    # Build Docker Image
    Write-Host "  -> Building Docker image..." -ForegroundColor Green
    docker build -t $service.Image . -q
    if ($LASTEXITCODE -ne 0) {
        Write-Host "  ERROR: Docker build failed for $($service.Path)" -ForegroundColor Red
        Pop-Location
        continue
    }
    
    Write-Host "  SUCCESS: $($service.Image) built successfully!" -ForegroundColor Green
    Write-Host ""
    Pop-Location
}

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Build Complete!" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "You can now run: docker-compose up -d" -ForegroundColor Yellow
