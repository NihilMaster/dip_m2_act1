package z.app.dip_m2_act1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author M
 */
public class Dip_m2_act1 extends JFrame{
    
    private static Dip_m2_act1 w;
    private JTextField no_tf,ca_tf,vu_tf;
    private final JButton a_b, d_b, l_b, i_b;
    private JTable table_sum = new JTable();
    private final JTextField txt = new JTextField();
    private final JPanel p1;
    private JPanel p2;
    
    ConcreteObserver otxt;
    ConcreteObservable observable;
    
    private String nom;
    private Integer val, can;

    public static void main(String[] args) {
        w=new Dip_m2_act1();
        w.setBounds(0,0,500,200);
        w.setResizable(false);
        w.setVisible(true);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void back(){ //Factory Method
        Registro m = new Registro(nom,can,val);
        IDataBase db=FactoryConnection.factoryDataBase(m);
        JOptionPane.showMessageDialog(w, "Éxito!");
        no_tf.setText(""); ca_tf.setText(""); vu_tf.setText("");
        db.getName();
    }
    
    public Dip_m2_act1(){
        otxt=new ConcreteObserver(txt);
        observable=new ConcreteObservable(otxt);
        
        this.p1 = new JPanel();
        p1.setLayout(new GridLayout(7,2,10,10));
        p1.add(new JLabel("Nombre: "));
        this.no_tf=new JTextField();
        p1.add(no_tf);
        p1.add(new JLabel("Cantidad: "));
        this.ca_tf=new JTextField();
        p1.add(ca_tf);
        p1.add(new JLabel("ValorU: "));
        this.vu_tf=new JTextField();
        p1.add(vu_tf);
        this.a_b=new JButton("Aceptar");
        p1.add(a_b);
        this.d_b=new JButton("Limpiar");
        p1.add(d_b);
        this.l_b=new JButton("Listar");
        p1.add(l_b);
        this.i_b=new JButton("Informe");
        p1.add(i_b);
        
        this.p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.setVisible(false);
        
        GridLayout grilla = new GridLayout(1,2,2,2);
        setLayout(grilla);

        add(p1);
        add(p2);
        
        a_b.addActionListener((ActionEvent e) -> {
            nom = (!no_tf.getText().isEmpty()) ? no_tf.getText() : null;
            can = (!ca_tf.getText().isEmpty()) ? Integer.parseInt(ca_tf.getText()) : null;
            val = (!vu_tf.getText().isEmpty()) ? Integer.parseInt(vu_tf.getText()) : null;
            if(!no_tf.getText().isEmpty() && can!=null && val!=null){back();}
            else{JOptionPane.showMessageDialog(w, "Hay campos vacíos", "Inane warning",JOptionPane.WARNING_MESSAGE);}
            p2.removeAll();
            Inventario inv = new Inventario();
            p2.setVisible(false);
            obeserver(inv);
            //observable.notify_All(txt.getText());
        });
        
        d_b.addActionListener((ActionEvent e) -> {
            no_tf.setText(""); ca_tf.setText(""); vu_tf.setText("");
        });
        
        l_b.addActionListener((ActionEvent e) -> {
            ListarProductos l = new ListarProductos();
            System.out.println("Imprimir valores");
        });
        
        i_b.addActionListener((ActionEvent e) -> {
            Inventario inv = new Inventario();
            obeserver(inv);
            System.out.println("Inventario");
        });
    }
    
    private void obeserver(Inventario inv){
        JPanel t = new JPanel();
        t.setLayout(new FlowLayout());
        t.add(new JLabel("Total"));
        t.add(txt, BorderLayout.CENTER);
        p2.add(t);
        txt.setText(inv.getTotal().toString());
        table_sum = new JTable(toTableModel(inv.getM()));
        JScrollPane scrollPane = new JScrollPane(table_sum);
        p2.add(scrollPane, BorderLayout.CENTER);
        p2.setVisible(true);
    }
    
    private static TableModel toTableModel(Map<?,?> map) {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] { "Producto", "Cantidad" }, 0
        );
        for (Map.Entry<?,?> entry : map.entrySet()) {
            model.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
        return model;
    }
}