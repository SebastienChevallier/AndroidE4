package com.example.seb.androide4.mesErreurs;

/**
 * Created by christian on 15/12/13.
 */
public class MonException extends Exception implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;
    private String type;

    public MonException() {
    }

    public MonException(String libelle, String type) {
        this.message = libelle;
        this.type = type;
    }

    public MonException(String libelle) {
        this.message = libelle;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String libelle) {
        this.message = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

