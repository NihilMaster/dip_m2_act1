package z.app.dip_m2_act1;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author M
 */
public class Mdb1 implements IDataBase{

    private Registro insert;
    
    public Mdb1(Registro db) {
        this.insert=db;
        
        String insertTableSQL = "INSERT INTO PRODUCTOS"
                + "(NOMBRE, CANTIDAD, VALOR_UNITARIO) VALUES"
                + "(?,?,?)";
        
        String url = "jdbc:mysql://localhost:3306/dip_m2_act1";
        String username = "root";
        String password = "";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(insertTableSQL);
            
            ps.setString(1, db.getNombre());
            ps.setInt(2, db.getCantid());
            ps.setInt(3, db.getValorU());
            ps.executeUpdate();

            System.out.println("Record is inserted into PRODUCTOS_MySQL table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
        
    }
    
    @Override
    public void getName() {
        
        System.out.println("Motor de base de datos 1 "+insert.getNombre());
    }
}
