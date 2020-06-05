/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import javax.swing.JOptionPane;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.util.HexHelper;
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
    
    public void rawPacketArrived(RawPacket data) {
        m_counter++;
        String raw = HexHelper.toString(data.getData()).toUpperCase();
        System.out.println(raw + "\n");
        String arr[] = raw.split(" ");
        Ethernet eth = new Ethernet(arr);
        Object ip = new Object();
        Object udp = new Object();
        Object tcp = new Object();
        Object icmp = new Object();
        Object icmp6 = new Object();
        String str = "";
        System.out.println(eth);
        //str += eth;
        if(eth.isValid()) {
            if(eth.getType().equals("IPv4")) {
                ip = new IPv4(arr);
            } else if(eth.getType().equals("IPv6")) {
                ip = new IPv6(arr);
            }
            //str += ip;
        }
        System.out.println(ip);
        if((ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("UDP")) || (ip instanceof IPv6 && ((IPv6)ip).getNextHeader().equals("UDP"))) {
            udp = new UDP(arr, ip);
            System.out.println(udp);
            //str += udp;
        }
        if(ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("TCP")) {
            tcp = new TCP(arr);
            System.out.println(tcp);
            //str += tcp;
        }
        if(ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("ICMP")) {
            icmp = new ICMP(arr);
            System.out.println(icmp);
            //str += icmp;
        }
        if(ip instanceof IPv6 && ((IPv6)ip).getNextHeader().equals("IPv6-ICMP")){
            icmp6 = new ICMP6(arr);
            System.out.println(icmp6);
            //str += icmp6;
        }
        //JOptionPane pane = new JOptionPane(eth);
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
            JOptionPane.showMessageDialog(null, str);
        }
    }
}