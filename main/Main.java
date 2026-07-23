package main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.AcaoAmbiental;
import model.EducacaoAmbiental;
import model.Limpeza;
import model.Participacao;
import model.Plantio;
import model.Voluntario;
import service.CertificadoPadrao;
import service.GeradorCertificado;
import service.VoluntariaService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Voluntario> voluntarios = new ArrayList<>();
        List<AcaoAmbiental> acoesAmbientais = new ArrayList<>();
        List<Participacao> participacoes = new ArrayList<>();
        GeradorCertificado geradorCertificado = new CertificadoPadrao();
        VoluntariaService service = new VoluntariaService(voluntarios, acoesAmbientais, participacoes, geradorCertificado);

        int opcao;
        do {
            System.out.println("\n=== Sistema de Voluntariado Ambiental ===");
            System.out.println("1 - Cadastrar voluntário");
            System.out.println("2 - Listar voluntários");
            System.out.println("3 - Atualizar voluntário");
            System.out.println("4 - Excluir voluntário");
            System.out.println("5 - Cadastrar ação ambiental");
            System.out.println("6 - Listar ações ambientais");
            System.out.println("7 - Atualizar ação ambiental");
            System.out.println("8 - Excluir ação ambiental");
            System.out.println("9 - Registrar participação");
            System.out.println("10 - Emitir certificado");
            System.out.println("11 - Relatório de carga horária");
            System.out.println("12 - Relatório de ações por tipo");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarVoluntario(service);
                    break;
                case 2:
                    listarVoluntarios(service);
                    break;
                case 3:
                    atualizarVoluntario(service);
                    break;
                case 4:
                    excluirVoluntario(service);
                    break;
                case 5:
                    cadastrarAcaoAmbiental(service);
                    break;
                case 6:
                    listarAcoesAmbientais(service);
                    break;
                case 7:
                    atualizarAcaoAmbiental(service);
                    break;
                case 8:
                    excluirAcaoAmbiental(service);
                    break;
                case 9:
                    registrarParticipacao(service);
                    break;
                case 10:
                    emitirCertificado(service);
                    break;
                case 11:
                    relatorioCargaHoraria(service);
                    break;
                case 12:
                    relatorioAcoesPorTipo(service);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarVoluntario(VoluntariaService service) {
        String nome = lerTexto("Nome do voluntário: ");
        int telefone = lerInteiro("Telefone: ");
        Voluntario voluntario = new Voluntario(nome, telefone);
        service.cadastrarVoluntario(voluntario);
        System.out.println("Voluntário cadastrado com sucesso!");
    }

    private static void listarVoluntarios(VoluntariaService service) {
        List<Voluntario> voluntarios = service.getVoluntarios();
        if (voluntarios.isEmpty()) {
            System.out.println("Nenhum voluntário cadastrado.");
            return;
        }

        System.out.println("\nVoluntários cadastrados:");
        for (int i = 0; i < voluntarios.size(); i++) {
            Voluntario voluntario = voluntarios.get(i);
            System.out.println((i + 1) + " - " + voluntario.getNome() + " | Telefone: " + voluntario.getTelefone());
        }
    }

    private static void atualizarVoluntario(VoluntariaService service) {
        String nomeAtual = lerTexto("Nome do voluntário a atualizar: ");
        Voluntario voluntario = service.buscarVoluntarioPorNome(nomeAtual);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        String novoNome = lerTexto("Novo nome: ");
        int novoTelefone = lerInteiro("Novo telefone: ");
        voluntario.setNome(novoNome);
        voluntario.setTelefone(novoTelefone);
        System.out.println("Voluntário atualizado com sucesso!");
    }

    private static void excluirVoluntario(VoluntariaService service) {
        String nome = lerTexto("Nome do voluntário a excluir: ");
        List<Voluntario> voluntarios = service.getVoluntarios();
        List<Participacao> participacoes = service.getParticipacoes();

        boolean removido = voluntarios.removeIf(voluntario -> voluntario.getNome().equalsIgnoreCase(nome));
        participacoes.removeIf(participacao -> participacao.getVoluntario().getNome().equalsIgnoreCase(nome));

        if (removido) {
            System.out.println("Voluntário excluído com sucesso!");
        } else {
            System.out.println("Voluntário não encontrado.");
        }
    }

    private static void cadastrarAcaoAmbiental(VoluntariaService service) {
        System.out.println("Escolha o tipo de ação ambiental:");
        System.out.println("1 - Educação Ambiental");
        System.out.println("2 - Limpeza");
        System.out.println("3 - Plantio");
        int tipo = lerInteiro("Tipo: ");

        String local = lerTexto("Local: ");
        LocalDate data = lerData("Data (yyyy-MM-dd): ");
        double duracaoHoras = lerDouble("Duração em horas: ");

        AcaoAmbiental acao;
        switch (tipo) {
            case 1:
                int numeroParticipantes = lerInteiro("Número de participantes: ");
                acao = new EducacaoAmbiental(local, data, duracaoHoras, numeroParticipantes);
                break;
            case 2:
                int quantidadeLixo = lerInteiro("Quantidade de lixo coletado: ");
                acao = new Limpeza(local, data, duracaoHoras, quantidadeLixo);
                break;
            case 3:
                int quantidadeArvores = lerInteiro("Quantidade de árvores plantadas: ");
                acao = new Plantio(local, data, duracaoHoras, quantidadeArvores);
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        service.cadastrarAcaoAmbiental(acao);
        System.out.println("Ação ambiental cadastrada com sucesso!");
    }

    private static void listarAcoesAmbientais(VoluntariaService service) {
        List<AcaoAmbiental> acoes = service.getAcoesAmbientais();
        if (acoes.isEmpty()) {
            System.out.println("Nenhuma ação ambiental cadastrada.");
            return;
        }

        System.out.println("\nAções ambientais cadastradas:");
        for (int i = 0; i < acoes.size(); i++) {
            AcaoAmbiental acao = acoes.get(i);
            System.out.println((i + 1) + " - " + acao.getClass().getSimpleName() + " | Local: " + acao.getLocal()
                    + " | Data: " + acao.getData() + " | Duração: " + acao.getDuracaoHoras() + "h");
        }
    }

    private static void atualizarAcaoAmbiental(VoluntariaService service) {
        List<AcaoAmbiental> acoes = service.getAcoesAmbientais();
        if (acoes.isEmpty()) {
            System.out.println("Nenhuma ação ambiental cadastrada.");
            return;
        }

        listarAcoesAmbientais(service);
        int indice = lerInteiro("Informe o número da ação a atualizar: ") - 1;
        if (indice < 0 || indice >= acoes.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        AcaoAmbiental acao = acoes.get(indice);
        String novoLocal = lerTexto("Novo local: ");
        LocalDate novaData = lerData("Nova data (yyyy-MM-dd): ");
        double novaDuracao = lerDouble("Nova duração em horas: ");

        acao.setLocal(novoLocal);
        acao.setData(novaData);
        acao.setDuracaoHoras(novaDuracao);

        if (acao instanceof EducacaoAmbiental) {
            int novoNumeroParticipantes = lerInteiro("Novo número de participantes: ");
            ((EducacaoAmbiental) acao).setNumeroParticipantes(novoNumeroParticipantes);
        } else if (acao instanceof Limpeza) {
            int novaQuantidadeLixo = lerInteiro("Nova quantidade de lixo coletado: ");
            ((Limpeza) acao).setQuantidadeLixoColetado(novaQuantidadeLixo);
        } else if (acao instanceof Plantio) {
            int novaQuantidadeArvores = lerInteiro("Nova quantidade de árvores plantadas: ");
            ((Plantio) acao).setQuantidadeArvoresPlantadas(novaQuantidadeArvores);
        }

        System.out.println("Ação ambiental atualizada com sucesso!");
    }

    private static void excluirAcaoAmbiental(VoluntariaService service) {
        List<AcaoAmbiental> acoes = service.getAcoesAmbientais();
        if (acoes.isEmpty()) {
            System.out.println("Nenhuma ação ambiental cadastrada.");
            return;
        }

        listarAcoesAmbientais(service);
        int indice = lerInteiro("Informe o número da ação a excluir: ") - 1;
        if (indice < 0 || indice >= acoes.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        acoes.remove(indice);
        System.out.println("Ação ambiental excluída com sucesso!");
    }

    private static void registrarParticipacao(VoluntariaService service) {
        String nomeVoluntario = lerTexto("Nome do voluntário: ");
        Voluntario voluntario = service.buscarVoluntarioPorNome(nomeVoluntario);
        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        List<AcaoAmbiental> acoes = service.getAcoesAmbientais();
        if (acoes.isEmpty()) {
            System.out.println("Nenhuma ação ambiental cadastrada.");
            return;
        }

        listarAcoesAmbientais(service);
        int indice = lerInteiro("Informe o número da ação: ") - 1;
        if (indice < 0 || indice >= acoes.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        service.registrarParticipacao(voluntario, acoes.get(indice));
        System.out.println("Participação registrada com sucesso!");
    }

    private static void emitirCertificado(VoluntariaService service) {
        String nomeVoluntario = lerTexto("Nome do voluntário: ");
        Voluntario voluntario = service.buscarVoluntarioPorNome(nomeVoluntario);
        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        String certificado = service.emitirCertificado(voluntario);
        System.out.println(certificado);
    }

    private static void relatorioCargaHoraria(VoluntariaService service) {
        if (service.getVoluntarios().isEmpty()) {
            System.out.println("Nenhum voluntário cadastrado.");
            return;
        }

        System.out.println("\nRelatório de carga horária:");
        for (Voluntario voluntario : service.getVoluntarios()) {
            double total = service.calcularCargaHorariaTotal(voluntario);
            System.out.println(voluntario.getNome() + " - " + total + " horas");
        }
    }

    private static void relatorioAcoesPorTipo(VoluntariaService service) {
        int educacao = 0;
        int limpeza = 0;
        int plantio = 0;

        for (AcaoAmbiental acao : service.getAcoesAmbientais()) {
            if (acao instanceof EducacaoAmbiental) {
                educacao++;
            } else if (acao instanceof Limpeza) {
                limpeza++;
            } else if (acao instanceof Plantio) {
                plantio++;
            }
        }

        System.out.println("\nRelatório de ações por tipo:");
        System.out.println("Educação Ambiental: " + educacao);
        System.out.println("Limpeza: " + limpeza);
        System.out.println("Plantio: " + plantio);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número.");
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato yyyy-MM-dd.");
            }
        }
    }
}
