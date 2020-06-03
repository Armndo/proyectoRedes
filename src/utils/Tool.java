/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Armando
 */
public class Tool {
    
    public int hex2dec(String hex) {
        return Integer.parseInt(hex, 16);
    }
    
    public int hex2dec(String hex[]) {
        String dec = "";
        for(String string : hex)
            dec += string;
        return Integer.parseInt(dec, 16);
    }
    
    public String hex2bin(String hex) {
        String bin = "";
        char arr[] = hex.toCharArray();
        for (char c : arr) {
            switch(c) {
                case '0':
                    bin += "0000";
                    break;
                case '1':
                    bin += "0001";
                    break;
                case '2':
                    bin += "0010";
                    break;
                case '3':
                    bin += "0011";
                    break;
                case '4':
                    bin += "0100";
                    break;
                case '5':
                    bin += "0101";
                    break;
                case '6':
                    bin += "0110";
                    break;
                case '7':
                    bin += "0111";
                    break;
                case '8':
                    bin += "1000";
                    break;
                case '9':
                    bin += "1001";
                    break;
                case 'A':
                    bin += "1010";
                    break;
                case 'B':
                    bin += "1011";
                    break;
                case 'C':
                    bin += "1100";
                    break;
                case 'D':
                    bin += "1101";
                    break;
                case 'E':
                    bin += "1110";
                    break;
                case 'F':
                    bin += "1111";
                    break;
                default:
                    bin += "0000";
                    break;
            }
        }
        return bin;
    }
    
    public String[] hex2bin(String hex, int index) {
        String arr[] = new String[2];
        String aux = hex2bin(hex);
        arr[0] = aux.substring(0, index);
        arr[1] = aux.substring(index, aux.length());
        return arr;
    }
    
    public String[] hex2bin(String hex[], int index) {
        String arr[] = new String[2];
        String aux = "";
        for (String string : hex) {
            aux += hex2bin(string);
        }
        arr[0] = aux.substring(0, index);
        arr[1] = aux.substring(index, aux.length());
        return arr;
    }
    
    public String hex2bin(String hex[]) {
        String aux = "";
        for (String string : hex)
            aux += hex2bin(string);
        return aux;
    }
    
    public String macConverter(String raw[], int index) {
        String addr = "";
        for(int i = index; i < index+6; i++) {
            addr += raw[i] + ":";
        }
        return addr.substring(0, addr.length()-1);
    }
    
    public String ipConverter(String raw[], int index) {
        String addr = "";
        for(int i = index; i < index+4; i++) {
            addr += Integer.parseInt(raw[i], 16) + ".";
        }
        return addr.substring(0, addr.length()-1);
    }
    
    public String ip6Converter(String raw[], int index) {
        String addr = "";
        String aux = "";
        for(int i = index, j = 1; i < index+16; i++, j++) {
            aux += raw[i];
            if((j & 1) == 0) {
                addr += aux + ":";
                aux = "";
            }
        }
        return addr.substring(0, addr.length()-1);
    }
    
    public String ipType(String type) {
        return type.equals("0800") ? "ipv4" : type.equals("86DD") ? "ipv6" : "error";
    }
    
    public String protocolType(String protocol) {
        String str = "";
        switch(protocol) {
            case "00":
                str = "HOPOPT";
                break;
            case "01":
                str = "ICMP";
                break;
            case "02":
                str = "IGMP";
                break;
            case "03":
                str = "GGP";
                break;
            case "04":
                str = "IP-in-IP";
                break;
            case "05":
                str = "ST";
                break;
            case "06":
                str = "TCP";
                break;
            case "07":
                str = "CBT";
                break;
            case "08":
                str = "EGP";
                break;
            case "09":
                str = "IGP";
                break;
            case "0A":
                str = "BBN-RCC-MON";
                break;
            case "0B":
                str = "NVP-II";
                break;
            case "0C":
                str = "PUP";
                break;
            case "0D":
                str = "ARGUS";
                break;
            case "0E":
                str = "EMCON";
                break;
            case "0F":
                str = "XNET";
                break;
            case "10":
                str = "CHAOS";
                break;
            case "11":
                str = "UDP";
                break;
            case "12":
                str = "MUX";
                break;
            case "13":
                str = "DCN-MEAS";
                break;
            case "14":
                str = "HMP";
                break;
            case "15":
                str = "PRM";
                break;
            case "16":
                str = "XNS-IDP";
                break;
            case "17":
                str = "TRUNK-1";
                break;
            case "18":
                str = "TRUNK-2";
                break;
            case "19":
                str = "LEAF-1";
                break;
            case "1A":
                str = "LEAF-2";
                break;
            case "1B":
                str = "RDP";
                break;
            case "1C":
                str = "IRTP";
                break;
            case "1D":
                str = "ISO-TP4";
                break;
            case "1E":
                str = "NETBLT";
                break;
            case "1F":
                str = "MFE-NSP";
                break;
            case "20":
                str = "MERIT-INP";
                break;
            case "21":
                str = "DCCP";
                break;
            case "22":
                str = "3PC";
                break;
            case "23":
                str = "IDPR";
                break;
            case "24":
                str = "XTP";
                break;
            case "25":
                str = "DDP";
                break;
            case "26":
                str = "IDPR-CMTP";
                break;
            case "27":
                str = "TP++";
                break;
            case "28":
                str = "IL";
                break;
            case "29":
                str = "IPv6";
                break;
            case "2A":
                str = "SDRP";
                break;
            case "2B":
                str = "IPv6-Route";
                break;
            case "2C":
                str = "IPv6-Frag";
                break;
            case "2D":
                str = "IDRP";
                break;
            case "2E":
                str = "RSVP";
                break;
            case "2F":
                str = "GREs";
                break;
            case "30":
                str = "DSR";
                break;
            case "31":
                str = "BNA";
                break;
            case "32":
                str = "ESP";
                break;
            case "33":
                str = "AH";
                break;
            case "34":
                str = "I-NLSP";
                break;
            case "35":
                str = "SwIPe";
                break;
            case "36":
                str = "NARP";
                break;
            case "37":
                str = "MOBILE";
                break;
            case "38":
                str = "TLSP";
                break;
            case "39":
                str = "SKIP";
                break;
            case "3A":
                str = "IPv6-ICMP";
                break;
            case "3B":
                str = "IPv6-NoNxt";
                break;
            case "3C":
                str = "IPv6-Opts";
                break;
            case "3D":
                str = "";
                break;
            case "3E":
                str = "CFTP";
                break;
            case "3F":
                str = "";
                break;
            case "40":
                str = "SAT-EXPAK";
                break;
            case "41":
                str = "KRYPTOLAN";
                break;
            case "42":
                str = "RVD";
                break;
            case "43":
                str = "IPPC";
                break;
            case "44":
                str = "";
                break;
            case "45":
                str = "SAT-MON";
                break;
            case "46":
                str = "VISA";
                break;
            case "47":
                str = "IPCU";
                break;
            case "48":
                str = "CPNX";
                break;
            case "49":
                str = "CPHB";
                break;
            case "4A":
                str = "WSN";
                break;
            case "4B":
                str = "PVP";
                break;
            case "4C":
                str = "BR-SAT-MON";
                break;
            case "4D":
                str = "SUN-ND";
                break;
            case "4E":
                str = "WB-MON";
                break;
            case "4F":
                str = "WB-EXPAK";
                break;
            case "50":
                str = "ISO-IP";
                break;
            case "51":
                str = "VMTP";
                break;
            case "52":
                str = "SECURE-VMTP";
                break;
            case "53":
                str = "VINES";
                break;
            case "54":
                str = "TTP";
                break;
            case "55":
                str = "NSFNET-IGP";
                break;
            case "56":
                str = "DGP";
                break;
            case "57":
                str = "TCF";
                break;
            case "58":
                str = "EIGRP";
                break;
            case "59":
                str = "OSPF";
                break;
            case "5A":
                str = "Sprite-RPC";
                break;
            case "5B":
                str = "LARP";
                break;
            case "5C":
                str = "MTP";
                break;
            case "5D":
                str = "AX.25";
                break;
            case "5E":
                str = "OS";
                break;
            case "5F":
                str = "MICP";
                break;
            case "60":
                str = "SCC-SP";
                break;
            case "61":
                str = "ETHERIP";
                break;
            case "62":
                str = "ENCAP";
                break;
            case "63":
                str = "";
                break;
            case "64":
                str = "GMTP";
                break;
            case "65":
                str = "IFMP";
                break;
            case "66":
                str = "PNNI";
                break;
            case "67":
                str = "PIM";
                break;
            case "68":
                str = "ARIS";
                break;
            case "69":
                str = "SCPS";
                break;
            case "6A":
                str = "QNX";
                break;
            case "6B":
                str = "A/N";
                break;
            case "6C":
                str = "IPComp";
                break;
            case "6D":
                str = "SNP";
                break;
            case "6E":
                str = "Compaq-Peer";
                break;
            case "6F":
                str = "IPX-in-IP";
                break;
            case "70":
                str = "VRRP";
                break;
            case "71":
                str = "PGM";
                break;
            case "72":
                str = "";
                break;
            case "73":
                str = "L2TP";
                break;
            case "74":
                str = "DDX";
                break;
            case "75":
                str = "IATP";
                break;
            case "76":
                str = "STP";
                break;
            case "77":
                str = "SRP";
                break;
            case "78":
                str = "UTI";
                break;
            case "79":
                str = "SMP";
                break;
            case "7A":
                str = "SM";
                break;
            case "7B":
                str = "PTP";
                break;
            case "7C":
                str = "IS-IS over IPv4";
                break;
            case "7D":
                str = "FIRE";
                break;
            case "7E":
                str = "CRTP";
                break;
            case "7F":
                str = "CRUDP";
                break;
            case "80":
                str = "SSCOPMCE";
                break;
            case "81":
                str = "IPLT";
                break;
            case "82":
                str = "SPS";
                break;
            case "83":
                str = "PIPE";
                break;
            case "84":
                str = "SCTP";
                break;
            case "85":
                str = "FC";
                break;
            case "86":
                str = "RSVP-E2E-IGNORE";
                break;
            case "87":
                str = "Mobility Header";
                break;
            case "88":
                str = "UDPLite";
                break;
            case "89":
                str = "MPLS-in-IP";
                break;
            case "8A":
                str = "manet";
                break;
            case "8B":
                str = "HIP";
                break;
            case "8C":
                str = "Shim6";
                break;
            case "8D":
                str = "WESP";
                break;
            case "8E":
                str = "ROHC";
                break;
            case "8F":
                str = "Ethernet";
                break;
        }
        return str;
    }
    
    public String filterDevice(String device) {
        int loc = device.lastIndexOf("}");
        return device = device.substring(0, loc+1);
    }
    
}
