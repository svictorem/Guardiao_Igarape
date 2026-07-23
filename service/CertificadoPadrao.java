package service;

import java.util.List;
import model.AcaoAmbiental;
import model.Voluntario;

public class CertificadoPadrao implements GeradorCertificado {

    @Override
    public String gerarCertificado(Voluntario voluntario, List<AcaoAmbiental> acoesAmbientais, double cargaHorariaTotal) {
        StringBuilder certificado = new StringBuilder();
        certificado.append("Certificado de Participação\n");
        certificado.append("Nome do Voluntário: ").append(voluntario.getNome()).append("\n");
        certificado.append("Telefone: ").append(voluntario.getTelefone()).append("\n");
        certificado.append("Carga Horária Total: ").append(cargaHorariaTotal).append(" horas\n");
        certificado.append("Ações Ambientais Participadas:\n");

        for (AcaoAmbiental acao : acoesAmbientais) {
            certificado.append("- Local: ").append(acao.getLocal())
                       .append(", Data: ").append(acao.getData())
                       .append(", Duração: ").append(acao.getDuracaoHoras()).append(" horas\n");
        }

        return certificado.toString();
    }
}