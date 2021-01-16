package model.notizia;

public class Notizia {
    private int id_notizia;
    private String titolo;
    private String testo;
    private String fonte;
    private int id_film;

    public Notizia() {
    }

    public Notizia(int id_notizia, String titolo, String testo, String fonte, int id_film) {
        this.id_notizia = id_notizia;
        this.titolo = titolo;
        this.testo = testo;
        this.fonte = fonte;
        this.id_film = id_film;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getTesto() {
        return testo;
    }

    public int getId_notizia() {
        return id_notizia;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public void setId_notizia(int id_notizia) {
        this.id_notizia = id_notizia;
    }
}
