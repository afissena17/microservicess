```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.2/deploy/static/provider/baremetal/deploy.yaml
```
setup agar ingress lisent port 80/443 dan ingress menetap di master node
```bash
kubectl patch deployment ingress-nginx-controller -n ingress-nginx --patch '
spec:
  template:
    spec:
      hostNetwork: true
      nodeSelector:
        ingress-ready: "true"
      tolerations:
      - key: "node-role.kubernetes.io/control-plane"
        operator: "Exists"
        effect: "NoSchedule"
      - key: "node-role.kubernetes.io/master"
        operator: "Exists"
        effect: "NoSchedule"
'
```
setup cert-manage untuk automasi sertifikat pada domain
```bash
kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.13.3/cert-manager.yaml
```

konfigurasi ingress di kube master
```bash
kubectl label nodes <NAMA-NODE-MASTER> ingress-ready=true --overwrite
```
