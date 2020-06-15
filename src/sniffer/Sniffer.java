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
import gui.GUI;
import gui.Selector;
import java.util.Vector;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import protocols.DataPacket;

/**
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 * 
 */
public class Sniffer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Selector s = new Selector();
        while(!s.getStatus()){
            
        }
        System.out.println(s.getDispositivo());
        try {
            GUI lmao = new GUI();
            Capturer capturer = new Capturer(lmao, s.getDispositivo());
        } catch (Exception ex) {
            Logger.getLogger(Sniffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
