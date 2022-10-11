package z.app.dip_m2_act1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author M
 */
public class Inventario {
    
    private Integer total=0;
    private final Map<String, Integer> m_final = new HashMap<>();

    public Inventario() {        
        Map<String, Integer> m_mysql = mysql();
        Map<String, Integer> m_psql = psql();
        m_final.putAll(m_psql);
        m_final.putAll(m_mysql);
        System.out.printf("Total: %d\n",total);
    }

    public Integer getTotal() {
        return total;
    }
    
    public Map<String, Integer> getM(){
        return m_final;
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
            // statement.close();
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
            // statement.close();
            // connps.closeCon();
        } catch (SQLException ex) {
            System.out.println("Error, no se ha podido cargar PostgreSQL JDBC Driver:\n"+ex);
        }
        
        return m;
    }
}
