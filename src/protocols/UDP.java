package protocols;

import utils.Tool;

/**
 *
 * @author Armando
 */
public class UDP {
    
    private String source;
    private String destination;
    private int length;
    private String checksum;
    private String data;

    public UDP(String raw[]) {
        int offset = Integer.parseInt(raw[14].substring(0, 1), 16);
        offset = offset == 4 ? 0 : offset == 6 ? 20 : 0;
        Tool tool = new Tool();
        this.source = tool.hex2dec(raw[34+offset] + raw[35+offset]);
        this.destination = tool.hex2dec(raw[36+offset] + raw[37+offset]);
        this.length = Integer.parseInt(raw[38+offset] + raw[39+offset], 16);
        this.checksum = raw[40+offset] + raw[41+offset];
        this.data = "";
        for(int i = 42+offset; i < this.length+42+offset-8; i++) {
            this.data += raw[i] + " ";
        }
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