package model;

import java.time.LocalDate;

public class EducacaoAmbiental extends AcaoAmbiental {
    private int numeroParticipantes;

    public EducacaoAmbiental(String local, LocalDate data, double duracaoHoras, int numeroParticipantes) {
        super(local, data, duracaoHoras);
        this.numeroParticipantes = numeroParticipantes;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    @Override
    public double fatorDeImpacto() {
        return 1.0; // Exemplo de fator de impacto para ações de educação ambiental
    }
}