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
 *  González Gonzáles Armando
 * 
 */
public class Ethernet {
    
    private String source;
    private String destination;
    private String type;
    private boolean valid;

    public Ethernet(String raw[]) {
        this.source = Tool.macConverter(raw, 0);
        this.destination = Tool.macConverter(raw, 6);
        this.type = raw[12] + raw[13];
        this.type = this.type.equals("0800") ? "IPv4" : this.type.equals("86DD") ? "IPv6" : null;
        this.valid = this.type != null;
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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    @Override
    public String toString() {
        return ""
            + "Ethernet {\n"
                + "\tsource: " + this.source + "\n"
                + "\tdestination: " + this.destination + "\n"
                + "\ttype: " + this.type + "\n"
                + "\tvalid: " + this.valid + "\n"
            + "}\n";
    }
        
}