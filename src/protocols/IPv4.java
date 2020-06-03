package protocols;

import utils.Tool;

/**
 *
 * @author Armando
 */
public class IPv4 {
    
    private int version;
    private int IHL;
    private String DSCP;
    private String ECN;
    private int length;
    private String identification;
    private String flags;
    private String offset;
    private int TTL;
    private String protocol;
    private String checksum;
    private String source;
    private String destination;

    public IPv4(String raw[]) {
        Tool tool = new Tool();
        this.version = Integer.parseInt(raw[14].substring(0, 1), 16);
        this.IHL = Integer.parseInt(raw[14].substring(1, 2))*4;
        String aux[] = tool.hex2bin(raw[16], 6);
        this.DSCP = aux[0];
        this.ECN = aux[1];
        this.length = tool.hex2dec(new String[]{raw[16], raw[17]});
        this.identification = raw[18] + raw[19];
        aux = tool.hex2bin(new String[]{raw[20], raw[21]}, 3);
        this.flags = aux[0];
        this.offset = aux[1];
        this.TTL = Integer.parseInt(raw[22], 16);
        this.protocol = tool.protocolType(raw[23]);
        this.checksum = raw[24] + raw[25];
        this.source = tool.ipConverter(new String[]{raw[26], raw[27], raw[28], raw[29]});
        this.destination = tool.ipConverter(new String[]{raw[30], raw[31], raw[32], raw[33]});
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getIHL() {
        return IHL;
    }

    public void setIHL(int IHL) {
        this.IHL = IHL;
    }

    public String getDSCP() {
        return DSCP;
    }

    public void setDSCP(String DSCP) {
        this.DSCP = DSCP;
    }

    public String getECN() {
        return ECN;
    }

    public void setECN(String ECN) {
        this.ECN = ECN;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
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
            + "IPv4 {\n"
                + "\tversion: " + this.version + "\n"
                + "\tIHL: " + this.IHL + "\n"
                + "\tDSCP: " + this.DSCP + "\n"
                + "\tECN: " + this.ECN + "\n"
                + "\tlength: " + this.length + "\n"
                + "\tidentification: " + this.identification + "\n"
                + "\tflags: " + this.flags + "\n"
                + "\toffset: " + this.offset + "\n"
                + "\tTTL: " + this.TTL + "\n"
                + "\tprotocol: " + this.protocol + "\n"
                + "\tchecksum: " + this.checksum + "\n"
                + "\tsource: " + this.source + "\n"
                + "\tdestination: " + this.destination + "\n"
            + "}\n";
    }
    
}