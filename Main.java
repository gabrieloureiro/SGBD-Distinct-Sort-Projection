import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void intercala(String[] v, String[] w, int ini, int meio, int fim){
		for (int k = ini; k <= fim; k++){
			w[k] = v[k];
		}

		int i = ini;
		int j = meio + 1;

		for (int k = ini; k <= fim; k++){
			if (i > meio){
				v[k] = w[j++];
			}
			else if (j > fim) {
				v[k] = w[i++];
			}
			else if (w[i].compareTo(w[j]) < 0){
				v[k] = w[i++];
			}
			else {
				v[k] = w[j++];
			}
		}
	}

	public static void main(String[] args) {

		String[] vinho_cols = {"vinho_id", "rotulo", "ano_colheita", "pais_producao_id", "uva_id"};
		String[] uva_cols   = {"uva_id", "nome", "tipo", "pais_origem_id"};
		String[] pais_cols  = {"pais_id", "nome"};

	    Tabela tab_vinho = new Tabela("tab_vinho", vinho_cols);
	    Tabela tab_uva   = new Tabela("tab_uva", uva_cols);
	    Tabela tab_pais  = new Tabela("tab_pais", pais_cols);

		//paises
		ArrayList<String> pais_1_valores = new ArrayList<String>();
		pais_1_valores.add("2");
		pais_1_valores.add("Franca");
		Registro pais_1 = new Registro(pais_1_valores);
	    tab_pais.inserirTupla(pais_1);

		ArrayList<String> pais_2_valores = new ArrayList<String>();
		pais_2_valores.add("1");
		pais_2_valores.add("Italia");
		Registro pais_2 = new Registro(pais_2_valores);
	    tab_pais.inserirTupla(pais_2);

		ArrayList<String> pais_3_valores = new ArrayList<String>();
		pais_3_valores.add("0");
		pais_3_valores.add("Brasil");
		Registro pais_3 = new Registro(pais_3_valores);
	    tab_pais.inserirTupla(pais_3);

		//uvas
		ArrayList<String> uva_1_valores = new ArrayList<String>();
		uva_1_valores.add("2");
		uva_1_valores.add("uva3"); 
		uva_1_valores.add("tinto"); 
		uva_1_valores.add("2");
		Registro uva_1 = new Registro(uva_1_valores);
	    tab_uva.inserirTupla(uva_1);

		ArrayList<String> uva_2_valores = new ArrayList<String>();
		uva_2_valores.add("0");
		uva_2_valores.add("uva1");
		uva_2_valores.add("branco");
		uva_2_valores.add("0");
		Registro uva_2 = new Registro(uva_2_valores);
	    tab_uva.inserirTupla(uva_2);

		ArrayList<String> uva_3_valores = new ArrayList<String>();
		uva_3_valores.add("1" );
		uva_3_valores.add("uva2"); 
		uva_3_valores.add("tinto" ); 
		uva_3_valores.add("1");
		Registro uva_3 = new Registro(uva_3_valores);
	    tab_uva.inserirTupla(uva_3);

		//vinhos
		ArrayList<String> vinho_1_valores = new ArrayList<String>();
		vinho_1_valores.add("1"); 
		vinho_1_valores.add("vinho2"); 
		vinho_1_valores.add("1993");
		vinho_1_valores.add("2");
		vinho_1_valores.add("2");
		Registro vinho_1 = new Registro(vinho_1_valores);
	    tab_vinho.inserirTupla(vinho_1);

		ArrayList<String> vinho_2_valores = new ArrayList<String>();
		vinho_2_valores.add("0" );
		vinho_2_valores.add("vinho1"); 
		vinho_2_valores.add("1991");
		vinho_2_valores.add("0");
		vinho_2_valores.add("0");
		Registro vinho_2 = new Registro(vinho_2_valores);
	    tab_vinho.inserirTupla(vinho_2);

		ArrayList<String> vinho_3_valores = new ArrayList<String>();
		vinho_3_valores.add("2");
		vinho_3_valores.add("vinho3"); 
		vinho_3_valores.add("1993");
		vinho_3_valores.add("2");
		vinho_3_valores.add("2");
		Registro vinho_3 = new Registro(vinho_3_valores);
	    tab_vinho.inserirTupla(vinho_3);

		ArrayList<String> vinho_4_valores = new ArrayList<String>();
		vinho_4_valores.add("0");
		vinho_4_valores.add("vinho1"); 
		vinho_4_valores.add("1991");
		vinho_4_valores.add("0");
		vinho_4_valores.add("0");
		Registro vinho_4 = new Registro(vinho_4_valores);
	    tab_vinho.inserirTupla(vinho_4);

		ArrayList<String> vinho_5_valores = new ArrayList<String>();
		vinho_5_valores.add("0");
		vinho_5_valores.add("vinho1"); 
		vinho_5_valores.add("1992");
		vinho_5_valores.add("1");
		vinho_5_valores.add("1");
		Registro vinho_5 = new Registro(vinho_5_valores);
	    tab_vinho.inserirTupla(vinho_5);

		//System.out.println(tab_pais);
		//System.out.println(tab_uva);
		//System.out.println(tab_vinho);

	    //PROJECAO:

	    Operador op = new Operador();

	    /* >>> DESCOMENTE ABAIXO */ //PROJETAR UVA
		//String[] projecao_cols = {"uva_id", "nome", "tipo", "pais_origem_id"};
	    //Tabela tab = op.tuplasGeradas(tab_uva, projecao_cols);
	    //int num_tuplas  = op.numTuplasGeradas(tab_uva); // Retorna a quantidade de tuplas geradas pela operacao.
	  	//int num_pags    = op.numPagsGeradas(tab_uva);  // Retorna a quantidade de paginas geradas pela operacao.

		/* >>> DESCOMENTE ABAIXO */ //PROJETAR VINHO
		String[] projecao_cols = {"vinho_id", "rotulo", "ano_colheita", "pais_producao_id", "uva_id"};
		Tabela tab = op.tuplasGeradas(tab_vinho, projecao_cols);
		int num_tuplas  = op.numTuplasGeradas(tab_vinho); // Retorna a quantidade de tuplas geradas pela operacao.
	  	int num_pags    = op.numPagsGeradas(tab_vinho);  // Retorna a quantidade de paginas geradas pela operacao.

		/* >>> DESCOMENTE ABAIXO */ //PROJETAR PAÍS
		//String[] projecao_cols = {"pais_id", "nome"};
		//Tabela tab = op.tuplasGeradas(tab_pais, projecao_cols);
		//int num_tuplas  = op.numTuplasGeradas(tab_pais); // Retorna a quantidade de tuplas geradas pela operacao.
	  	//int num_pags    = op.numPagsGeradas(tab_pais);  // Retorna a quantidade de paginas geradas pela operacao.



	}

}