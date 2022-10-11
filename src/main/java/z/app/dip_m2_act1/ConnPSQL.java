package z.app.dip_m2_act1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author M
 */
public class ConnPSQL {
    private static final String url_psql="jdbc:postgresql://190.60.242.160:5432/desarrollo";
    private static final String usr_psql ="postgres";
    private static final String pas_psql="clinicaDEV-2022*";
    private static Connection conex = null;        
    private static ConnPSQL con = null;

    public ConnPSQL() {
    }
    
    public static ConnPSQL getInstance(){       
        
        if(con==null){
            con=new ConnPSQL();
            try {
                conex = DriverManager.getConnection(url_psql, usr_psql, pas_psql);        
            } catch (SQLException e) {System.out.println("Error al conectar con la base de datos.\n" + e.getMessage());}        
        }
        return con;
    }
    
    public Connection getCon(){
        return ConnPSQL.conex;
    }
    
    public void closeCon(){
        try {
            ConnPSQL.conex.close();
        } catch (SQLException e) {
            System.out.println("e: "+e);
        }
    }
}
