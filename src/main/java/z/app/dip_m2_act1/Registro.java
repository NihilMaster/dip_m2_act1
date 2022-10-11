package z.app.dip_m2_act1;

/**
 *
 * @author M
 */
public class Registro { //Prototype
    private String nombre;
    private Integer cantid;
    private Integer valoru;
    
    public Registro(String no, Integer ca, Integer vu) {
        this.nombre=no;
        this.cantid=ca;
        this.valoru=vu;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCantid() {
        return cantid;
    }

    public Integer getValorU() {
        return valoru;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantid(Integer cantid) {
        this.cantid = cantid;
    }

    public void setValoru(Integer valoru) {
        this.valoru = valoru;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
