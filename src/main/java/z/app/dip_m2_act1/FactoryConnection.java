package z.app.dip_m2_act1;

/**
 *
 * @author M
 */
public class FactoryConnection {
    public static IDataBase factoryDataBase(Registro db){
        return db.getValorU()>100000 ? new Mdb1(db) : new Mdb2(db);
    }
}
