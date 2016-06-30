/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.Classi;

import java.util.ArrayList;

/**
 *
 * @author paolo
 */
public class OggettoInVendita {
    
    //Attributi
    private int idOggetto;
    protected String nomeEAutore;
    protected String image;
    protected double prezzo;
    protected int quantita;
    protected String descrizione;   
  
    
    //costruttore
    public OggettoInVendita() {
        
        idOggetto=0;
        nomeEAutore="";
        image="";
        prezzo=0.0;
        quantita=0;
        descrizione="";
        
    }

    /**
     * @return the id
     */
    public int getIdOggetto() {
        return idOggetto;
    }

    /**
     * @param id the id to set
     */
    public void setIdOggetto(int id) {
        this.idOggetto = id;
    }

    /**
     * @return the nomeEAutore
     */
    public String getNomeEAutore() {
        return nomeEAutore;
    }

    /**
     * @param nomeEAutore the nomeEAutore to set
     */
    public void setNomeEAutore(String nomeEAutore) {
        this.nomeEAutore = nomeEAutore;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the prezzo
     */
    public double getPrezzo() {
        return prezzo;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * @return the quantita
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * @param quantita the quantita to set
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
    

