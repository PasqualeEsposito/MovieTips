package model.utente;

/**
 * Classe che rappresenta un utente
 */
public class Utente {
    private String username;
    private String mail;
    private String password;
    private String nome;
    private String cognome;
    private String genere;
    private String dataNascita;
    private String ruolo;

    public Utente() {
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
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
    public String getDataNascita() {
        return dataNascita;
    }

    /**
     * @param dataNascita
     */
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Ruolo "ABCDEF":
     * 'A' utente bannato
     * 'B' utente non attivo
     * 'C' filmino
     * 'D' amministratore
     * 'E' articolista
     * 'F' moderatore
     *
     * @return Ritorna i ruoli dell'utente
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Ruolo "ABCDEF":
     * 'A' utente bannato
     * 'B' utente non attivo
     * 'C' filmino
     * 'D' amministratore
     * 'E' articolista
     * 'F' moderatore
     *
     * @param ruolo I ruoli dell'utente
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * @return
     */
    public boolean isBanned() {
        if (ruolo.charAt(0) == '1')
            return true;
        return false;
    }

    /**
     * @return
     */
    public boolean isNotActive() {
        if (ruolo.charAt(1) == '1')
            return true;
        return false;
    }

    /**
     * @return
     */
    public boolean isFilmino() {
        if (ruolo.charAt(2) == '1')
            return true;
        return false;
    }

    /**
     * @return
     */
    public boolean isModeratore() {
        if (ruolo.charAt(5) == '1')
            return true;
        return false;
    }
}