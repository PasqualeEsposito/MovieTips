package model.recensione;

public class Recensione {
    private int idRecensione;
    private int valutazione;
    private String testo;
    private int numeroSegnalazioni;
    private int idUtente;
    private int idFilm;

    public Recensione() {
    }

    public Recensione(int valutazione, String testo, int idUtente, int idFilm) {
        this.valutazione = valutazione;
        this.testo = testo;
        this.idUtente = idUtente;
        this.idFilm = idFilm;
        numeroSegnalazioni = 0;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public int getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }

    public void setNumeroSegnalazioni(int numeroSegnalazioni) {
        this.numeroSegnalazioni = numeroSegnalazioni;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}