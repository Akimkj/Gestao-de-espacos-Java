# Gestão de Espaços

## Descrição

Este sistema foi desenvolvido como parte de um projeto para a disciplina de **Linguagem de Programação II**. O objetivo do sistema é permitir o cadastro, edição, listagem e remoção de espaços físicos em uma organização, como salas, auditórios ou laboratórios, registrando também logs das ações executadas pelos usuários.

O projeto é implementado em **Java**, utilizando os princípios de orientação a objetos, com persistência de dados feita por arquivos `.txt`.

---

## Como Executar

- **Java JDK** (versão 11 ou superior)  
- **Git** (para clonar o repositório)  
- **IDE Java** recomendada: IntelliJ IDEA, Eclipse, NetBeans ou VSCode com extensão Java  

1. Clone o repositório:
```bash
git clone https://github.com/Akimkj/Gestao-de-espacos-Java
cd Gestao-de-espacos-Java
```

2. Compile e execute o projeto:
   - A partir de uma IDE: importe o projeto como projeto Java.
   - Ou pelo terminal:
```bash
javac App.java
java App
```

> As classes estão organizadas nas pastas `models`, `controller`, `persistence` e `view`.

---

## Estrutura do Código

- `App.java`: Ponto de entrada do programa.
- `models/`: Contém as classes de domínio (`Espaco`, `Usuario`, `Log`, etc.).
- `controller/`: Controladores que lidam com a lógica de negócio (`EspacoController`, `UsuarioController`, `RelatorioController`).
- `persistence/`: Camada de persistência que realiza leitura e escrita dos dados (`EspacoDAO`, `UsuarioDAO`, `LogDAO`).
- `view/`: Contém as classes responsáveis pela interface textual com o usuário (`MenuPrincipal`, `EspacoView`, `RelatoriosView`, etc.).
- `data/`: onde são salvos os arquivos `.txt` com os dados persistidos.

---

## Funcionalidades do Sistema

- **Login de usuário** com verificação.
- **Cadastro de espaços** com nome, capacidade e descrição.
- **Edição e remoção de espaços existentes.**
- **Registro de logs** com data, hora, ação executada e usuário responsável.
- **Relatórios** de logs e espaços salvos.
- **Persistência de dados** em arquivos `.txt`.

---

## Classes Principais

### `Espaco`
Responsável por representar um espaço físico.
```java
public class Espaco {
    private int id;
    private String nome;
    private String descricao;
    private int capacidade;
}
```

### `Usuario`
Representa um usuário do sistema, que pode ser administrador.
```java
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean ehADM;
}
```

### `Log`
Registra ações importantes do sistema.
```java
public class Log {
    private Date dataHora;
    private String usuario;
    private String acao;
}
```

---

## Exemplo de Interação com o Sistema

```plaintext
===== Sistema de Gestão de Espaços =====
Usuário: admin
Senha: ******

Login realizado com sucesso!

----- Menu Principal -----
1 - Cadastrar Espaço
2 - Listar Espaços
3 - Editar Espaço
4 - Remover Espaço
5 - Relatórios (Logs)
0 - Sair

Opção: 1

--- Cadastro de Espaço ---
Nome: Sala de Reuniões
Capacidade: 20
Descrição: Sala com projetor e ar-condicionado.

Espaço cadastrado com sucesso!

[LOG] 2025-07-31 21:45 | admin | Cadastrou espaço: Sala de Reuniões (Capacidade: 20)
```

---

## Autores

Este projeto foi desenvolvido por:  
**Diogo Costa**, **Gabriel Serra**, **Marina Veiga** e **Mika Marques**
