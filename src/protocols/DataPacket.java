package protocols;

import net.sourceforge.jpcap.util.Timeval;
/**
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 * 
 */
public class DataPacket {
    
    private Timeval time;
    private int id;
    private String raw;
    private Ethernet ethernet;
    private IPv4 ipv4;
    private IPv6 ipv6;
    private ICMP icmp;
    private ICMP6 icmp6;
    private TCP tcp;
    private UDP udp;

    public DataPacket(Timeval time, int id, String raw, Ethernet ethernet, Object ip, Object icmp, Object icmp6, Object tcp, Object udp) {
        this.time = time;
        this.id = id;
        this.raw = raw;
        this.ethernet = ethernet;
        this.ipv4 = ip instanceof IPv4 ? (IPv4)ip : null;
        this.ipv6 = ip instanceof IPv6 ? (IPv6)ip : null;
        this.icmp = icmp instanceof ICMP ? (ICMP)icmp : null;
        this.icmp6 = icmp6 instanceof ICMP6 ? (ICMP6)icmp6 : null;
        this.tcp = tcp instanceof TCP ? (TCP)tcp : null;
        this.udp = udp instanceof UDP ? (UDP)udp : null;
    }

    public Ethernet getEthernet() {
        return ethernet;
    }

    public IPv4 getIpv4() {
        return ipv4;
    }

    public IPv6 getIpv6() {
        return ipv6;
    }

    public ICMP getIcmp() {
        return icmp;
    }

    public ICMP6 getIcmp6() {
        return icmp6;
    }

    public TCP getTcp() {
        return tcp;
    }

    public UDP getUdp() {
        return udp;
    }
    
    public String getDetails() {
        String str = "";
        
        str += ""
                + "Ethernet II:\n"
                + "    Destination: " + this.ethernet.getDestination() + "\n"
                + "    Source: " + this.ethernet.getSource() + "\n"
                + "    Type: " + this.ethernet.getType() + "\n";
        if(this.ipv4 != null) {
            str += "Internet Protocol Version 4:\n"
                    + "    Version: " + this.ipv4.getVersion() + "\n"
                    + "    Header Length: " + this.ipv4.getIHL() + " bytes\n"
                    + "    DSCP: " + this.ipv4.getDSCP() + "\n"
                    + "    ECN: " + this.ipv4.getECN() + "\n"
                    + "    Identification: 0x" + this.ipv4.getIdentification() + "\n"
                    + "    Total Length: " + this.ipv4.getLength() + " bytes\n"
                    + "    Flags: " + this.ipv4.getFlags() + "\n"
                    + "    Fragment Offset: " + this.ipv4.getOffset() + " bytes\n"
                    + "    Time to Live: " + this.ipv4.getTTL() + " seconds\n"
                    + "    Protocol: " + this.ipv4.getProtocol() + "\n"
                    + "    Checksum: 0x" + this.ipv4.getChecksum() + "\n"
                    + "    Source: " + this.ipv4.getSource() + "\n"
                    + "    Destination: " + this.ipv4.getDestination() + "\n"
                    + "";
        }
        if(this.ipv6 != null) {
            str += "Internet Protocol Version 6:\n"
                    + "    Version: " + this.ipv6.getVersion() + "\n"
                    + "    Traffic Class: " + this.ipv6.getTrafficClass() + "\n"
                    + "    Flow Label: " + this.ipv6.getFlowLabel() + "\n"
                    + "    Payload Length: " + this.ipv6.getLength() + " bytes\n"
                    + "    Next Header: " + this.ipv6.getNextHeader() + "\n"
                    + "    Hop Limit: " + this.ipv6.getHopLimit() + "\n"
                    + "    Source: " + this.ipv6.getSource() + "\n"
                    + "    Destination: " + this.ipv6.getDestination() + "\n"
                    + "";
        }
        if(this.udp != null) {
            str += "User Datagram Protocol:\n"
                    + "    Source Port: " + this.udp.getSource() + "\n"
                    + "    Destination Port: " + this.udp.getDestination() + "\n"
                    + "    Length: " + this.udp.getLength() + " bytes\n"
                    + "    Checksum: 0x" + this.udp.getChecksum() + "\n"
                    + "Data:\n" + this.udp.getData()
                    + "";
        }
        if(this.icmp != null) {
            str += "Internet Control Message Protocol:\n"
                    + "    Type: " + this.icmp.getType() + "\n"
                    + "    Code: " + this.icmp.getCode() + "\n"
                    + "    Checksum: 0x" + this.icmp.getChecksum() + "\n"
                    + "    Identifier: 0x" + this.icmp.getIdentifier() + "\n"
                    + "    Sequence Number: 0x" + this.icmp.getSequenceNumber() + "\n"
                    + "Data:\n" + this.icmp.getData()
                    + "";
        }
        return str;
    }

    @Override
    public String toString() {
        return this.id + "";
    }
    
}