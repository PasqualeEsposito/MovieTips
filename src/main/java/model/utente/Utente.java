package model.utente;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe che rappresenta un utente
 */
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

    /**
     * Inizializza un oggetto Utente appena creato
     */
    public Utente() {
    }

    /**
     * Costruisce una nuovo Utente utilizzando i dati in input
     *
     * @param username L'username dell'utente
     * @param nome     Il nome dell'utente
     * @param cognome  il cognome dell'utente
     * @param email    L'email dell'utente
     * @param password La password dell'utente
     * @param genere   Il genere dell'utente
     * @param ddn      La data di nascita dell'utente
     * @param ruolo    I ruoli dell'utente (utente bannato, utente non attivo, filmino, amministratore, articolista, moderatore)
     */
    public Utente(String username, String nome, String cognome, String email, String password, String genere, String ddn, String ruolo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        setPassword(password);
        this.genere = genere;
        this.ddn = ddn;
        this.ruolo = ruolo;
    }

    /**
     * @return Ritorna l'intero che distingue univocamente un utente dall'altro
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * @param idUtente L'intero che distingue univocamente un utente dall'altro
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * @return Ritorna il cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome Il cognome dell'utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
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
     * @param ruolo I ruoli dell'utente
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * @return Ritorna La data di nascita dell'utente
     */
    public String getDdn() {
        return ddn;
    }

    /**
     * @param ddn La data di nascita dell'utente
     */
    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    /**
     * @return Ritorna l'email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email L'email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Ritorna il genere dell'utente
     */
    public String getGenere() {
        return genere;
    }

    /**
     * @param genere Il genere dell'utente
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * @return Ritorna il nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Il nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Ritorna la password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password La password dell'utente
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
     * @return Ritorna l'username dell'utente
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username L'username dell'utente
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*public boolean isBanned() {
        if (ruolo.charAt(0) == '1')
            return true;
        return false;
    }

    public boolean isNotActive() {
        if (ruolo.charAt(1) == '1')
            return true;
        return false;
    }

    public boolean isFilmino() {
        if (ruolo.charAt(2) == '1')
            return true;
        return false;
    }

    public boolean isAmministratore() {
        if (ruolo.charAt(3) == '1')
            return true;
        return false;
    }

    public boolean isArticolista() {
        if (ruolo.charAt(4) == '1')
            return true;
        return false;
    }

    public boolean isModeratore() {
        if (ruolo.charAt(5) == '1')
            return true;
        return false;
    }*/
}