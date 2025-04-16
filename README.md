# ⚖️ Sistema de Gestão Jurídica - Tribunal Virtual

Sistema em Java para gerenciar advogados, réus e casos jurídicos, permitindo o registro de advogados em processos com base na especialidade e consistência lógica entre entidades. Este sistema simula operações típicas de um tribunal digital, com foco na integridade dos dados e experiência do usuário via terminal.

---

## 📚 Índice

- [📌 Sobre o Projeto](#-sobre-o-projeto)
- [🚀 Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [⚙️ Como Executar](#️-como-executar)
- [📂 Estrutura de Diretórios](#-estrutura-de-diretórios)
- [📈 Melhorias Futuras](#-melhorias-futuras)
- [🤝 Contribuição](#-contribuição)
- [📄 Licença](#-licença)

---

## 📌 Sobre o Projeto

Este projeto foi desenvolvido com o objetivo de simular o funcionamento de um sistema jurídico digital. Os usuários podem cadastrar advogados, clientes (réus), e associar advogados a casos jurídicos, respeitando a compatibilidade entre especialidades e tipos de caso.

Um dos principais focos do sistema é evitar inconsistências, como a remoção indevida de um cliente de um advogado quando ainda existem vínculos ativos com outros processos.

---

## 🚀 Funcionalidades

- ✅ Cadastro de advogados e clientes
- ✅ Registro de novos casos jurídicos
- ✅ Associação entre advogado e caso (com validação de especialidade)
- ✅ Exibição detalhada de dados (advogado, cliente, processo)
- ✅ Troca de advogado responsável pelo caso
- ✅ Prevenção de remoção indevida de clientes de advogados
- ✅ Validações completas com tratamento de exceções personalizadas

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Paradigma Orientado a Objetos (POO)**
- **Coleções Java (List, Map, Stream API)**
- **Tratamento de Exceções Personalizadas**
- **Stream API**
- **Entrada via Terminal (Scanner)**

---

## ⚙️ Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd https://github.com/guigokw/sistema-juridico.git

2.   Abra o projeto em sua IDE Java favorita (ex: IntelliJ, Eclipse, VSCode com extensão Java).

3. Compile e execute a classe principal, que inicia o sistema interativo no terminal.

## 📁 estrutura de diretorio

📁 src/
│
├── exceptions/
│   ├── AdvogadoNaoEncontradoException.java
│   └── ProcessoNaoEncontradoException.java
│
├── modelos/
│   ├── Advogado.java
│   ├── Cliente.java
│   └── CasoJuridico.java
│
├── sistema/
│   ├── SistemaTribunal.java
│   └── Main.java

## 📈 Melhorias Futuras

- 🔄 Interface gráfica com JavaFX ou Swing

- 🔗 Persistência com banco de dados (JDBC ou Hibernate)

- 🌐 API REST para integração com outros sistemas

- 📊 Relatórios e filtros por tipo de caso, advogado, status, etc.

- 🧪 Testes unitários com JUnit
  

  ## 🤝 Contribuição
Sinta-se à vontade para contribuir! Toda ajuda é bem-vinda. Para contribuir:

Faça um fork do projeto

Crie uma nova branch (git checkout -b feature/nova-funcionalidade)

Commit suas alterações (git commit -m 'feat: nova funcionalidade')

Push para a branch (git push origin feature/nova-funcionalidade)

Abra um Pull Request

## 📄 Licença
Este projeto está sob a licença MIT.
Sinta-se livre para usar, modificar e distribuir conforme os termos da licença.

👨‍💻 Autor
Desenvolvido com 💻 e ☕ por Luiz guilherme carvalho ribeiro

🔗 [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luiz-guilherme-carvalho-ribeiro-12032829b/) • 🐙 
https://github.com/guigokw
