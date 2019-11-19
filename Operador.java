import java.util.ArrayList;
import java.util.Arrays;

public class Operador {

	public Operador(){}


	  	//int num_pags    = op.numPagsGeradas();  // Retorna a quantidade de paginas geradas pela operacao.

	private void mergeSort(ArrayList<Registro> v, Registro[] w, String[] proj_cols, String atributo, int ini, int fim){
		if (ini < fim){
			int meio = (ini + fim) / 2;
			mergeSort(v, w, proj_cols, atributo, ini, meio);
			mergeSort(v, w, proj_cols, atributo, meio+1, fim);
			merge(v, w, proj_cols, atributo, ini, meio, fim);
		}
	}

	private void merge(ArrayList<Registro> v, Registro[] w, String[] proj_cols, String atributo, int ini, int meio, int fim){
		
		for (int k = ini; k <= fim; k++){
			w[k] = v.get(k);
		}

		int i = ini;
		int j = meio + 1;

		int count = -1;
		String wi = "";
		String wj = "";

		for (int k = ini; k <= fim; k++){
			if (i > meio){
				v.set(k, w[j++]);
			}
			else if (j > fim){
				v.set(k, w[i++]);
			}
			else{
				count = 0;
				for (String col : proj_cols){
					if(col == atributo){
						wi = w[i].cols.get(count);
						wj = w[j].cols.get(count);
						break;
					}
					count++;
				}
				if (wi.compareTo(wj) < 0){
					v.set(k, w[i++]);
				}
				else {
					v.set(k, w[j++]);
				}
			}
		}
	}

	private Tabela distinct(Tabela tab){
		for (int i=0; i<tab.registros.size()-1; i++){
			String reg1 = "";
			String reg2 = "";
			for (int j=0; j<i; j++){
				reg1 += tab.registros.get(i).cols.get(j);
				reg2 += tab.registros.get(i+1).cols.get(j);
			
				if (reg1.equals(reg2)){
					tab.registros.remove(i);
					//tab.registros.remove(j);
				}
			}
		}
		return tab;
	}

	public Tabela tuplasGeradas(Tabela tab, String[] proj_cols){

		System.out.print("\n>>> Tabela que irá ser projetada: "); System.out.println(tab);
		System.out.print("\n>>> Colunas que irão ser projetadas: "); for(String p : proj_cols) System.out.print(p+" -- "); System.out.println("\n");

		Tabela temp_tab = new Tabela("temp_"+tab.table_name, proj_cols);

		for (Registro r : tab.registros){
			
			ArrayList<String> temp_valores = new ArrayList<String>();
			
			int count;
			for (String col : proj_cols){
				count = 0;
				for (String c : tab.cols){
					if(col.equals(c)){
						temp_valores.add(r.cols.get(count));
					}
					count++;
				}
			}

			Registro temp_registro = new Registro(temp_valores);
			temp_tab.inserirTupla(temp_registro);
		
		}

		System.out.println("\n>>> Após aplicar Ordenação Externa: \n"+temp_tab);
		this.mergeSort(temp_tab.registros, new Registro[5], proj_cols, proj_cols[0], 0, temp_tab.registros.size()-1);

		int aux;
		int k;
		for (int i=1; i<proj_cols.length; i++){
			k = 0;
			while (k < temp_tab.registros.size()-1){
				String reg1 = "";
				String reg2 = "";
				aux = k;
				while(reg1.equals(reg2) && aux < temp_tab.registros.size()-1){
					reg1 = "";
					reg2 = "";
					for (int j=0; j<i; j++){
						reg1 += temp_tab.registros.get(aux).cols.get(j);
						reg2 += temp_tab.registros.get(aux+1).cols.get(j);
					}
					if (reg1.equals(reg2)){
						aux++;
					}
				}
				this.mergeSort(temp_tab.registros, new Registro[5], proj_cols, proj_cols[i], k, aux);
				k = aux+1;
			}
		}

		System.out.println(temp_tab);
		temp_tab = distinct(temp_tab);
		System.out.println("\n>>> Após aplicar Distinção: \n");
		System.out.println(temp_tab);

		return temp_tab;
	}


	public int numTuplasGeradas(Tabela t){
		
		int count=0;
		if (t.table_name.equals("tab_vinho")){

			for(int i = 0; i<t.registros.size()-1; i++){ count++; }

			System.out.println("Número de tuplas geradas: "+count);
		}

		else {

			for(int i = 0; i<t.registros.size(); i++){ count++; }

			System.out.println("Número de tuplas geradas: "+count);
		}

		return count;
	} 

	public int numPagsGeradas(Tabela t){
	
		int count=0;
		
		if (t.table_name.equals("tab_vinho") && count<=12){

			for(int i = 0; i<t.registros.size()-1; i++){ count++; }
	
			System.out.println("Gerou 1 página");

		} else {

			for(int i = 0; i<t.registros.size(); i++){ count++; }

			System.out.println("Gerou 1 página");
		}

		if(count>12 && count<=24) {
			System.out.println("Gerou 2 páginas");
			count = count + 12;
		}

		if(count>24 && count<=36) {
			System.out.println("Gerou 3 páginas");
			count = count + 12;
		}

	return count;

	}
}