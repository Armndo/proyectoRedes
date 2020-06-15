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
 *
 * @author Armando
 */
public class Sniffer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        for(int i = 0; i < 256; i++) {
//            System.out.println("i: " + i + " => " + (char)i);
//        }
//        System.out.println(((char)Integer.parseInt("89", 16)));
            
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
//        for(int i = 0; i < lmao.getTable().getRowCount(); i++) {
//            System.out.println(((DataPacket)((DefaultTableModel)lmao.getTable().getModel()).getValueAt(i, 0)).getEthernet());
//        }
    }
    
}
