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

    public Recensione(int valutazione, String testo, int id_utente, int id_film) {
        this.valutazione = valutazione;
        this.testo = testo;
        this.idUtente = id_utente;
        this.idFilm = id_film;
        numeroSegnalazioni = 0;
    }

    public int getId_utente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int id_film) {
        this.idFilm = idFilm;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int id_recensione) {
        this.idRecensione = id_recensione;
    }

    public int getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }

    public void setNumeroSegnalazioni(int numero_segnalazioni) {
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