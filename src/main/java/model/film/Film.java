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
    private int anno;

    public Film() {
    }

    public Film(String titolo, String produzione, String musiche,
                String fotografia, String sceneggiatura, String distribuzione, int durata, String paese,
                String attori, String regia, String genere, String trama, int anno) {
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
        this.anno = anno;
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

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getMusiche() {
        return musiche;
    }

    public void setMusiche(String musiche) {
        this.musiche = musiche;
    }

    public String getProduzione() {
        return produzione;
    }

    public void setProduzione(String produzione) {
        this.produzione = produzione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getSceneggiatura() {
        return sceneggiatura;
    }

    public void setSceneggiatura(String sceneggiatura) {
        this.sceneggiatura = sceneggiatura;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getAttori() {
        return attori;
    }

    public void setAttori(String attori) {
        this.attori = attori;
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

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getRegia() {
        return regia;
    }

    public void setRegia(String regia) {
        this.regia = regia;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }
}