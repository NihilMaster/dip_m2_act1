package z.app.dip_m2_act1;

import java.sql.*;
/**
 *
 * @author carlo
 */
public class ConnDB {
        
    
    private static final String url_mysql="jdbc:mysql://localhost:3306/dip_m2_act1";
    private static final String usr_mysql ="root";
    private static final String pas_mysql="";
    
    private static final String url_psql="jdbc:postgresql://190.60.242.160:5432/desarrollo";
    private static final String usr_psql ="postgres";
    private static final String pas_psql="clinicaDEV-2022*";
     
   
    public static Connection conexionMySQL(){
        Connection conex = null;
        try {
            conex = DriverManager.getConnection(url_mysql, usr_mysql, pas_mysql);        
        } catch (SQLException e) {System.out.println("Error al conectar con la base de datos.\n" + e.getMessage().toString());}   
        if(conex==null){System.out.println("NULíSIMO");}
        return conex;
    }
    
    public static Connection conexionPSQL(){
        Connection conex = null;
        try {
            conex = DriverManager.getConnection(url_psql, usr_psql, pas_psql);        
        } catch (SQLException e) {System.out.println("Error al conectar con la base de datos.\n" + e.getMessage().toString());}   
        return conex;
    }
}



