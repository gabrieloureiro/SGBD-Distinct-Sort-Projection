package dsp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Tabela {
    ArrayList<Pagina> paginas = new ArrayList<Pagina>(); 
    int numeroPaginas = 0;
    String arq;
    Tabela(String arq){
        this.arq = arq;
    }
    
    boolean inserirPaginas(){
        try{
            FileReader file = new FileReader(arq);
            BufferedReader lerArq = new BufferedReader(file);
            String linha = "";
            while(linha != null){
                ArrayList<String> valoresPagina = new ArrayList<String>();
                Pagina pag = new Pagina();
                for(int i=0; i<16; i++){                
                    linha = lerArq.readLine();
                    if(linha!=null){
                        valoresPagina.add(linha);
                    }
                    else{
                        i = 16;
                    }
                }
                pag.inserirRegistros(valoresPagina);
                paginas.add(pag);
                if(valoresPagina.size()==16){
                    valoresPagina = null;
                }
            }
            numeroPaginas = paginas.size();
            return true;
        }
        catch(IOException e){
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            return false;
        }
    }
    
    Registro encontrarRegistro(int id){
        for(int i=0; i<numeroPaginas; i++){
            for(int j=0; j<paginas.get(i).registros.size();j++){
                if(id==Integer.parseInt(paginas.get(i).registros.get(j).dados[0])){
                    return paginas.get(i).registros.get(j);
                }
            }
        }
        return null;
    }
    
    Tabela ordenacaoExterna(){
        Tabela tab = new Tabela(arq);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(int i=0; i<numeroPaginas; i++){
            for(int j=0; j<paginas.get(i).registros.size();j++){
                ids.add(Integer.parseInt(paginas.get(i).registros.get(j).dados[0]));
            }
        }
        Collections.sort(ids);
        int cont = 0;
        for(int i=0; i<numeroPaginas; i++){
            Pagina pag = new Pagina();
            for(int j=0; j<paginas.get(i).registros.size(); j++){
                Registro reg = encontrarRegistro(ids.get(cont));
                pag.registros.add(reg);
                cont++;
            }
            tab.paginas.add(pag);
        }
        tab.numeroPaginas = tab.paginas.size();
        return tab;
    }
    
    void distincao(int colunas[]) throws IOException{
        Scanner scan = new Scanner(System.in);
        ArrayList<Registro> registrosDistintos = new ArrayList<Registro>();
        for(int i=0; i<numeroPaginas; i++){
            for(int j=0; j<paginas.get(i).registros.size();j++){
                registrosDistintos.add(paginas.get(i).registros.get(j));
            }
        }
        for(int i=0; i<registrosDistintos.size(); i++){
            for(int j=0; j<registrosDistintos.size(); j++){
                String t1 = new String();
                String t2 = new String();
                if(i!=j){
                    for(int k=0; k<colunas.length; k++){
                        t1+=registrosDistintos.get(i).dados[colunas[k]];
                        t2+=registrosDistintos.get(j).dados[colunas[k]];
                    }
                    if(t1.equals(t2)){
                        registrosDistintos.remove(j);
                        j=0;
                    }
                }
            }
        }
        System.out.println("Informe o diretório que você deseja salvar o arquivo com os dados de retorno:");
        String diretorio = scan.nextLine();
        FileWriter arquivo = new FileWriter(diretorio.concat("\\data_DSP.txt"));
        BufferedWriter gravarArq = new BufferedWriter(arquivo);
        for(int i=0; i<registrosDistintos.size(); i++){
            for(int j=0; j<colunas.length; j++){
                gravarArq.write(registrosDistintos.get(i).dados[colunas[j]]);
                if(j!=colunas.length-1){
                    gravarArq.write(",");
                }
            }
            gravarArq.write("\n");
        }
        gravarArq.close();
    }
}
