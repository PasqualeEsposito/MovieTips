package model.recensione;

public class Recensione {
    private int id_recensione;
    private int valutazione;
    private String testo;
    private int numero_segnalazioni;
    private int id_utente;
    private int id_film;

    public Recensione() {
    }

    public Recensione(int valutazione, String testo, int id_utente, int id_film) {
        this.valutazione = valutazione;
        this.testo = testo;
        this.id_utente = id_utente;
        this.id_film = id_film;
        numero_segnalazioni = 0;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public int getId_recensione() {
        return id_recensione;
    }

    public void setId_recensione(int id_recensione) {
        this.id_recensione = id_recensione;
    }

    public int getNumero_segnalazioni() {
        return numero_segnalazioni;
    }

    public void setNumero_segnalazioni(int numero_segnalazioni) {
        this.numero_segnalazioni = numero_segnalazioni;
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