/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocols.Ethernet;
import protocols.IPv4;
import protocols.UDP;

/**
 *
 * @author Armando
 */
public class Sniffer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Capturer capturer = new Capturer();
        } catch (Exception ex) {
            Logger.getLogger(Sniffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
