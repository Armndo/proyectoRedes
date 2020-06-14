/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import java.util.logging.Level;
import java.util.logging.Logger;
import gui.GUI;
import javax.swing.JOptionPane;

/**
 *
 * @author Armando
 */
public class Sniffer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI lmao = new GUI();
        try {
            Capturer capturer = new Capturer(lmao);
        } catch (Exception ex) {
            Logger.getLogger(Sniffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
