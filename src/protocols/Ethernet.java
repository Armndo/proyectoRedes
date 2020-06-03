/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocols;

import utils.Tool;

/**
 *
 * @author Armando
 */
public class Ethernet {
    
    private String source;
    private String destination;
    private String type;
    private boolean isValid;

    public Ethernet(String raw[]) {
        Tool tool = new Tool();
        this.source = tool.macConverter(new String[]{raw[0], raw[1], raw[2], raw[3], raw[4], raw[5]});
        this.destination = tool.macConverter(new String[]{raw[6], raw[7], raw[8], raw[9], raw[10], raw[11]});
        this.type = raw[12] + raw[13];
        this.type = this.type.equals("0800") ? "ipv4" : this.type.equals("86DD") ? "ipv6" : null;
        this.isValid = this.type != null;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
    
    @Override
    public String toString() {
        return ""
            + "Ethernet {\n"
                + "\tsource: " + this.source + "\n"
                + "\tdestination: " + this.destination + "\n"
                + "\ttype: " + this.type + "\n"
                + "\tisValid: " + this.isValid + "\n"
            + "}\n";
    }
        
}