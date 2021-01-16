package model.film;

public class Film {
    private int id_film;
    private String titolo;
    private String produzione;
    private String musiche;
    private String fotografia;
    private String sceneggiatura;
    private String distribuzione;
    private int durata;
    private String paese;
    private String attori;
    private String regia;
    private String genere;
    private String trama;

    public Film() {
    }

    public Film(String titolo, String produzione, String musiche,
                String fotografia, String sceneggiatura, String distribuzione, int durata, String paese,
                String attori, String regia, String genere, String trama) {
        this.titolo = titolo;
        this.produzione = produzione;
        this.musiche = musiche;
        this.fotografia = fotografia;
        this.sceneggiatura = sceneggiatura;
        this.distribuzione = distribuzione;
        this.durata = durata;
        this.paese = paese;
        this.attori = attori;
        this.regia = regia;
        this.genere = genere;
        this.trama = trama;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public String getFotografia() {
        return fotografia;
    }

    public String getMusiche() {
        return musiche;
    }

    public String getProduzione() {
        return produzione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setMusiche(String musiche) {
        this.musiche = musiche;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public void setProduzione(String produzione) {
        this.produzione = produzione;
    }

    public String getSceneggiatura() {
        return sceneggiatura;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setSceneggiatura(String sceneggiatura) {
        this.sceneggiatura = sceneggiatura;
    }

    public String getGenere() {
        return genere;
    }

    public int getDurata() {
        return durata;
    }

    public String getAttori() {
        return attori;
    }

    public String getDistribuzione() {
        return distribuzione;
    }

    public void setDistribuzione(String distribuzione) {
        this.distribuzione = distribuzione;
    }

    public String getPaese() {
        return paese;
    }

    public String getRegia() {
        return regia;
    }

    public String getTrama() {
        return trama;
    }

    public void setAttori(String attori) {
        this.attori = attori;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public void setRegia(String regia) {
        this.regia = regia;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }
}
