/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import gui.GUI;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.util.HexHelper;
import protocols.DataPacket;
import protocols.Ethernet;
import protocols.ICMP;
import protocols.ICMP6;
import protocols.IPv4;
import protocols.IPv6;
import protocols.TCP;
import protocols.UDP;

/**
 *
 * @author Armando
 */
public class RawPacketHandler implements RawPacketListener {
    
    private static int m_counter = 0;
    private GUI gui;
    private ArrayList<Packet> arr;
    private long init;
    
    public RawPacketHandler(GUI gui, ArrayList arr) {
        this.gui = gui;
        this.arr = arr;
        init = System.currentTimeMillis();
    }
    
    public void rawPacketArrived(RawPacket data) {
        m_counter++;
        String raw = HexHelper.toString(data.getData()).toUpperCase();
        String arr[] = raw.split(" ");
        Ethernet eth = new Ethernet(arr);
        Object ip = new Object();
        Object udp = new Object();
        Object tcp = new Object();
        Object icmp = new Object();
        Object icmp6 = new Object();
        String str = "";
        if(eth.isValid()) {
            if(eth.getType().equals("IPv4")) {
                ip = new IPv4(arr);
            } else if(eth.getType().equals("IPv6")) {
                ip = new IPv6(arr);
            }
        }
        if((ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("UDP")) || (ip instanceof IPv6 && ((IPv6)ip).getNextHeader().equals("UDP"))) {
            udp = new UDP(arr, ip);
        }
        if(ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("TCP")) {
            tcp = new TCP(arr);
        }
        if(ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("ICMP")) {
            icmp = new ICMP(arr);
        }
        if(ip instanceof IPv6 && ((IPv6)ip).getNextHeader().equals("IPv6-ICMP")){
            icmp6 = new ICMP6(arr);
        }
        if(eth.isValid()) {
            str += "Packet " + m_counter + "\n";
            str += data.getTimeval().getDate().toInstant() + "\n";
            String lmao[] = arr;
            for(int i = 0; i < lmao.length; i++) {
                str += lmao[i] + " ";
                if((i+1)%40 == 0) {
                    str += "\n";
                }
            }
            str += "\n\n";
            str += eth.getType() +": ";
            if(eth.getType().equals("IPv4")) {
                IPv4 ip4 = (IPv4)ip;
                str += ip4.getSource() + "->" + ip4.getDestination() + " (" + ip4.getProtocol() + ")";
            }
            if(eth.getType().equals("IPv6")) {
                IPv6 ip6 = (IPv6)ip;
                str += ip6.getSource() + "->" + ip6.getDestination() + " (" + ip6.getNextHeader() + ")";
            }
            str += "\n";
            if(udp instanceof UDP) {
                UDP pudp = (UDP)udp;
                str += "UDP: " + pudp.getSource() + "->" + pudp.getDestination() +", Length: " + pudp.getSource() + " bytes, Checksum: 0x" + pudp.getChecksum() + "\n\nData:\n";
                if(pudp.getData().length() == 0)
                    str += "N/A";
                String aux[] = pudp.getData().split(" ");
                for(int i = 0; i < aux.length; i++) {
                    str += aux[i] + " ";
                    if((i+1)%40 == 0) {
                        str += "\n";
                    }
                }
            }
            if(tcp instanceof TCP) {
                TCP ptcp = (TCP)tcp;
                str += "TCP: " + ptcp.getSource() + "->" + ptcp.getDestination() + ", Length: " + ptcp.getHeaderLength() + " bytes, ACK: 0x" + ptcp.getACK() + ", SEQ: 0x" + ptcp.getSEQ() + ", Checksum: 0x" + ptcp.getChecksum() + "\n\nData:\n";
                if(ptcp.getData().length() == 0)
                    str += "N/A";
                String aux[] = ptcp.getData().split(" ");
                for(int i = 0; i < aux.length; i++) {
                    str += aux[i] + " ";
                    if((i+1)%40 == 0) {
                        str += "\n";
                    }
                }
            }
            if(icmp instanceof ICMP) {
                ICMP picmp = (ICMP)icmp;
                str += "ICMP: " + picmp.getType() + ", Code: " + picmp.getCode() + ", Checksum: 0x" + picmp.getChecksum() + "\n\nData:\n";
                if(picmp.getData().length() == 0)
                    str += "N/A";
                String aux[] = picmp.getData().split(" ");
                for(int i = 0; i < aux.length; i++) {
                    str += aux[i] + " ";
                    if((i+1)%40 == 0) {
                        str += "\n";
                    }
                }
            }
            if(icmp6 instanceof ICMP6) {
                ICMP6 picmp6 = (ICMP6)icmp6;
                str += "ICMP-IPv6: " + picmp6.getType_code()[0] + ", Checksum: 0x" + picmp6.getChecksum() + "\n\nMessage Body:\n";
                if(picmp6.getMessagebody().length() == 0)
                    str += "N/A";
                String aux[] = picmp6.getMessagebody().split(" ");
                for(int i = 0; i < aux.length; i++) {
                    str += aux[i] + " ";
                    if((i+1)%40 == 0) {
                        str += "\n";
                    }
                }
            }
            DataPacket dp = new DataPacket(data.getTimeval(), m_counter, raw, eth, ip, icmp, icmp6, tcp, udp);
            DefaultTableModel model = (DefaultTableModel)gui.getTable().getModel();
            Vector v = new Vector();
            v.add((DataPacket)dp);
            v.add(String.format("%.6f", ((long)(Double.parseDouble(data.getTimeval().toString().substring(0, data.getTimeval().toString().length()-1))*1000000)-init*1000)/1000000.0));
            String aux = "";
            aux = ip instanceof IPv4 ? ((IPv4)ip).getSource() : ip instanceof IPv6 ? ((IPv6)ip).getSource() : "ERROR";
            v.add(aux);
            aux = ip instanceof IPv4 ? ((IPv4)ip).getDestination(): ip instanceof IPv6 ? ((IPv6)ip).getDestination() : "ERROR";
            v.add(aux);
            aux = ip instanceof IPv4 ? ((IPv4)ip).getProtocol() : ip instanceof IPv6 ? ((IPv6)ip).getNextHeader() : "ERROR";
            v.add(aux);
            v.add(data.getData().length + " bytes");
            aux = "";
            if(udp instanceof UDP) {
                aux = ((UDP) udp).getSource() + " → " + ((UDP) udp).getDestination() + " Len=" + ((UDP) udp).getLength();
            }
            v.add(aux);
            model.addRow(v);
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(RawPacketHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
}