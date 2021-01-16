package model.film_seguiti;

public class Film_seguiti {
    private int id_utente;
    private int id_film;

    public Film_seguiti() {
    }

    public Film_seguiti(int id_utente, int id_film) {
        this.id_film = id_film;
        this.id_utente = id_utente;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public int getId_film() {
        return id_film;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }
}
