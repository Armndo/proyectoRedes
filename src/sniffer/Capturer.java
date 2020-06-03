/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.util.HexHelper;
import protocols.Ethernet;
import protocols.IPv4;
import protocols.IPv6;
import protocols.TCP;
import protocols.UDP;
import utils.Tool;

/**
 *
 * @author Armando
 */
public class Capturer {
    
    private static final int INFINITE = -1;
    private static final int PACKET_COUNT = INFINITE;
    private static String alv = "(ip or ip6) and (tcp or udp)";
    private static final String FILTER = "icmp6";
    private PacketCapture m_pcap;
    private String m_device;

    public Capturer() throws Exception {
        Tool tool = new Tool();
        this.m_pcap = new PacketCapture();
        this.m_device = tool.filterDevice(this.m_pcap.lookupDevices()[2]);
    //      for (String s : m_pcap.lookupDevices()) {
    //          System.out.println(s);
    //      }
        this.m_pcap.open(m_device, 65536, false, 1000);
        this.m_pcap.setFilter(FILTER, true);
        this.m_pcap.addRawPacketListener(new RawPacketHandler());
        //this.m_pcap.addPacketListener(new PacketHandler());
        this.m_pcap.capture(PACKET_COUNT);
    }
}

class PacketHandler implements PacketListener 
{
    private static int m_counter = 0;
  public void packetArrived(Packet packet) {
    m_counter++;
    //String type = packet.getClass().getName();
      System.out.println(packet);
      System.out.println(packet.getClass().getName());
      System.out.println("");
      //System.out.println(HexHelper.toString(packet.getData())+ "\n");
    //System.out.println("Packet(" + m_counter + ") is of type " + type + ".");
    //System.out.println("Packet(" + m_counter + ") time = " + packet.getTimeval());
  }
}