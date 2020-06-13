/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import gui.GUI;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.util.HexHelper;
import protocols.DataPacket;

/**
 *
 * @author Armando
 */
public class RawPacketHandler implements RawPacketListener {
    
    private static int m_counter = 0;
    private GUI gui;
    private long init;
    
    public RawPacketHandler(GUI gui) {
        this.gui = gui;
        init = System.currentTimeMillis();
    }
    
    public void rawPacketArrived(RawPacket data) {
        m_counter++;
        String raw = HexHelper.toString(data.getData()).toUpperCase();
        DataPacket dp = new DataPacket(m_counter, raw);
        if(dp.getEthernet().isValid()) {
            DefaultTableModel model = (DefaultTableModel)gui.getTable().getModel();
            Vector vector = new Vector();
            vector.add((DataPacket)dp);
            vector.add(String.format("%.6f", ((long)(Double.parseDouble(data.getTimeval().toString().substring(0, data.getTimeval().toString().length()-1))*1000000)-init*1000)/1000000.0));
            String aux = "";
            aux = dp.getIpv4() != null ? dp.getIpv4().getSource() : dp.getIpv6() != null ? dp.getIpv6().getSource() : "ERROR";
            vector.add(aux);
            aux = dp.getIpv4() != null ? dp.getIpv4().getDestination() : dp.getIpv6() != null ? dp.getIpv6().getDestination() : "ERROR";
            vector.add(aux);
            aux = dp.getIpv4() != null ? dp.getIpv4().getProtocol() : dp.getIpv6() != null ? dp.getIpv6().getNextHeader() : "ERROR";
            vector.add(aux);
            vector.add(data.getData().length);
            aux = "";
            if(dp.getUdp() != null) {
                aux = dp.getUdp().getSource() + " → " + dp.getUdp().getDestination() + " Len=" + dp.getUdp().getLength();
            }
            if(dp.getTcp() != null) {
                char arr[] = dp.getTcp().getFlags().toCharArray();
                if(arr[5] == '1') {
                    aux = "Application Data";
                } else
                    aux = dp.getTcp().getSource() + " → " + dp.getTcp().getDestination() + " [ACK] Len=" + dp.getTcp().getLength();
            }
            if(dp.getIcmp() != null) {
                aux = dp.getIcmp().getType() + " id=0x" + dp.getIcmp().getIdentifier();
            }
            if(dp.getIcmp6() != null) {
                aux = dp.getIcmp6().getType_code()[0] + " for " + dp.getIpv6().getSource() + " from " + dp.getIpv6().getDestination();
            }
            vector.add(aux);
            model.addRow(vector);
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(RawPacketHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
}