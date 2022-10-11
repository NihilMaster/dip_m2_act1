package z.app.dip_m2_act1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M
 */
public class ListarProductos{
    
    private JTable table_psql = new JTable();
    private JTable table_mysql = new JTable();

    public ListarProductos() {
        JFrame lista = new JFrame();
        lista.setLayout(new FlowLayout());
        
        String[] columnNames = {"Nombre","Cantidad","Valor Unitario"};
        ArrayList<String[]> a_mysql = mysql();
        ArrayList<String[]> a_psql = postgres();
        
        DefaultTableModel dtm1 = new DefaultTableModel(columnNames, 0);
        DefaultTableModel dtm2 = new DefaultTableModel(columnNames, 0);
        table_psql = new JTable(dtm1);
        table_mysql = new JTable(dtm2);
        
        for(int i = 0; i < a_psql.size(); i++) {
            dtm1.addRow( (Object[]) a_psql.get(i));
            System.out.println(a_psql.get(i)[0]);
        }
        
        for(int i = 0; i < a_mysql.size(); i++) {
            dtm2.addRow( (Object[]) a_mysql.get(i));
            System.out.println(a_mysql.get(i)[0]);
        }
        
        table_psql.setPreferredScrollableViewportSize(new Dimension(250, 100));
        JScrollPane scrollPane_1 = new JScrollPane(table_psql);
        lista.add(new JLabel("Motor DB 1"));
        lista.getContentPane().add(scrollPane_1, BorderLayout.CENTER);  
        
        table_mysql.setPreferredScrollableViewportSize(new Dimension(250, 100));
        JScrollPane scrollPane2 = new JScrollPane(table_mysql);
        lista.getContentPane().add(new JLabel("Motor DB 2"));
        lista.getContentPane().add(scrollPane2, BorderLayout.CENTER);  
        
        lista.addWindowListener(new WindowAdapter() {           
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);               
            }
        });
        
        lista.pack();
        lista.setVisible(true);
        lista.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
        
    }
    
    private ArrayList<String[]> postgres(){
        ArrayList<String[]> r = new ArrayList<>();
        try {
            ConnDB conn = new ConnDB();
            Statement statement = conn.conexionPSQL().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.productos");
            
            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String cantidad = rs.getString("cantidad");
                Integer valor = rs.getInt("valor_unitario");

                String[] x = new String[3];
                x[0]=nombre; x[1]=cantidad; x[2]=valor.toString();
                r.add(x);
                System.out.println(String.format("%d, %s %s, %d", id, nombre, cantidad, valor));
            }

            rs.close();
            statement.close();
            conn.conexionPSQL().close();
        } catch (SQLException ex) {
            System.out.println("Error, no se ha podido cargar PostgreSQL JDBC Driver:\n"+ex);
        }
        return r;
    }
    
    private ArrayList<String[]> mysql(){
        ArrayList<String[]> r = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/dip_m2_act1";
        String username = "root";
        String password = "";
        try {
            ConnDB conn = new ConnDB();
            Statement statement = conn.conexionMySQL().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM productos");
            
            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String cantidad = rs.getString("cantidad");
                Integer valor = rs.getInt("valor_unitario");
                
                String[] x = new String[3];
                x[0]=nombre; x[1]=cantidad; x[2]=valor.toString();
                r.add(x);
                System.out.println(String.format("%d, %s %s, %d", id, nombre, cantidad, valor));
            }

            rs.close();
            statement.close();
            conn.conexionMySQL().close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return r;
    }
}
