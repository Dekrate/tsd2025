#!/usr/bin/env bash
set -e
# 1) Uruchomienie klastra kind
kind create cluster --name tutorial --wait 60s

# 2) Weryfikacja
kubectl cluster-info

# 3) Utworzenie namespace
kubectl create namespace tutorial || true

echo "🎉 Klaster gotowy! Przełącz się na namespace 'tutorial':"
echo "   kubectl config set-context --current --namespace=tutorial"

