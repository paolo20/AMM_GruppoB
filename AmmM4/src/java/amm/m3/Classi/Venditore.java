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
public class Venditore extends Utente{
    
    private ArrayList<OggettoInVendita> listaOggetti= new ArrayList<OggettoInVendita>();
    
    public Venditore(){
        super();
    }

    /**
     * @return the listaOggetti
     */
    public ArrayList<OggettoInVendita> getListaOggetti() {
        return listaOggetti;
    }

    /**
     * @param listaOggetti the listaOggetti to set
     */
    public void setListaOggetti(ArrayList<OggettoInVendita> listaOggetti) {
        this.listaOggetti = listaOggetti;
    }
}
