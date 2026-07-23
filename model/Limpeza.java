package model;

import java.time.LocalDate;

public class Limpeza extends AcaoAmbiental {
    private int quantidadeLixoColetado;

    public Limpeza(String local, LocalDate data, double duracaoHoras, int quantidadeLixoColetado) {
        super(local, data, duracaoHoras);
        this.quantidadeLixoColetado = quantidadeLixoColetado;
    }

    public int getQuantidadeLixoColetado() {
        return quantidadeLixoColetado;
    }

    public void setQuantidadeLixoColetado(int quantidadeLixoColetado) {
        this.quantidadeLixoColetado = quantidadeLixoColetado;
    }

    @Override
    public double fatorDeImpacto() {
        return 1.5; // Exemplo de fator de impacto para ações de limpeza
    }
}