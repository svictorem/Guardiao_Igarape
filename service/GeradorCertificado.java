package service;

import java.util.List;
import model.AcaoAmbiental;
import model.Voluntario;

public interface GeradorCertificado {
    public String gerarCertificado(Voluntario voluntario, List<AcaoAmbiental> acoesAmbientais, double cargaHorariaTotal);
}