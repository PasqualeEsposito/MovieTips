package model.gestioneRecensione;

public class Recensione {
    private int idRecensione;
    private int valutazione;
    private String testo;
    private boolean segnalazione;
    private int idFilm;
    private String usernameUtente;

    public Recensione() {
    }

    public Recensione(int valutazione, String testo, boolean segnalazione, int idFilm, String usernameUtente) {
        this.valutazione = valutazione;
        this.testo = testo;
        this.segnalazione = segnalazione;
        this.idFilm = idFilm;
        this.usernameUtente = usernameUtente;
    }

    /**
     * @return
     */
    public int getIdRecensione() {
        return idRecensione;
    }

    /**
     * @param idRecensione
     */
    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    /**
     * @return
     */
    public int getValutazione() {
        return valutazione;
    }

    /**
     * @param valutazione
     */
    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    /**
     * @return
     */
    public String getTesto() {
        return testo;
    }

    /**
     * @param testo
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * @return
     */
    public boolean isSegnalazione() {
        return segnalazione;
    }

    /**
     * @param segnalazione
     */
    public void setSegnalazione(boolean segnalazione) {
        this.segnalazione = segnalazione;
    }

    /**
     * @return
     */
    public int getIdFilm() {
        return idFilm;
    }

    /**
     * @param idFilm
     */
    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    /**
     * @return
     */
    public String getUsernameUtente() {
        return usernameUtente;
    }

    /**
     * @param usernameUtente
     */
    public void setUsernameUtente(String usernameUtente) {
        this.usernameUtente = usernameUtente;
    }
}