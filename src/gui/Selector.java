/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sourceforge.jpcap.capture.PacketCapture;
import utils.Tool;
/**
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González Gonzáles Armando
 * 
 */
public class Selector {
    private JLabel texto;
    private JTextField auxiliar;
    private JComboBox comboBox;
    private JFrame frame;
    private JLabel background;
    private JButton empezarBtn;
    private String dispositivo;
    private String[] dispositivos;
    private PacketCapture m_pcap;
    private Object lock = new Object();
    private boolean closed = false;
    
    public Selector() throws Exception{
        comboBox = new JComboBox();
        texto = new JLabel("Dispositivo de red: ");
        empezarBtn = new JButton("Capturar");
        
        m_pcap = new PacketCapture();
        dispositivos = this.m_pcap.lookupDevices();
        
        for(String a : dispositivos){
            comboBox.addItem(Tool.filterDevice(a));
        }
        empezarBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispositivo = comboBox.getSelectedItem().toString();
                frame.dispose();
                closed = true;
                frame.dispose();;
            }
        });
        
        background = new JLabel("Sniffer");
        JPanel linea1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linea1.add(texto);
        linea1.add(comboBox);
        JPanel linea2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linea2.add(empezarBtn);
        
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.add(linea1, BorderLayout.NORTH);
        borderPanel.add(linea2, BorderLayout.SOUTH);
        
        JPanel contenedor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contenedor.add(borderPanel);
        
        frame = new JFrame();
        frame.setTitle("Seleccione el dispositivo de red");
        frame.getContentPane().add(contenedor);
        frame.pack();
        frame.setVisible(true);
    
        
    }
    
    public String getDispositivo(){
        return this.dispositivo;
    }
    public Boolean getStatus(){
        return this.closed;
    }
}
