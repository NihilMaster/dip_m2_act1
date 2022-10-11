package z.app.dip_m2_act1;

/**
 *
 * @author M
 */
public interface IObservable {
    
    void notify_All(String msj);
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
    
}
