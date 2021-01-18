package model.film;

public class Film {
    private int idFilm;
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

    /**
     * Classe che rappresenta un film
     *
     * @param idFilm        l'id del film
     * @param titolo        il titolo del film
     * @param produzione    la casa di produzione cinematografica
     * @param musiche       il nome del compositore delle musiche usate nel film
     * @param fotografia    il nome di chi si occupa della fotografia
     * @param sceneggiatura il nome di chi si occupa della sceneggiatura
     * @param distribuzione il nome di chi distribuisce il film
     * @param durata        la durata in minuti del film
     * @param paese         il paese dove è stato girato il film
     * @param attori        i nomi degli attori presenti nel film
     * @param regia         la regia del film
     * @param genere        il genere/i generi del film
     * @param trama         la trama del film
     * @param anno          l'anno di uscita del film
     */
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
    public String getTrama() {
        return trama;
    }

    /**
     * @param trama
     */
    public void setTrama(String trama) {
        this.trama = trama;
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
}