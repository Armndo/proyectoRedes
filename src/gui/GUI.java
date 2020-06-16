package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import protocols.DataPacket;

/**
 * 
 *  Proyecto Redes: Sniffer
 *  Grupo: 2CV6
 *  Integrantes:
 *  Cortés Larios Eddieson
 *  González González Armando
 */
public class GUI {
    
    private final JFrame window;
    private final JLabel tittle;
    private final JScrollPane scroll;
    private final JScrollPane scroll2;
    private final JTextArea details;
    private final JTable table;
    private final int width;
    private final int height;

    public GUI() {
        width = 1225;
        height = 700;
        
        window = new JFrame();
        tittle = new JLabel();
        scroll = new JScrollPane();
        scroll2 = new JScrollPane();
        details = new JTextArea();
        table = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"No.", "Time", "Source", "Destination", "Protocol", "Length", "Info"}));
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        window.setBounds((screen.width - width)/2, (screen.height - height)/2, width, height); 
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Sniffer");
        window.setResizable(false);
        window.setLayout(null);
        
        tittle.setFont(new Font("", 0, 36));
        tittle.setText("Sniffer");
        tittle.setHorizontalAlignment(SwingConstants.LEFT);
        tittle.setBounds(10 , 10, 1200, 36);
        
        scroll.setBounds(10, 52, 1200, 200);
        table.setFont(new Font("Courier New", 0, 12));
        table.setDefaultEditor(Object.class, null);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(20);
        tcm.getColumn(1).setPreferredWidth(20);
        tcm.getColumn(2).setPreferredWidth(100);
        tcm.getColumn(3).setPreferredWidth(100);
        tcm.getColumn(4).setPreferredWidth(20);
        tcm.getColumn(5).setPreferredWidth(20);
        tcm.getColumn(6).setPreferredWidth(250);
        DefaultTableCellRenderer hed = (DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer();
        hed.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);   
        table.getColumn("No.").setCellRenderer(rightRenderer);
        table.getColumn("Length").setCellRenderer(rightRenderer);
        
        scroll2.setBounds(10, 262, 1200, 400);
        details.setLineWrap(true);
        details.setEditable(false);
        details.setFont(new Font("Courier New", Font.PLAIN, 12));
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                DataPacket sel = (DataPacket)table.getValueAt(table.getSelectedRow(), 0);
                details.setText(sel.getDetails());
                details.setCaretPosition(0);
            }
        });
        
        window.add(tittle);
        scroll2.setViewportView(details);
        scroll.setViewportView(table);
        window.add(scroll);
        window.add(scroll2);
        
        window.setVisible(true);
    }

    public JTable getTable() {
        return table;
    }
    
}
