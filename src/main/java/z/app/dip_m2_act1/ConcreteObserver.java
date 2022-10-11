package z.app.dip_m2_act1;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author M
 */
public class ConcreteObserver implements IObserver{
    
    JComponent component;

    public ConcreteObserver(JComponent c) {
        this.component=c;
    }
    

    @Override
    public void notify_(String msj) {
        if(component instanceof JButton btn){
            btn.setText(msj+" De click aqui");
        }
        if(component instanceof JLabel lbl){
            lbl.setText("Bienvenido "+msj);
        }

        
    }
    
}
