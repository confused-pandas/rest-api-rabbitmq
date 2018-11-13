package eu.telecomnancy.championnat;

public enum Statut {

    PAUSE("Pause"),
    ENCOURS("En cours"),
    ANNULE("Annulé"),
    REPORTE("Reporté"),
    PREVU("Prévu"),
    FINI("Fini");

    private String statut;

    Statut(String statut) {
        this.statut = statut;
    }

    public String statut() {
        return statut;
    }
}
