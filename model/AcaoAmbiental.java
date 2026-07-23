package model;

import java.time.LocalDate;

public abstract class AcaoAmbiental {
    private String local;
    private LocalDate data;
    private double duracaoHoras;

    public AcaoAmbiental(String local, LocalDate data, double duracaoHoras) {
        this.local = local;
        this.data = data;
        this.duracaoHoras = duracaoHoras;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(double duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public abstract double fatorDeImpacto();

    public double calcularImpactoAmbiental() {
        return fatorDeImpacto() * duracaoHoras;
    }
}