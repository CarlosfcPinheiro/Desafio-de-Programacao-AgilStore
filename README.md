# Gerenciamento de Produtos para a Loja AgilStore
O repositório possui como objetivo armazenar a solução para um Desafio de Programação, desenvolvendo um script em Java para automatizar o processo de **Gerenciamento de Produtos para a Loja AgilStore**.

## ⚙️ Tecnologias utilizadas
- Java (Linguagem de programação)
- Maven (Gerenciador de pacotes/dependências)

## 🔧 Como funciona?
O programa possui 3 classes principais:
- com.agilstore.Produto (representando a entidade de um produto)
- Estoque (representando um conjunto de produtos)
- com.agilstore.Main (fluxo principal do programa)

## 🏠 Como rodar localmente?
A seguir, seguem as instruções de como rodar o programa localmente.

### Requisitos
- Java 21 (preferencialmente)
- Maven
- Git

### Instruções
1. No terminal, clone o repositório e entre no diretório:
```bash
git clone https://github.com/CarlosfcPinheiro/Desafio-de-Programacao-AgilStore.git
cd Desafio-de-Programacao-AgilStore
```
2. Compile o projeto através do Maven
```bash
mvn clean compile
```
3. Execute o programa
```bash
mvn exec:java
```

Com isso será possível executar o programa!