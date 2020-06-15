/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocols;

import utils.Tool;

/**
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 * 
 */
public class TCP {
    
    private int source;
    private int destination;
    private String SEQ;
    private String ACK;
    private int headerLength;
    private String reserved;
    private String flags;
    private int size;
    private String checksum;
    private int urgent;
    private String data;

    public TCP(String raw[]) {
        Tool tool = new Tool();
        this.source = tool.hex2dec(new String[]{raw[34], raw[35]});
        this.destination = tool.hex2dec(new String[]{raw[36], raw[37]});
        this.SEQ = raw[38] + raw[39] + raw[40] + raw[41];
        this.ACK = raw[42] + raw[43] + raw[44] + raw[45];
        this.headerLength = tool.hex2dec(raw[46].substring(0, 1))*4;
        String aux[] = tool.hex2bin(raw[46].substring(1, 2), 3);
        this.reserved = aux[0];
        this.flags = aux[1] + tool.hex2bin(raw[47]);
        this.size = tool.hex2dec(new String[]{raw[48], raw[49]});
        this.checksum = raw[50] + raw[51];
        this.urgent = tool.hex2dec(new String[]{raw[52], raw[53]});
        this.data = "";
        for(int i = 54; i < raw.length; i++) {
            this.data += raw[i] + " ";
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

    public String getSEQ() {
        return SEQ;
    }

    public void setSEQ(String SEQ) {
        this.SEQ = SEQ;
    }

    public String getACK() {
        return ACK;
    }

    public void setACK(String ACK) {
        this.ACK = ACK;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public int getUrgent() {
        return urgent;
    }

    public void setUrgent(int urgent) {
        this.urgent = urgent;
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
            + "TCP {\n"
                + "\tsource: " + this.source + "\n"
                + "\tdestination: " + this.destination + "\n"
                + "\tSEQ: " + this.SEQ + "\n"
                + "\tACK: " + this.ACK + "\n"
                + "\theaderLength: " + this.headerLength + "\n"
                + "\treserved: " + this.reserved + "\n"
                + "\tflags: " + this.flags + "\n"
                + "\tsize: " + this.size + "\n"
                + "\tchecksum: " + this.checksum + "\n"
                + "\turgent: " + this.urgent + "\n"
                + "\tdata: " + this.data + "\n"
            + "}\n";
    }
    
}
