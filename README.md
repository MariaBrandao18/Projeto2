# 🌍 Agência de Viagens e Turismo

## 📖 Manual de Utilização do Sistema

### ✨ Apresentação

A **Agência de Viagens e Turismo** disponibiliza um sistema de controle de dados com foco no gerenciamento e cadastro de **clientes** e **pacotes de viagens**. Além de relacionar clientes a pacotes turísticos, o sistema também oferece a opção de contratação de **serviços adicionais**.

---

### 🧩 Funcionalidades do Sistema

#### 👤 Usuário (Cliente)

Ao acessar o sistema, o cliente é apresentado a uma interface gráfica onde realiza seu cadastro, informando os seguintes dados:

- Nome completo  
- Data de nascimento  
- Telefone  
- E-mail  
- Tipo de documento:  
  - **CPF** para clientes nacionais  
  - **Passaporte** para clientes estrangeiros  

Após o cadastro, o cliente pode escolher entre diversos **pacotes de viagens**, com as seguintes informações:

- Nome do pacote  
- Destino  
- Duração  
- Preço  
- Tipo do pacote:  
  - 🏨 **Luxuoso**: mais comodidade, viagem de primeira classe e motoristas inclusos
  - 🏕️ **Aventura**: inclui trilhas e passeios de jipe 
  - 🏛️ **Cultural**: visitas a museus, centros históricos e experiências gastronômicas

O cliente pode escolher **mais de um pacote**, e também optar por **serviços adicionais** (não obrigatórios), como:

- 🚐 Traslado  
- 🗺️ Passeios  
- 🚘 Motorista particular  
- 🚗 Aluguel de carro  

> 💡 *Os valores dos serviços adicionais variam conforme a escolha do usuário.*

---

#### 🛠️ Gerência

A área administrativa do sistema oferece funcionalidades para a **gestão de dados**, como:

- **Listar, buscar e excluir** clientes e pacotes  
  - ❗ Pacotes **só podem ser excluídos** se **não houver clientes associados**
  - ✅ Para listar pacotes, é necessário que o cadastro contenha **preço** e **destino**

- **Análise de dados**:
  - 🔍 Buscar clientes específicos e visualizar seus dados e pacotes adquiridos  
  - 📦 Ver todos os clientes cadastrados em determinado pacote  

> 📋 *Todos os dados são organizados em listas e arranjos, facilitando a busca e a análise de informações.*

---

### ⚙️ Requisitos do Sistema

- 🖥️ **Linguagem**: Java 11 (Programação Orientada a Objetos)  
- 💾 **Banco de Dados**: MySQL  
- 🖼️ **Interface Gráfica (GUI)**: Utiliza os pacotes `javax.swing` e `java.awt`
- 📚 **Bibliotecas Importadas**: `mysql-connector` e `protobuf-java`

---
