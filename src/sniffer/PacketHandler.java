/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import gui.GUI;
import java.util.ArrayList;
import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.util.HexHelper;

/**
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González Gonzáles Armando
 * 
 */
public class PacketHandler implements PacketListener  {
    
    private GUI gui;
    private static int m_counter = 0;
    private ArrayList<Packet> arr;
    
    public PacketHandler(GUI gui, ArrayList arr) {
        this.gui = gui;
        this.arr = arr;
    }
    
    public void packetArrived(Packet packet) {
        m_counter++;
        System.out.println(packet);
        System.out.println(HexHelper.toString(packet.getHeader()));
        System.out.println(HexHelper.toString(packet.getData()));
        System.out.println("");
        
    
        //((DefaultListModel)gui.getList().getModel()).addElement(packet);
      //System.out.println(HexHelper.toString(packet.getData())+ "\n");
    //System.out.println("Packet(" + m_counter + ") is of type " + type + ".");
    //System.out.println("Packet(" + m_counter + ") time = " + packet.getTimeval());
  }
}