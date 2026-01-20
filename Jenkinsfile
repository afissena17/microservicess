pipeline {
    agent any

    tools {
        maven 'maven3' 
        jdk 'jdk17'
        // Opsional: Jika menggunakan docker tool, uncomment baris bawah
        // docker 'docker' 
    }

    environment {
        // Ganti dengan username Docker Hub Anda
        DOCKER_HUB_USER = 'itssena' 
        // ID Credentials yang disimpan di Jenkins (Dashboard -> Manage Jenkins -> Credentials)
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build & Push Parallel') {
            parallel {
                stage('Service Buku') {
                    steps {
                        script {
                            buildAndPushService('perpustakaan/buku', 'buku')
                        }
                    }
                }
                stage('Service Anggota') {
                    steps {
                        script {
                            buildAndPushService('perpustakaan/anggota', 'anggota')
                        }
                    }
                }
                stage('Service Peminjaman') {
                    steps {
                        script {
                            buildAndPushService('perpustakaan/peminjaman', 'peminjaman')
                        }
                    }
                }
                stage('Service Pengembalian') {
                    steps {
                        script {
                            buildAndPushService('perpustakaan/pengembalian', 'pengembalian')
                        }
                    }
                }
                stage('Perpustakaan API Gateway') {
                    steps {
                        script {
                            // Nama image disesuaikan dengan docker-compose: itssena/perpustakaan-gateway
                            buildAndPushService('perpustakaan/api-gateway', 'perpustakaan-gateway')
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline berhasil! Semua 5 service (termasuk API Gateway) telah di-push ke Docker Hub.'
        }
        failure {
            echo 'Pipeline gagal. Silakan cek logs.'
        }
    }
}

// Helper Function untuk Build & Push
def buildAndPushService(String dirPath, String imageName) {
    dir(dirPath) {
        echo "=== Building ${imageName} ==="
        // 1. Build JAR
        // Skip tests untuk mempercepat, hapus '-DskipTests' jika ingin menjalankan test
        bat 'mvn clean package -DskipTests'

        // 2. Build Docker Image
        echo "=== Docker Build ${imageName} ==="
        script {
            def imageTag = "${env.DOCKER_HUB_USER}/${imageName}:latest"
            
            // Build image
            bat "docker build -t ${imageTag} ."

            // 3. Login & Push ke Docker Hub
            echo "=== Pushing to Docker Hub ==="
            withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                // Login (gunakan --password-stdin untuk keamanan)
                bat "echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin"
                // Push
                bat "docker push ${imageTag}"
                // Logout cleanup
                bat "docker logout"
            }
        }
    }
}
