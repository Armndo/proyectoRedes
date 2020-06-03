/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.util.HexHelper;
import protocols.Ethernet;
import protocols.ICMP;
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
        //m_counter++;
        String raw = HexHelper.toString(data.getData()).toUpperCase();
        System.out.println(raw + "\n");
        String arr[] = raw.split(" ");
        Ethernet eth = new Ethernet(arr);
        Object ip = new Object();
        Object udp = new Object();
        Object tcp = new Object();
        Object icmp = new Object();
        System.out.println(eth);
        if(eth.isValid()) {
            if(eth.getType().equals("IPv4")) {
                ip = new IPv4(arr);
            } else if(eth.getType().equals("IPv6")) {
                ip = new IPv6(arr);
            }
        }
        System.out.println(ip);
        if((ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("UDP")) || (ip instanceof IPv6 && ((IPv6)ip).getNextHeader().equals("UDP"))) {
            udp = new UDP(arr, ip);
            System.out.println(udp);
        }
        if(ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("TCP")) {
            tcp = new TCP(arr);
            System.out.println(tcp);
        }
        if(ip instanceof IPv4 && ((IPv4)ip).getProtocol().equals("ICMP")) {
            icmp = new ICMP(arr);
            System.out.println(icmp);
        }
    }
}