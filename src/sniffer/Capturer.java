package sniffer;

import gui.GUI;
import net.sourceforge.jpcap.capture.PacketCapture;

/**
 * 
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 */
public class Capturer {
    
    private static final int INFINITE = -1;
    private static final int PACKET_COUNT = INFINITE;
    private static final String FILTER = "(ip and (tcp or udp or icmp)) or (ip6 and (udp or icmp6))";
    private PacketCapture capturer;
    private String device;
    private GUI gui;

    public Capturer(GUI gui, String device) throws Exception {
        this.gui = gui;
        this.capturer = new PacketCapture();
        this.device = device;
        this.capturer.open(device, 65536, false, 10000);
        this.capturer.setFilter(this.FILTER, true);
        this.capturer.addRawPacketListener(new RawPacketHandler(this.gui));
        this.capturer.capture(this.PACKET_COUNT);
    }
    
}