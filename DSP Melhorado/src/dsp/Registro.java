package dsp;
public class Registro {
    String dados[] = new String[4];
    Registro(String dados){
        this.dados = dados.split(",");
    }
}
