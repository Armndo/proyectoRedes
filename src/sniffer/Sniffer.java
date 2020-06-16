package sniffer;

import java.util.logging.Level;
import java.util.logging.Logger;
import gui.GUI;
import gui.Selector;

/**
 * 
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 */
public class Sniffer {

    public static void main(String[] args) throws Exception {
        Selector s = new Selector();
        while(!s.getStatus()){ }
        try {
            GUI lmao = new GUI();
            Capturer capturer = new Capturer(lmao, s.getDispositivo());
        } catch (Exception ex) {
            Logger.getLogger(Sniffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
