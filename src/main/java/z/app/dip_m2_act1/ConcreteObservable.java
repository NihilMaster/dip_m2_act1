package z.app.dip_m2_act1;

import java.util.LinkedList;

/**
 *
 * @author M
 */
public class ConcreteObservable implements IObservable{

    IObserver observer;
    LinkedList<IObserver> subscriptors;

    public ConcreteObservable(IObserver o) {
        observer=o;
        subscriptors=new LinkedList<>();
    }   
    
    
    
    @Override
    public void notify_All(String msj) {
        for (IObserver subscriptor : subscriptors) {
            subscriptor.notify_(msj);
        }
    }

    @Override
    public void addObserver(IObserver o) {
        subscriptors.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        subscriptors.remove(o);
    }

   
    
}
