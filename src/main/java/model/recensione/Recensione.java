package model.recensione;

public class Recensione {
    private int idRecensione;
    private int valutazione;
    private String testo;
    private String usernameUtente;
    private int idFilm;
    private boolean segnalazione;

    public Recensione() {
    }

    public Recensione(int valutazione, String testo, String usernameUtente, int idFilm) {
        this.valutazione = valutazione;
        this.testo = testo;
        this.usernameUtente = usernameUtente;
        this.idFilm = idFilm;
    }

    public String getUsernameUtente() {
        return usernameUtente;
    }

    public void setUsernameUtente(String usernameUtente) {
        this.usernameUtente = usernameUtente;
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

    public boolean isSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(boolean segnalazione) {
        this.segnalazione = segnalazione;
    }
}