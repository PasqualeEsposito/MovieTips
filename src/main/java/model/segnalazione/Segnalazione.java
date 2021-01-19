package model.segnalazione;

public class Segnalazione {
    private int idRecensione;
    private String usernameUtente;
    private int counter;

    public Segnalazione() {
    }

    public Segnalazione(int idRecensione, String usernameUtente) {
        this.idRecensione = idRecensione;
        this.usernameUtente = usernameUtente;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public String getUsernameUtente() {
        return usernameUtente;
    }

    public void setUsernameUtente(String usernameUtente) {
        this.usernameUtente = usernameUtente;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
}
