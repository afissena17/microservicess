#!/bin/bash

# ==========================================
# JENKINS IP UPDATER (MASTER NODE VERSION)
# ==========================================

CONFIG_FILE="/var/jenkins_home/jenkins.model.JenkinsLocationConfiguration.xml"
LABEL_SELECTOR="app=jenkins" 

# Warna output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Jenkins URL Updater (Master Node IP) ===${NC}"

# 1. Deteksi IP Public Master Node (Tempat script ini dijalankan)
echo -ne "Mendeteksi IP Public Master... "
MASTER_IP=$(curl -s http://checkip.amazonaws.com)

if [ -z "$MASTER_IP" ]; then
    echo -e "${RED}GAGAL!${NC}"
    echo "Cek koneksi internet Anda."
    exit 1
fi
echo -e "${GREEN}${MASTER_IP}${NC}"

# 2. Cari Pod Jenkins
echo -ne "Mencari Pod Jenkins... "
POD_NAME=$(kubectl get pods -l ${LABEL_SELECTOR} -o jsonpath="{.items[0].metadata.name}")

if [ -z "$POD_NAME" ]; then
    echo -e "${RED}TIDAK DITEMUKAN!${NC}"
    echo "Pastikan label 'app=jenkins' benar dan pod sedang running."
    exit 1
fi
echo -e "${GREEN}${POD_NAME}${NC}"

# 3. Input Port dari User
echo ""
echo -e "${YELLOW}Masukkan NodePort Jenkins Anda.${NC}"
echo "Biasanya port 30000 (sesuai jenkins.yaml Anda)."
read -p "Port: " USER_PORT

# Validasi input port (harus angka)
if ! [[ "$USER_PORT" =~ ^[0-9]+$ ]]; then
    echo -e "${RED}Error: Port harus berupa angka!${NC}"
    exit 1
fi

NEW_URL="http://${MASTER_IP}:${USER_PORT}/"

# 4. Eksekusi Update Config
echo -e "\n${YELLOW}Mengupdate konfigurasi Jenkins...${NC}"
echo "Target URL: ${NEW_URL}"

# Kita pakai 'sed' langsung di dalam container biar cepat
# Mengubah tag <jenkinsUrl>...</jenkinsUrl>
kubectl exec ${POD_NAME} -- sed -i "s|<jenkinsUrl>.*</jenkinsUrl>|<jenkinsUrl>${NEW_URL}</jenkinsUrl>|" ${CONFIG_FILE}

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Konfigurasi XML berhasil diubah.${NC}"
else
    echo -e "${RED}✗ Gagal mengubah file config di dalam pod.${NC}"
    exit 1
fi

# 5. Restart Pod (Delete agar diganti baru oleh Deployment)
echo -e "\n${YELLOW}Me-restart Pod Jenkins untuk menerapkan perubahan...${NC}"
kubectl delete pod ${POD_NAME}

echo -e "\n${GREEN}=== SELESAI ===${NC}"
echo -e "Tunggu beberapa detik, lalu akses: ${YELLOW}${NEW_URL}${NC}"