FROM mcr.microsoft.com/devcontainers/base:ubuntu

# Instalacja zależności
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    apt-transport-https \
    gnupg2 \
    ca-certificates \
    lsb-release \
    software-properties-common \
    && rm -rf /var/lib/apt/lists/*

# Instalacja kind (najnowsza wersja)
RUN curl -Lo ./kind "https://kind.sigs.k8s.io/dl/v0.22.0/kind-$(uname -s | tr '[:upper:]' '[:lower:]')-$(uname -m | sed 's/x86_64/amd64/')" && \
    chmod +x ./kind && \
    mv ./kind /usr/local/bin/kind

# Instalacja kubectl (najnowsza stabilna wersja)
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    chmod +x kubectl && \
    mv kubectl /usr/local/bin/ && \
    kubectl version --client

# Instalacja Helm
RUN curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash && \
    helm version
