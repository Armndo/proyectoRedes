/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import protocols.Ethernet;
import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;
import net.sourceforge.jpcap.util.HexHelper;
import protocols.IPv4;
import protocols.IPv6;
import protocols.UDP;


/**
 * jpcap Tutorial - Example 2
 *
 * @author Jonas Lehmann and Patrick Charles
 * @version $Revision: 1.3 $
 * @lastModifiedBy $Author: pcharles $
 * @lastModifiedAt $Date: 2002/02/18 15:33:00 $
 */
public class Example2 
{
  private static final int INFINITE = -1;
  private static final int PACKET_COUNT = INFINITE; 

  // BPF filter for capturing any packet
  private static final String FILTER = "ip6";

  private PacketCapture m_pcap;
  private String m_device;

  public Example2() throws Exception {
    // Step 1:  Instantiate Capturing Engine
    m_pcap = new PacketCapture();

    // Step 2:  Check for devices 
    m_device = filterDevice(m_pcap.lookupDevices()[2]);
//      for (String s : m_pcap.lookupDevices()) {
//          System.out.println(s);
//      }
//    System.out.println("Device: " + m_device);

    // Step 3:  Open Device for Capturing (requires root)
    m_pcap.open(m_device, 65536, true, 1000);

    // Step 4:  Add a BPF Filter (see tcpdump documentation)
    m_pcap.setFilter(FILTER, true);

    // Step 5:  Register a Listener for Raw Packets
    m_pcap.addRawPacketListener(new RawPacketHandler());
    //m_pcap.addPacketListener(new PacketHandler());

    // Step 6:  Capture Data (max. PACKET_COUNT packets)
    m_pcap.capture(PACKET_COUNT);
  }

    private String filterDevice(String device) {
        int loc = device.lastIndexOf("}");
        return device = device.substring(0, loc+1);
    }

  public static void main(String[] args) {
      //System.out.println(System.getProperty("java.library.path"));
    try {
      Example2 example = new Example2();
    } catch(Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}


class RawPacketHandler implements RawPacketListener 
{
  private static int m_counter = 0;

  public void rawPacketArrived(RawPacket data) {
    //m_counter++;
    //System.out.println("Packet " + m_counter + "\n" + data + "\n");
      //System.out.println(data.getTimeval());
      //System.out.println(HexHelper.toString(data.getData()));
      //System.out.println(data);
      //System.out.println(data);
      String raw = HexHelper.toString(data.getData()).toUpperCase();
      System.out.println(raw + "\n");
      String arr[] = raw.split(" ");
      Ethernet eth = new Ethernet(arr);
//      IPv4 ip = new IPv4(arr);
      IPv6 ip = new IPv6(arr);
//      UDP udp = new UDP(arr);
      System.out.println(eth);
//      if(eth.isIsValid() && eth.getType().equals("ipv4")) {
        System.out.println(ip);
//        if(ip.getProtocol().equals("UDP")) {
//            System.out.println(udp);
//        }
//      }
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