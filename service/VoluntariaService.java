package service;

import java.util.List;
import model.AcaoAmbiental;
import model.Participacao;
import model.Voluntario;

public class VoluntariaService {
    private static final double CARGA_HORARIA_MINIMA = 10.0; // Exemplo de carga horária mínima exigida
    private List<Voluntario> voluntarios;
    private List<AcaoAmbiental> acoesAmbientais;
    private List<Participacao> participacoes;
    private GeradorCertificado geradorCertificado;

    public VoluntariaService(List<Voluntario> voluntarios, List<AcaoAmbiental> acoesAmbientais, List<Participacao> participacoes, GeradorCertificado geradorCertificado) {
        this.voluntarios = voluntarios;
        this.acoesAmbientais = acoesAmbientais;
        this.participacoes = participacoes;
        this.geradorCertificado = geradorCertificado;
    }

    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public List<AcaoAmbiental> getAcoesAmbientais() {
        return acoesAmbientais;
    }

    public void setAcoesAmbientais(List<AcaoAmbiental> acoesAmbientais) {
        this.acoesAmbientais = acoesAmbientais;
    }

    public List<Participacao> getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(List<Participacao> participacoes) {
        this.participacoes = participacoes;
    }

    public GeradorCertificado getGeradorCertificado() {
        return geradorCertificado;
    }

    public void setGeradorCertificado(GeradorCertificado geradorCertificado) {
        this.geradorCertificado = geradorCertificado;
    }

    public void cadastrarVoluntario(Voluntario voluntario) {
        voluntarios.add(voluntario);
    }

    public void cadastrarAcaoAmbiental(AcaoAmbiental acaoAmbiental) {
        acoesAmbientais.add(acaoAmbiental);
    }

    public void registrarParticipacao(Voluntario voluntario, AcaoAmbiental acaoAmbiental) {
        Participacao participacao = new Participacao(voluntario, acaoAmbiental);
        participacoes.add(participacao);
    }

    public Voluntario buscarVoluntarioPorNome(String nome) {
        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getNome().equalsIgnoreCase(nome)) {
                return voluntario;
            }
        }
        return null; // Retorna null se o voluntário não for encontrado
    }

    public double calcularCargaHorariaTotal(Voluntario voluntario) {
        double cargaHorariaTotal = 0.0;
        for (Participacao participacao : participacoes) {
            if (participacao.getVoluntario().equals(voluntario)) {
                cargaHorariaTotal += participacao.getAcaoAmbiental().getDuracaoHoras();
            }
        }
        return cargaHorariaTotal;
    }

    public String emitirCertificado(Voluntario voluntario) {
        double cargaHorariaTotal = calcularCargaHorariaTotal(voluntario);
        if (cargaHorariaTotal < CARGA_HORARIA_MINIMA) {
            return "Carga horária insuficiente para emissão do certificado.";
        }
        try {
            return geradorCertificado.gerarCertificado(voluntario, acoesAmbientais, cargaHorariaTotal);
        } catch (Exception e) {
            return "Erro ao gerar certificado: " + e.getMessage();
        }
    }

}