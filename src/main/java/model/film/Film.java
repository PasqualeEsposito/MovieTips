package model.film;


/**
 * Classe che rappresenta un film
 */
public class Film {
    private int idFilm;
    private String titolo;
    private String genere;
    private int anno;
    private String regia;
    private String attori;
    private String paese;
    private int durata;
    private String distribuzione;
    private String sceneggiatura;
    private String fotografia;
    private String musiche;
    private String produzione;
    private String trama;

    public Film() {
    }

    public Film(String titolo, String genere, int anno, String regia, String attori, String paese, int durata, String distribuzione, String sceneggiatura, String fotografia, String musiche, String produzione, String trama) {
        this.titolo = titolo;
        this.genere = genere;
        this.anno = anno;
        this.regia = regia;
        this.attori = attori;
        this.paese = paese;
        this.durata = durata;
        this.distribuzione = distribuzione;
        this.sceneggiatura = sceneggiatura;
        this.fotografia = fotografia;
        this.musiche = musiche;
        this.produzione = produzione;
        this.trama = trama;
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
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @return
     */
    public String getGenere() {
        return genere;
    }

    /**
     * @param genere
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * @return
     */
    public int getAnno() {
        return anno;
    }

    /**
     * @param anno
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }

    /**
     * @return
     */
    public String getRegia() {
        return regia;
    }

    /**
     * @param regia
     */
    public void setRegia(String regia) {
        this.regia = regia;
    }

    /**
     * @return
     */
    public String getAttori() {
        return attori;
    }

    /**
     * @param attori
     */
    public void setAttori(String attori) {
        this.attori = attori;
    }

    /**
     * @return
     */
    public String getPaese() {
        return paese;
    }

    /**
     * @param paese
     */
    public void setPaese(String paese) {
        this.paese = paese;
    }

    /**
     * @return
     */
    public int getDurata() {
        return durata;
    }

    /**
     * @param durata
     */
    public void setDurata(int durata) {
        this.durata = durata;
    }

    /**
     * @return
     */
    public String getDistribuzione() {
        return distribuzione;
    }

    /**
     * @param distribuzione
     */
    public void setDistribuzione(String distribuzione) {
        this.distribuzione = distribuzione;
    }

    /**
     * @return
     */
    public String getSceneggiatura() {
        return sceneggiatura;
    }

    /**
     * @param sceneggiatura
     */
    public void setSceneggiatura(String sceneggiatura) {
        this.sceneggiatura = sceneggiatura;
    }

    /**
     * @return
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * @return
     */
    public String getMusiche() {
        return musiche;
    }

    /**
     * @param musiche
     */
    public void setMusiche(String musiche) {
        this.musiche = musiche;
    }

    /**
     * @return
     */
    public String getProduzione() {
        return produzione;
    }

    /**
     * @param produzione
     */
    public void setProduzione(String produzione) {
        this.produzione = produzione;
    }

    /**
     * @return
     */
    public String getTrama() {
        return trama;
    }

    /**
     * @param trama
     */
    public void setTrama(String trama) {
        this.trama = trama;
    }
}