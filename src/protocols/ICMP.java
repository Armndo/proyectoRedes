package protocols;

import utils.Tool;

/**
 *
 * @author Armando
 */
public class ICMP {
    
    private String type;
    private int code;
    private String checksum;
    private int identifier;
    private int sequenceNumber;
    private String data;

    public ICMP(String raw[]) {
        Tool tool = new Tool();
        this.type = tool.icmpType(tool.hex2dec(raw[34]));
        this.code = tool.hex2dec(raw[35]);
        this.checksum = raw[36] + raw[37];
        this.identifier = tool.hex2dec(new String[]{raw[38], raw[39]});
        this.sequenceNumber = tool.hex2dec(new String[]{raw[40], raw[41]});
        this.data = "";
        for(int i = 42; i < raw.length; i++) {
            this.data += raw[i] + " ";
        }
    }
    @Override
    public String toString() {
        return ""
            + "ICMP {\n"
                + "\ttype: " + this.type + "\n"
                + "\tcode: " + this.code + "\n"
                + "\tchecksum: " + this.checksum + "\n"
                + "\tidentifier: " + this.identifier + "\n"
                + "\tsequenceNumber: " + this.sequenceNumber + "\n"
                + "\tdata: " + this.data + "\n"
            + "}\n";
    }
    
}