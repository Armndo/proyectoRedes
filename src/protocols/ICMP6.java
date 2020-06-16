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
public class ICMP6 {
    
    private String[] type_code;
    //0: type
    //1: code
    private String checksum;
    private String messagebody;
    
    public ICMP6(String[] raw){
        this.type_code = Tool.ICMP6Type(Tool.hex2dec(raw[54]),Tool.hex2dec(raw[55]));
        this.type_code[1] = this.type_code[1].equals("") ? "N/A" : this.type_code[1];
        this.checksum = raw[56]+raw[57];
        this.messagebody = Tool.arrayConcater(raw, 58);
    }   

    public String[] getType_code() {
        return type_code;
    }

    public void setType_code(String[] type_code) {
        this.type_code = type_code;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getMessagebody() {
        return messagebody;
    }

    public void setMessagebody(String messagebody) {
        this.messagebody = messagebody;
    }
    
    @Override
    public String toString(){
        return "ICMP6 {\n"
                + "\tType: "+this.type_code[0]+"\n"
                + "\tCode: "+this.type_code[1]+"\n"
                + "\tChecksum: "+this.checksum+"\n"
                + "\tMessage body: "+this.messagebody+"\n"
                + "}\n";
    }
}
