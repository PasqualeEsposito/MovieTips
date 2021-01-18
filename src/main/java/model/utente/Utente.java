package model.utente;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utente {
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String genere;
    private String ddn;
    private int idUtente;
    private String ruolo;

    public Utente() {
    }

    /**
     * Classe che rappresenta un utente qualsiasi
     *
     * @param username l'username dell'utente, univoco per ciascun utente
     * @param nome     il nome dell'utente
     * @param cognome  il cognome dell'utente
     * @param email    l'email dell'utente, univoca per ciascun utente
     * @param password la password dell'utente
     * @param genere   il sesso dell'utente
     * @param ddn      la data di nascita dell'utente
     * @param idUtente l'id dell'utente generato automaticamente
     * @param ruolo    il ruolo dell'utente (utente bannato, utente non attivo, filmino, amministratore, articolista, moderatore)
     */
    public Utente(String username, String nome, String cognome, String email, String password, String genere, String ddn, int idUtente, String ruolo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        setPassword(password);
        this.genere = genere;
        this.ddn = ddn;
        this.idUtente = idUtente;
        this.ruolo = ruolo;
    }

    /**
     * @return
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * @param idUtente
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
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
    public String getRuolo() {
        return ruolo;
    }

    /**
     * @param ruolo
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * @return
     */
    public String getDdn() {
        return ddn;
    }

    /**
     * @param ddn
     */
    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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

    /*
     * Ruolo "ABCDEF":
     *
     * 'A' utente bannato
     * 'B' utente non attivo
     * 'C' filmino
     * 'D' amministratore
     * 'E' articolista
     * 'F' moderatore
     * */

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
    public boolean isAmministratore() {
        if (ruolo.charAt(3) == '1')
            return true;
        return false;
    }

    /**
     * @return
     */
    public boolean isArticolista() {
        if (ruolo.charAt(4) == '1')
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

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", genere='" + genere + '\'' +
                ", ddn='" + ddn + '\'' +
                ", idUtente=" + idUtente +
                ", ruolo=" + ruolo +
                '}';
    }
}