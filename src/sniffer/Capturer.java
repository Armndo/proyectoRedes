/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import gui.GUI;
import java.util.ArrayList;
import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.net.Packet;
import utils.Tool;

/**
 *
 * @author Armando
 */
public class Capturer {
    
    private static final int INFINITE = -1;
    private static final int PACKET_COUNT = INFINITE;
    private static String alv = "(ip or ip6) and (tcp or udp)";
    private static final String FILTER = "";
    private ArrayList<Packet> arr;
    private PacketCapture m_pcap;
    private String m_device;
    private GUI gui;

    public Capturer(GUI gui, String dispositivo) throws Exception {
        this.arr = new ArrayList<>();
        this.gui = gui;
        Tool tool = new Tool();
        this.m_pcap = new PacketCapture();
        this.m_device = dispositivo;
        this.m_pcap.open(m_device, 65536, false, 10000);
        this.m_pcap.setFilter(FILTER, true);
        this.m_pcap.addRawPacketListener(new RawPacketHandler(this.gui, this.arr));
        //this.m_pcap.addPacketListener(new PacketHandler(this.gui, this.arr));
        this.m_pcap.capture(PACKET_COUNT);
    }
    
}