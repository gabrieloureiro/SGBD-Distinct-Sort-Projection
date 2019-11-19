package dsp;
import java.util.ArrayList;
public class Pagina {
    ArrayList<Registro> registros = new ArrayList<Registro>();
    void inserirRegistros(ArrayList<String> dados){
        for(int i=0; i<dados.size(); i++){
            Registro reg = new Registro(dados.get(i));
            if(dados!=null){
                registros.add(reg);
            }
        }
    }
}
