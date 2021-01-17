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
    private byte[] password;
    private String genere;
    private String ddn;
    private int id_utente;
    private int ruolo;

    public Utente() {
        password = new byte[32];
    }

    public Utente(String username, String nome, String cognome, String email, String password, String genere, String ddn, int id_utente, int ruolo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        setPassword(password);
        this.genere = genere;
        this.ddn = ddn;
        this.id_utente = id_utente;
        this.ruolo = ruolo;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
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

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(String passwordToEncode) {

        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte arr[] = md.digest(passwordToEncode.getBytes());
            this.password = arr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", id_utente=" + id_utente +
                ", ruolo=" + ruolo +
                '}';
    }
}