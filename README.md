# Guardião Igarapé

O projeto **Guardião Igarapé** é um Sistema de Voluntariado Ambiental desenvolvido em Java. Ele permite gerenciar voluntários, registrar diferentes tipos de ações ambientais (Educação Ambiental, Limpeza e Plantio), controlar a participação dos voluntários nessas atividades e emitir certificados. O sistema funciona através de uma interface de linha de comando (CLI) interativa e amigável.

## Funcionalidades

- **Gerenciamento de Voluntários:** Cadastrar, listar, atualizar e excluir voluntários.
- **Ações Ambientais:** Cadastrar, listar, atualizar e excluir ações específicas (Educação Ambiental, Limpeza, Plantio).
- **Participação:** Registrar a presença e participação de voluntários em ações específicas.
- **Certificados:** Emissão de certificados de participação no voluntariado.
- **Relatórios:** Geração de relatórios de carga horária acumulada por voluntário e quantidade de ações por tipo.

## Requisitos Técnicos

- **Java Development Kit (JDK):** Versão 8 ou superior.
- **Ambiente:** Terminal, Console ou Prompt de Comando para execução.

## Instruções de Execução

Siga os passos abaixo para compilar e executar o sistema localmente:

1. Abra o seu terminal e navegue até o diretório raiz do projeto:
   ```bash
   cd caminho/para/Guardiao_Igarape
   ```

2. Crie um diretório para armazenar os binários compilados (opcional, mas recomendado):
   ```bash
   mkdir bin
   ```

3. Compile todos os arquivos `.java` do projeto:
   ```bash
   javac -d bin dao/*.java exception/*.java model/*.java service/*.java main/*.java
   ```
   *(Nota para Windows: Dependendo do terminal, você pode precisar compilar listando os arquivos ou usando o comando `dir /s /B *.java > sources.txt` seguido de `javac -d bin @sources.txt`)*

4. Execute a classe principal do sistema:
   ```bash
   java -cp bin main.Main
   ```

5. O menu interativo aparecerá no terminal. Basta digitar o número da opção desejada e pressionar Enter.

## Integrantes
- **Ana Kelley (Líder)** - anakelleycarril@gmail.com
- **Elano Serrão** - serraoelano7@gmail.com
- **Izhac Nylton** - izhac2006@gmail.com
- **Victor Emanuel** - victoroliveira280780@gmail.com
