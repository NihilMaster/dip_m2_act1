package z.app.dip_m2_act1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author M
 */
public class Dip_m2_act1 extends JFrame{
    
    private static Dip_m2_act1 w;
    private JTextField no_tf,ca_tf,vu_tf;
    private final JButton a_b, d_b, l_b, i_b;
    
    private String nom;
    private Integer val, can;

    public static void main(String[] args) {
        w=new Dip_m2_act1();
        w.setBounds(0,0,300,200);
        w.setResizable(false);
        w.setVisible(true);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void back(){
        Registro m = new Registro(nom,can,val);
        IDataBase db=FactoryConnection.factoryDataBase(m);
        JOptionPane.showMessageDialog(w, "Éxito!");
        no_tf.setText(""); ca_tf.setText(""); vu_tf.setText("");
        db.getName();
    }
    
    public Dip_m2_act1(){
        GridLayout grilla = new GridLayout(5,2);
        setLayout(grilla);
        add(new JLabel("Nombre: "));
        this.no_tf=new JTextField();
        add(no_tf);
        add(new JLabel("Cantidad: "));
        this.ca_tf=new JTextField();
        add(ca_tf);
        add(new JLabel("ValorU: "));
        this.vu_tf=new JTextField();
        add(vu_tf);
        this.a_b=new JButton("Aceptar");
        add(a_b);
        this.d_b=new JButton("Limpiar");
        add(d_b);
        this.l_b=new JButton("Listar");
        add(l_b);
        this.i_b=new JButton("Informe");
        add(i_b);
        
        a_b.addActionListener((ActionEvent e) -> {
            nom = (!no_tf.getText().isEmpty()) ? no_tf.getText() : null;
            can = (!ca_tf.getText().isEmpty()) ? Integer.parseInt(ca_tf.getText()) : null;
            val = (!vu_tf.getText().isEmpty()) ? Integer.parseInt(vu_tf.getText()) : null;
            if(!no_tf.getText().isEmpty() && can!=null && val!=null){back();}
            else{JOptionPane.showMessageDialog(w, "Hay campos vacíos", "Inane warning",JOptionPane.WARNING_MESSAGE);}
        });
        
        d_b.addActionListener((ActionEvent e) -> {
            no_tf.setText(""); ca_tf.setText(""); vu_tf.setText("");
        });
        
        l_b.addActionListener((ActionEvent e) -> {
            ListarProductos l = new ListarProductos();
            System.out.println("Imprimir valores");
        });
        
        i_b.addActionListener((ActionEvent e) -> {
            System.out.println("Inventario");
        });
    }
}


/*
        Scanner sc=new Scanner(System.in);
        System.out.println("Registro de productos");
        System.out.println("Ingrese el nombre");
        String nom=sc.nextLine();
        System.out.println("Ingrese el número");
        String num=sc.nextLine();
        System.out.println("Ingrese el valor unitario");
        Integer val=Integer.parseInt(sc.nextLine());
        Registro m = new Registro(nom,num,val);
        IDataBase db=FactoryConnection.factoryDataBase(m);
        db.getName();


*/