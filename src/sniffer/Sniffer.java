/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import protocols.Ethernet;
import protocols.IPv4;
import protocols.UDP;

/**
 *
 * @author Armando
 */
public class Sniffer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String arr[] = "01 00 5E 7F FF FA 04 ED 33 15 51 E9 08 00 45 00 00 A5 13 41 00 00 04 11 F2 62 C0 A8 00 02 EF FF FF FA EA 22 07 6C 00 91 6A 7A 4D 2D 53 45 41 52 43 48 20 2A 20 48 54 54 50 2F 31 2E 31 0D 0A 48 6F 73 74 3A 20 32 33 39 2E 32 35 35 2E 32 35 35 2E 32 35 30 3A 31 39 30 30 0D 0A 53 54 3A 20 75 72 6E 3A 73 63 68 65 6D 61 73 2D 75 70 6E 70 2D 6F 72 67 3A 64 65 76 69 63 65 3A 49 6E 74 65 72 6E 65 74 47 61 74 65 77 61 79 44 65 76 69 63 65 3A 31 0D 0A 4D 61 6E 3A 20 22 73 73 64 70 3A 64 69 73 63 6F 76 65 72 22 0D 0A 4D 58 3A 20 33 0D 0A 0D 0A".split(" ");
        System.out.println(new Ethernet(arr));
        System.out.println(new IPv4(arr));
        System.out.println(new UDP(arr));
    }
    
}
