package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import net.sourceforge.jpcap.capture.PacketCapture;
import utils.Tool;

/**
 * 
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 */
public class Selector {
    
    private JFrame frame;
    private JLabel texto;
    private JComboBox devices;
    private JButton button;
    private String dispositivo;
    private String[] dispositivos;
    private PacketCapture capturer;
    private boolean closed = false;
    private final int width;
    private final int height;
    
    public Selector() throws Exception{
        width = 600;
        height = 100;
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame = new JFrame();
        texto = new JLabel();
        devices = new JComboBox();
        button = new JButton();
        
        frame.setBounds((screen.width - width)/2, (screen.height - height)/2, width, height); 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Select Network device");
        frame.setResizable(false);
        frame.setLayout(null);
        
        texto.setText("Network Device:");
        texto.setHorizontalAlignment(SwingConstants.LEFT);
        texto.setBounds(10 , 10, 100, 20);
        
        devices.setBounds(110, 10, 475, 20);
        
        button.setText("Capture");
        button.setBounds(width/2-40, 40, 80, 20);
        button.setHorizontalAlignment(JLabel.CENTER);
        
        capturer = new PacketCapture();
        dispositivos = this.capturer.lookupDevices();
        
        for(String a : dispositivos){
            devices.addItem(a);
        }
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispositivo = Tool.filterDevice(devices.getSelectedItem().toString());
                frame.dispose();
                closed = true;
                frame.dispose();;
            }
        });
        
//        JPanel linea1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        linea1.add(texto);
//        linea1.add(comboBox);
//        JPanel linea2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        linea2.add(empezarBtn);
//        
//        JPanel borderPanel = new JPanel(new BorderLayout());
//        borderPanel.add(linea1, BorderLayout.NORTH);
//        borderPanel.add(linea2, BorderLayout.SOUTH);
//        
//        JPanel contenedor = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        contenedor.add(borderPanel);
        
        frame.add(texto);
        frame.add(devices);
        frame.add(button);
        frame.setVisible(true);
    
        
    }
    
    public String getDispositivo(){
        return this.dispositivo;
    }
    public Boolean getStatus(){
        return this.closed;
    }
}
