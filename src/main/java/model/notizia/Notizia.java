package model.notizia;

public class Notizia {
    private int idNotizia;
    private String titolo;
    private String testo;
    private String fonte;
    private int idFilm;

    public Notizia() {
    }

    public Notizia(int idNotizia, String titolo, String testo, String fonte, int idFilm) {
        this.idNotizia = idNotizia;
        this.titolo = titolo;
        this.testo = testo;
        this.fonte = fonte;
        this.idFilm = idFilm;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getIdNotizia() {
        return idNotizia;
    }

    public void setIdNotizia(int idNotizia) {
        this.idNotizia = idNotizia;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
}