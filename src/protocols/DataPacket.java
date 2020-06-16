package protocols;

/**
 * 
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 */
public class DataPacket {
    
    private int id;
    private String raw;
    private Ethernet ethernet;
    private IPv4 ipv4;
    private IPv6 ipv6;
    private ICMP icmp;
    private ICMP6 icmp6;
    private TCP tcp;
    private UDP udp;

    public DataPacket(int id, String raw) {
        this.id = id;
        this.raw = raw;
        String arr[] = this.raw.split(" ");
        this.ethernet = new Ethernet(arr);
        this.ipv4 = ethernet.getType() != null && ethernet.getType().equals("IPv4") ? new IPv4(arr) : null;
        this.ipv6 = ethernet.getType() != null && ethernet.getType().equals("IPv6") ? new IPv6(arr) : null;
        this.icmp = ipv4 != null && ipv4.getProtocol().equals("ICMP") ? new ICMP(arr) : null;
        this.icmp6 = ipv6 != null && ipv6.getNextHeader().equals("IPv6-ICMP") ? new ICMP6(arr) : null;
        this.tcp = ipv4 != null && ipv4.getProtocol().equals("TCP") ? new TCP(arr) : null;
        this.udp = (ipv4 != null && ipv4.getProtocol().equals("UDP")) || (ipv6 != null && ipv6.getNextHeader().equals("UDP")) ? new UDP(arr) : null;
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
        //str += this.raw + "\n";
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
                    + "    Checksum: 0x" + this.udp.getChecksum()
                    + (this.udp.getData().length() > 0 ? "\nData:\n" + this.udp.getData() : "")
                    + "";
        }
        if(this.tcp != null) {
            str += "Transmission Control Protocol:\n"
                    + "    Source Port: " + this.tcp.getSource() + "\n"
                    + "    Destination Port: " + this.tcp.getDestination() + "\n"
                    + "    Length: " + this.tcp.getLength() + " bytes\n"
                    + "    SEQ: 0x" + this.tcp.getSEQ() + "\n"
                    + "    ACK: 0x" + this.tcp.getACK() + "\n"
                    + "    Header Length: " + this.tcp.getHeaderLength() + " bytes\n"
                    + "    Reserved: " + this.tcp.getReserved() + "\n"
                    + "    Flags: " + this.tcp.getFlags() + "\n"
                    + "    Window Size: " + this.tcp.getSize() + " bytes\n"
                    + "    Checksum: 0x" + this.tcp.getChecksum() + "\n"
                    + "    Urgent pointer: " + this.tcp.getUrgent()
                    + (this.tcp.getOptions().length() > 0 ? "\nOptions:\n" + this.tcp.getOptions() : "")
                    + (this.tcp.getData().length() > 0 ? "\nData:\n" + this.tcp.getData() : "")
                    + "";
        }
        if(this.icmp != null) {
            str += "Internet Control Message Protocol:\n"
                    + "    Type: " + this.icmp.getType() + "\n"
                    + "    Code: " + this.icmp.getCode() + "\n"
                    + "    Checksum: 0x" + this.icmp.getChecksum() + "\n"
                    + "    Identifier: 0x" + this.icmp.getIdentifier() + "\n"
                    + "    Sequence Number: 0x" + this.icmp.getSequenceNumber()
                    + (this.icmp.getData().length() > 0 ? "\nData:\n" + this.icmp.getData() : "")
                    + "";
        }
        if(this.icmp6 != null) {
            str += "Internet Control Message Protocol v6:\n"
                    + "    Type: " + this.icmp6.getType_code()[0] + "\n"
                    + "    Code: " + this.icmp6.getType_code()[1] + "\n"
                    + "    Checksum: 0x" + this.icmp6.getChecksum() + "\n"
                    + "Data:\n" + this.icmp6.getMessagebody()
                    + "";
        }
        return str;
    }

    @Override
    public String toString() {
        return this.id + "";
    }
    
}