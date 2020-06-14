/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import gui.GUI;
import javax.swing.JOptionPane;
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
    private static final String FILTER = "(ip and (udp or tcp or icmp)) or (ip6 and (icmp6 or udp))";
    private PacketCapture capturer;
    private String device;
    private GUI gui;

    public Capturer(GUI gui) throws Exception {
        this.gui = gui;
        Tool tool = new Tool();
        this.capturer = new PacketCapture();
        boolean flag = false;
        int in;
        String aux = "Seleccione el dispositivo para capturar paquetes.\n\n";
        String arr[] = PacketCapture.lookupDevices();
        for(int i = 0; i < arr.length; i++) {
            aux += "[" + i + "] " + arr[i] + "\n";
        }
        while(!flag) {
            try {
                in = Integer.parseInt(JOptionPane.showInputDialog(aux));
                this.device = Tool.filterDevice(arr[in]);
                flag = true;
            } catch (Exception e) {
                flag = false;
                System.out.println(e);
            }
        }
        this.device = tool.filterDevice(this.capturer.lookupDevices()[2]);
        this.capturer.open(device, 65536, false, 10000);
        this.capturer.setFilter(FILTER, true);
        this.capturer.addRawPacketListener(new RawPacketHandler(this.gui));
        this.capturer.capture(PACKET_COUNT);
    }
    
}