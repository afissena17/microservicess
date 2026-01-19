#!/bin/bash

echo "🗑️  Deleting all services..."
kubectl delete -f all-services-k8s.yaml

echo "📥 Pulling latest changes..."
cd ..
git pull

echo "🚀 Applying all services..."
cd kubernetes-cluster
kubectl apply -f all-services-k8s.yaml

echo "✅ Done! Monitoring pods..."
kubectl get pods -w
