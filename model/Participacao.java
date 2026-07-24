package model;

public class Participacao {
    private int id;
    private Voluntario voluntario;
    private AcaoAmbiental acaoAmbiental;

    public Participacao(Voluntario voluntario, AcaoAmbiental acaoAmbiental) {
        this.voluntario = voluntario;
        this.acaoAmbiental = acaoAmbiental;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public AcaoAmbiental getAcaoAmbiental() {
        return acaoAmbiental;
    }

    public void setAcaoAmbiental(AcaoAmbiental acaoAmbiental) {
        this.acaoAmbiental = acaoAmbiental;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void exibirDetalhesParticipacao() {
        voluntario.exibirDetalhesVoluntario();
        System.out.println("Ação: " + acaoAmbiental);
        System.out.println("Local da Ação: " + acaoAmbiental.getLocal());
        System.out.println("Data da Ação: " + acaoAmbiental.getData());
        System.out.println("Duração (horas): " + acaoAmbiental.getDuracaoHoras());
        System.out.println("Impacto Ambiental: " + acaoAmbiental.calcularImpactoAmbiental());
    }
}