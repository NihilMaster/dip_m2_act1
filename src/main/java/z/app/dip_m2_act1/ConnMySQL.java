package z.app.dip_m2_act1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author M
 */
public class ConnMySQL { //Singleton
    private static final String url_mysql="jdbc:mysql://localhost:3306/dip_m2_act1";
    private static final String usr_mysql ="root";
    private static final String pas_mysql="";
    private static Connection conex = null;        
    private static ConnMySQL con = null;

    public ConnMySQL() {
    }
    
    public static ConnMySQL getInstance(){       
        
        if(con==null){
            con=new ConnMySQL();
            try {
                conex = DriverManager.getConnection(url_mysql, usr_mysql, pas_mysql);        
            } catch (SQLException e) {System.out.println("Error al conectar con la base de datos.\n" + e.getMessage());}        
        }
        return con;
    }
    
    public Connection getCon(){
        return ConnMySQL.conex;
    }
    
    public void closeCon(){
        try {
            ConnMySQL.conex.close();
        } catch (SQLException e) {
            System.out.println("e: "+e);
        }
    }
}
