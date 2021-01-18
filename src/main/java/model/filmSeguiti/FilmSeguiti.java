package model.filmSeguiti;

import model.film.Film;

import java.util.ArrayList;

public class FilmSeguiti {
    private int idUtente;
    private ArrayList<Film> listaFilm;

    public FilmSeguiti() {
    }

    public FilmSeguiti(int idUtente) {
        listaFilm = new ArrayList<>();
        this.idUtente = idUtente;
    }

    public ArrayList<Film> getListaFilm() {
        return listaFilm;
    }

    public void setListaFilm(ArrayList<Film> listaFilm) {
        this.listaFilm = listaFilm;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
}