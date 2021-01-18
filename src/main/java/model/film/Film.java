package model.film;


/**
 * Classe rappresenta un film
 */
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

    /**
     * Inizializza un oggetto Film appena creato
     */
    public Film() {
    }

    /**
     * Costruisce una nuovo Film utilizzando i dati in input
     *
     * @param titolo        Il titolo del film
     * @param produzione    I nomi delle case di produzione del film
     * @param musiche       Ii nomi dei compositori delle musiche utilizzate nel film
     * @param fotografia    Ii nomi di chi si occupa della fotografia nel film
     * @param sceneggiatura Ii nomi di chi si occupa della sceneggiatura nel film
     * @param distribuzione Il nome del distributore del film
     * @param durata        La durata in minuti del film
     * @param paese         Il paese dove è stato prodotto il film
     * @param attori        I nomi degli attori presenti nel film
     * @param regia         I nomi di chi si occupa della regia del film
     * @param genere        I generi del film
     * @param trama         La trama del film
     * @param anno          L'anno di uscita del film
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
     * @return Ritorna l'intero che distingue univocamente un film dall'altro
     */
    public int getIdFilm() {
        return idFilm;
    }

    /**
     * @param idFilm L'intero che distingue univocamente un film dall'altro
     */
    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    /**
     * @return Ritorna i nomi di chi si occupa della fotografia nel film
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia I nomi di chi si occupa della fotografia nel film
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * @return Ritorna i nomi dei compositori delle musiche utilizzate nel film
     */
    public String getMusiche() {
        return musiche;
    }

    /**
     * @param musiche I nomi dei compositori delle musiche utilizzate nel film
     */
    public void setMusiche(String musiche) {
        this.musiche = musiche;
    }

    /**
     * @return Ritorna il nome delle case di produzione del film
     */
    public String getProduzione() {
        return produzione;
    }

    /**
     * @param produzione Il nome delle case di produzione del film
     */
    public void setProduzione(String produzione) {
        this.produzione = produzione;
    }

    /**
     * @return Ritorna il titolo del film
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo Il titolo del film
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @return Ritorna i nomi di chi si occupa della sceneggiatura nel film
     */
    public String getSceneggiatura() {
        return sceneggiatura;
    }

    /**
     * @param sceneggiatura I nomi di chi si occupa della sceneggiatura nel film
     */
    public void setSceneggiatura(String sceneggiatura) {
        this.sceneggiatura = sceneggiatura;
    }

    /**
     * @return Ritorna i generi del film
     */
    public String getGenere() {
        return genere;
    }

    /**
     * @param genere I generi del film
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * @return Ritorna la durata in minuti del film
     */
    public int getDurata() {
        return durata;
    }

    /**
     * @param durata La durata in minuti del film
     */
    public void setDurata(int durata) {
        this.durata = durata;
    }

    /**
     * @return Ritorna i nomi degli attori presenti nel film
     */
    public String getAttori() {
        return attori;
    }

    /**
     * @param attori I nomi degli attori presenti nel film
     */
    public void setAttori(String attori) {
        this.attori = attori;
    }

    /**
     * @return Ritorna il nome del distributore del film
     */
    public String getDistribuzione() {
        return distribuzione;
    }

    /**
     * @param distribuzione Il nome del distributore del film
     */
    public void setDistribuzione(String distribuzione) {
        this.distribuzione = distribuzione;
    }

    /**
     * @return Ritorna il paese dove è stato prodotto il film
     */
    public String getPaese() {
        return paese;
    }

    /**
     * @param paese Il paese dove è stato prodotto il film
     */
    public void setPaese(String paese) {
        this.paese = paese;
    }

    /**
     * @return Ritorna i nomi di chi si occupa della regia del film
     */
    public String getRegia() {
        return regia;
    }

    /**
     * @param regia I nomi di chi si occupa della regia del film
     */
    public void setRegia(String regia) {
        this.regia = regia;
    }

    /**
     * @return Ritorna la trama del film
     */
    public String getTrama() {
        return trama;
    }

    /**
     * @param trama La trama del film
     */
    public void setTrama(String trama) {
        this.trama = trama;
    }

    /**
     * @return Ritorna l'anno di uscita del film
     */
    public int getAnno() {
        return anno;
    }

    /**
     * @param anno L'anno di uscita del film
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }
}