package z.app.dip_m2_act1;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author M
 */
public class Mdb2 implements IDataBase{
    
    Registro insert;    
    public Mdb2(Registro db) {
        this.insert=db;
        
        String insertTableSQL = "INSERT INTO PUBLIC.PRODUCTOS"
                + "(NOMBRE, CANTIDAD, VALOR_UNITARIO) VALUES"
                + "(?,?,?)";
        try {
            ConnPSQL connps = ConnPSQL.getInstance();
            PreparedStatement ps = connps.getCon().prepareStatement(insertTableSQL);
            
            ps.setString(1, db.getNombre());
            ps.setInt(2, db.getCantid());
            ps.setInt(3, db.getValorU());
            ps.executeUpdate();

            System.out.println("Record is inserted into Public.PRODUCTOS_PostgreSQL table!");
            ps.close();
            // connps.closeCon();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }
    
    @Override
    public void getName() {
        System.out.println("Motor de base de datos 2 "+insert.getNombre());
    }
}
