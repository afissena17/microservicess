#!/bin/bash

# ip.sh - Auto-update Jenkins IP configuration

CONTAINER_NAME="jenkins"
CONFIG_FILE="/var/jenkins_home/jenkins.model.JenkinsLocationConfiguration.xml"
CONFIG_MAIN="/var/jenkins_home/config.xml"

# Warna output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Jenkins IP Updater ===${NC}"

# 1. Cek apakah container Jenkins berjalan
if ! docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
    echo -e "${RED}Error: Container '${CONTAINER_NAME}' tidak berjalan!${NC}"
    echo "Pastikan jalankan: docker-compose up -d"
    exit 1
fi

# 2. Input IP baru dengan validasi
while true; do
    read -p "Masukkan IP Public Baru: " NEW_IP
    
    # Validasi format IP (sederhana)
    if [[ $NEW_IP =~ ^[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
        break
    else
        echo -e "${RED}Format IP tidak valid! Cth: 13.213.53.252${NC}"
    fi
done

# 3. Backup file konfigurasi
echo -e "${YELLOW}Mencadangkan file konfigurasi...${NC}"
docker exec ${CONTAINER_NAME} cp ${CONFIG_FILE} ${CONFIG_FILE}.backup
docker exec ${CONTAINER_NAME} cp ${CONFIG_MAIN} ${CONFIG_MAIN}.backup

# 4. Ganti IP di jenkins.model.JenkinsLocationConfiguration.xml
echo "Mengganti IP di ${CONFIG_FILE}..."
docker exec ${CONTAINER_NAME} sed -i "s|<jenkinsUrl>http://.*:8080/ *</jenkinsUrl>|<jenkinsUrl>http://${NEW_IP}:8080/ </jenkinsUrl>|" ${CONFIG_FILE}

# 5. Hapus blok Theme Manager yang menyebabkan timeout
echo "Menghapus Theme Manager bermasalah..."
docker exec ${CONTAINER_NAME} sed -i '/<io.jenkins.plugins.thememanager.ThemeManagerPageDecorator>/,/<\/io.jenkins.plugins.thememanager.ThemeManagerPageDecorator>/d' ${CONFIG_MAIN}

# 6. Restart Jenkins
echo -e "${YELLOW}Restarting Jenkins...${NC}"
docker restart ${CONTAINER_NAME}

# 7. Tunggu 15 detik dan cek status
echo "Menunggu 15 detik..."
sleep 15

if docker ps | grep -q ${CONTAINER_NAME}; then
    echo -e "${GREEN}✓ Jenkins restarted sukses!${NC}"
    echo -e "${GREEN}✓ IP diperbarui ke: ${NEW_IP}${NC}"
    echo -e "${GREEN}✓ Load time akan kembali normal (< 5 detik)${NC}"
else
    echo -e "${RED}✗ Restart gagal!${NC}"
fi

# 8. Cek logs terakhir
echo -e "\n${YELLOW}--- Logs Jenkins (10 baris terakhir) ---${NC}"
docker logs ${CONTAINER_NAME} --tail=10