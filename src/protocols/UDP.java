package protocols;

import utils.Tool;

/**
 * 
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 */
public class UDP {
    
    private int source;
    private int destination;
    private int length;
    private String checksum;
    private String data;

    public UDP(String raw[]) {
        String aux = raw[12] + raw[13];
        int offset = aux.equals("0800") ? 0 : aux.equals("86DD") ? 20 : 0;
        this.source = Tool.hex2dec(raw[34+offset] + raw[35+offset]);
        this.destination = Tool.hex2dec(raw[36+offset] + raw[37+offset]);
        this.length = Integer.parseInt(raw[38+offset] + raw[39+offset], 16);
        this.checksum = raw[40+offset] + raw[41+offset];
        this.data = "";
        for(int i = 42+offset; i < raw.length; i++) {
            this.data += (char)(Integer.parseInt(raw[i], 16));
        }
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return ""
            + "UDP {\n"
                + "\tsource: " + this.source + "\n"
                + "\tdestination: " + this.destination + "\n"
                + "\tlength: " + this.length + "\n"
                + "\tchecksum: " + this.checksum + "\n"
                + "\tdata: " + this.data + "\n"
            + "}\n";
    }
    
}