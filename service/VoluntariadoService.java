package service;

import dao.AcaoAmbientalDAO;
import dao.ParticipacaoDAO;
import dao.VoluntarioDAO;
import exception.CargaHorariaInsuficienteException;
import model.AcaoAmbiental;
import model.Participacao;
import model.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class VoluntariadoService {
    private static final double HORAS_MINIMAS_CERTIFICADO = 10.0;
    
    private VoluntarioDAO voluntarioDAO;
    private AcaoAmbientalDAO acaoAmbientalDAO;
    private ParticipacaoDAO participacaoDAO;
    private GeradorCertificado geradorCertificado;

    public VoluntariadoService(GeradorCertificado geradorCertificado) {
        this.voluntarioDAO = new VoluntarioDAO();
        this.acaoAmbientalDAO = new AcaoAmbientalDAO();
        this.participacaoDAO = new ParticipacaoDAO();
        this.geradorCertificado = geradorCertificado;
    }

    public void cadastrarVoluntario(Voluntario voluntario) {
        voluntarioDAO.cadastrar(voluntario);
    }

    public void cadastrarAcaoAmbiental(AcaoAmbiental acaoAmbiental) {
        acaoAmbientalDAO.cadastrar(acaoAmbiental);
    }

    public void registrarParticipacao(Voluntario voluntario, AcaoAmbiental acaoAmbiental) {
        participacaoDAO.cadastrar(voluntario, acaoAmbiental);
    }

    public Voluntario buscarVoluntarioPorNome(String nome) {
        return voluntarioDAO.buscarPorNome(nome);
    }

    public List<Voluntario> getVoluntarios() {
        return voluntarioDAO.listarTodos();
    }

    public List<AcaoAmbiental> getAcoesAmbientais() {
        return acaoAmbientalDAO.listarTodas();
    }
    
    public void atualizarVoluntario(Voluntario voluntario) {
        voluntarioDAO.atualizar(voluntario);
    }
    
    public void excluirVoluntario(int id) {
        voluntarioDAO.remover(id);
    }
    
    public void atualizarAcaoAmbiental(AcaoAmbiental acao) {
        acaoAmbientalDAO.atualizar(acao);
    }
    
    public void excluirAcaoAmbiental(int id) {
        acaoAmbientalDAO.remover(id);
    }

    public double calcularTotalHoras(Voluntario voluntario) {
        double totalHoras = 0.0;
        List<Participacao> participacoes = participacaoDAO.listarPorVoluntario(voluntario.getId());
        for (Participacao p : participacoes) {
            if (p.getAcaoAmbiental() != null) {
                totalHoras += p.getAcaoAmbiental().getDuracaoHoras();
            }
        }
        return totalHoras;
    }

    public String emitirCertificado(Voluntario voluntario) throws CargaHorariaInsuficienteException {
        double cargaHorariaTotal = calcularTotalHoras(voluntario);
        if (cargaHorariaTotal < HORAS_MINIMAS_CERTIFICADO) {
            throw new CargaHorariaInsuficienteException("Carga horária insuficiente para emissão do certificado. Mínimo: " + HORAS_MINIMAS_CERTIFICADO);
        }
        
        List<Participacao> participacoes = participacaoDAO.listarPorVoluntario(voluntario.getId());
        List<AcaoAmbiental> acoes = new ArrayList<>();
        for (Participacao p : participacoes) {
            acoes.add(p.getAcaoAmbiental());
        }
        
        try {
            return geradorCertificado.gerarCertificado(voluntario, acoes, cargaHorariaTotal);
        } catch (Exception e) {
            return "Erro ao gerar certificado: " + e.getMessage();
        }
    }
}
