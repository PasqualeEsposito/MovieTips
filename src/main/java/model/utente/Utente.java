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
    private int ruolo;

    public Utente() {
    }

    public Utente(String username, String nome, String cognome, String email, String password, String genere, String ddn, int idUtente, int ruolo) {
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

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public String getDdn() {
        return ddn;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * Ruolo:
     *   Filmino: 1
     *   Moderatore: 2
     *   Articolista: 3
     *   Amministratore: 4
     * */

    public boolean isFilmino() {
        if (ruolo == 1)
            return true;
        return false;
    }

    public boolean isModeratore() {
        if (ruolo == 2)
            return true;
        return false;
    }

    public boolean isArticolista() {
        if (ruolo == 3)
            return true;
        return false;
    }


    public boolean isAmministratore() {
        if (ruolo == 4)
            return true;
        return false;
    }

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
                ", id_utente=" + idUtente +
                ", ruolo=" + ruolo +
                '}';
    }
}