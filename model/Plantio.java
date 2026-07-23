package model;

import java.time.LocalDate;

public class Plantio extends AcaoAmbiental {
    private int quantidadeArvoresPlantadas;

    public Plantio(String local, LocalDate data, double duracaoHoras, int quantidadeArvoresPlantadas) {
        super(local, data, duracaoHoras);
        this.quantidadeArvoresPlantadas = quantidadeArvoresPlantadas;
    }

    public int getQuantidadeArvoresPlantadas() {
        return quantidadeArvoresPlantadas;
    }

    public void setQuantidadeArvoresPlantadas(int quantidadeArvoresPlantadas) {
        this.quantidadeArvoresPlantadas = quantidadeArvoresPlantadas;
    }

    @Override
    public double fatorDeImpacto() {
        return 2.0; // Exemplo de fator de impacto para ações de plantio
    }
}