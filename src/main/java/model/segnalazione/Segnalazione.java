package model.segnalazione;

public class Segnalazione {
    private int idRecensione;
    private int idUtente;
    private int counter;

    public Segnalazione() {
    }

    public Segnalazione(int idRecensione, int idUtente) {
        this.idRecensione = idRecensione;
        this.idUtente = idUtente;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
