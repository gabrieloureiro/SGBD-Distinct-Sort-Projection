import java.io.IOException;
import java.util.ArrayList;

public class Tabela {

    String table_name;
	String[] cols = new String[5];
    ArrayList<Registro> registros;    

	public Tabela(){}

	public Tabela(String table_name, String[] cols){
        this.table_name = table_name;
        this.registros = new ArrayList<Registro>();
		this.cols = cols;
	}

	public void inserirTupla(Registro registro){

        this.registros.add(registro);

	}

    public String toString(){
        System.out.println(this.table_name);
		for(String c : this.cols) System.out.print(c+"\t\t");
        System.out.println();
        for(Registro r : this.registros) System.out.println(r);
        return "";
    }

}