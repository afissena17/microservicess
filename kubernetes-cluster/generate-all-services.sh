#!/bin/bash

# Script untuk menggabungkan semua service yaml menjadi satu file
# Exclude: pvc.yaml, elk-configmap.yaml, prometheus-configmap.yaml

OUTPUT_FILE="all-services-k8s.yaml"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Header
cat > "$OUTPUT_FILE" << 'EOF'
# ==========================================
# ALL SERVICES DEPLOYMENT
# ==========================================
# Deploy semua service sekaligus (kecuali PVC)
# Version: Auto-generated
# 
# Usage:
#   kubectl apply -f all-services-k8s.yaml
#
# Note: PVC harus sudah dibuat terlebih dahulu:
#   kubectl apply -f pvc.yaml
# ==========================================

EOF

# Array file yang akan digabungkan (urutan sesuai dependency)
FILES=(
    "elk-configmap.yaml"
    "prometheus-configmap.yaml"
    "eureka.yaml"
    "cqrs.yaml"
    "mongodb.yaml"
    "mongo-express.yaml"
    "rabbitmq.yaml"
    "elasticsearch.yaml"
    "logstash.yaml"
    "kibana.yaml"
    "prometheus.yaml"
    "grafana.yaml"
    "jenkins.yaml"
    "buku.yaml"
    "anggota.yaml"
    "peminjaman.yaml"
    "pengembalian.yaml"
    "perpustakaan-gateway.yaml"
    "produk.yaml"
    "pelanggan.yaml"
    "order.yaml"
    "marketplace-gateway.yaml"
)

# Gabungkan semua file
for file in "${FILES[@]}"; do
    if [ -f "$SCRIPT_DIR/$file" ]; then
        echo "---" >> "$OUTPUT_FILE"
        echo "# ==================== ${file%.yaml} ====================" >> "$OUTPUT_FILE"
        cat "$SCRIPT_DIR/$file" >> "$OUTPUT_FILE"
        echo "" >> "$OUTPUT_FILE"
    else
        echo "Warning: $file not found, skipping..."
    fi
done

echo "✅ Generated: $OUTPUT_FILE"
echo "📦 Total files merged: ${#FILES[@]}"
