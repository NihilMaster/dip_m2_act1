package z.app.dip_m2_act1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author M
 */
public class Inventario {
    
    private JTable table_sum = new JTable();
    private Integer total=0;
    private final JTextField txt = new JTextField();
    
    // ConcreteObserver otxt;
    // ConcreteObservable observable;

    public Inventario() {
        // otxt=new ConcreteObserver(txt);
        // observable=new ConcreteObservable(otxt);
        
        JFrame lista = new JFrame();
        lista.setLayout(new FlowLayout());
        
        Map<String, Integer> m_mysql = mysql();
        Map<String, Integer> m_psql = psql();
        Map<String, Integer> m_final = new HashMap<>();
        m_final.putAll(m_psql);
        m_final.putAll(m_mysql);
        System.out.printf("Total: %d\n",total);
        
        table_sum = new JTable(toTableModel(m_final));
        txt.setText(total.toString());
        
        JScrollPane scrollPane = new JScrollPane(table_sum);
        lista.add(scrollPane, BorderLayout.CENTER);
        lista.add(new JLabel("Total"));
        lista.add(txt, BorderLayout.CENTER);
        lista.pack();
        lista.setVisible(true);
        lista.setSize(500,500); 
        lista.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
        
    }
    
    private Map<String, Integer> mysql(){
        Map<String, Integer> m = new HashMap<>();
        try {
            ConnMySQL connmy = ConnMySQL.getInstance();
            Statement statement = connmy.getCon().createStatement();
            
            ResultSet rs1 = statement.executeQuery("SELECT nombre, SUM(cantidad) as cantidad FROM `productos` GROUP BY nombre;");
            while (rs1.next()) {
                String nombre = rs1.getString("nombre");
                Integer cantidad = rs1.getInt("cantidad");
                m.put(nombre, cantidad);
            }
            
            ResultSet rs2 = statement.executeQuery("SELECT SUM(valor_unitario*cantidad) Total FROM productos;");
            rs2.next();
            total+=rs2.getInt("Total");
            System.out.printf("Total(Mysql): %d\n",rs2.getInt("Total"));

 
            rs1.close(); rs1.close();
            statement.close();
            // connmy.closeCon();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return m;
    }

    private Map<String, Integer> psql() {
        Map<String, Integer> m = new HashMap<>();
        try {
            ConnPSQL connps = ConnPSQL.getInstance();
            Statement statement = connps.getCon().createStatement();
            
            ResultSet rs1 = statement.executeQuery("SELECT nombre, SUM(cantidad) cantidad FROM public.productos GROUP BY 1;");
            while (rs1.next()) {
                String nombre = rs1.getString("nombre");
                Integer cantidad = rs1.getInt("cantidad");
                m.put(nombre, cantidad);
            }
            
            ResultSet rs2 = statement.executeQuery("SELECT SUM(valor_unitario*cantidad) Total FROM public.productos;");
            rs2.next();
            total+=rs2.getInt("Total");
            System.out.printf("Total(Psql): %d\n",rs2.getInt("Total"));

            rs1.close(); rs2.close();
            statement.close();
            connps.closeCon();
        } catch (SQLException ex) {
            System.out.println("Error, no se ha podido cargar PostgreSQL JDBC Driver:\n"+ex);
        }
        
        return m;
    }
    
    public static TableModel toTableModel(Map<?,?> map) {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] { "Producto", "Cantidad" }, 0
        );
        for (Map.Entry<?,?> entry : map.entrySet()) {
            model.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
    return model;
}
}
