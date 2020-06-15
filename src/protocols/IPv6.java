package protocols;

import inet.ipaddr.IPAddressString;
import utils.Tool;

/**
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 * 
 */
public class IPv6 {
    
    private int version;
    private String trafficClass;
    private String flowLabel;
    private int length;
    private String nextHeader;
    private int hopLimit;
    private String source;
    private String destination;

    public IPv6(String raw[]) {
        Tool tool = new Tool();
        this.version = Integer.parseInt(raw[14].substring(0, 1), 16);
        String aux[] = tool.hex2bin(new String[]{raw[14].substring(1, 2), raw[15], raw[16], raw[17]}, 8);
        this.trafficClass = aux[0];
        this.flowLabel = aux[1];
        this.length = tool.hex2dec(new String[]{raw[18], raw[19]});
        this.nextHeader = tool.protocolType(raw[20]);
        this.hopLimit = Integer.parseInt(raw[21], 16);
        this.source = new IPAddressString(tool.ip6Converter(raw, 22)).getAddress().toCanonicalString();
        this.destination = new IPAddressString(tool.ip6Converter(raw, 38)).getAddress().toCanonicalString();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTrafficClass() {
        return trafficClass;
    }

    public void setTrafficClass(String trafficClass) {
        this.trafficClass = trafficClass;
    }

    public String getFlowLabel() {
        return flowLabel;
    }

    public void setFlowLabel(String flowLabel) {
        this.flowLabel = flowLabel;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getNextHeader() {
        return nextHeader;
    }

    public void setNextHeader(String nextHeader) {
        this.nextHeader = nextHeader;
    }

    public int getHopLimit() {
        return hopLimit;
    }

    public void setHopLimit(int hopLimit) {
        this.hopLimit = hopLimit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    @Override
    public String toString() {
        return ""
            + "IPv6 {\n"
                + "\tversion: " + this.version + "\n"
                + "\ttrafficClass: " + this.trafficClass + "\n"
                + "\tflowLabel: " + this.flowLabel + "\n"
                + "\tlength: " + this.length + "\n"
                + "\tnextHeader: " + this.nextHeader + "\n"
                + "\thopLimit: " + this.hopLimit + "\n"
                + "\tsource: " + this.source + "\n"
                + "\tdestination: " + this.destination + "\n"
            + "}\n";
    }
    
}