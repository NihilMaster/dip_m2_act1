package z.app.dip_m2_act1;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        try {
            ConnMySQL connmy = ConnMySQL.getInstance();
            PreparedStatement ps = connmy.getCon().prepareStatement(insertTableSQL);
            
            ps.setString(1, db.getNombre());
            ps.setInt(2, db.getCantid());
            ps.setInt(3, db.getValorU());
            ps.executeUpdate();

            System.out.println("Record is inserted into PRODUCTOS_MySQL table!");
            ps.close();
            // connmy.closeCon();            

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
        
    }
    
    @Override
    public void getName() {
        
        System.out.println("Motor de base de datos 1 "+insert.getNombre());
    }
}
