package dsp;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Informe o nome de arquivo que compreende a base de dados e o local que o mesmo se encontra:");
        String arq = scan.nextLine();
        Tabela tabFuncionarios = new Tabela(arq);
        boolean arquivoValido = true;
        while(arquivoValido){
            if(tabFuncionarios.inserirPaginas()){
                arquivoValido = false;
                System.out.println("Informe quantas colunas deseja visualizar:");
                int qntColunas = scan.nextInt();
                int colunas[] = new int[qntColunas];
                for(int i=0; i<qntColunas; i++){
                    System.out.println("Informe a "+(i+1)+"º coluna que deseja visualizar:\n 0 - ID Funcionario\n 1 - Nome Funcionario\n 2 - Sobrenome Funcionario\n 3 - Idade Funcionario");
                    colunas[i] = scan.nextInt();
                    boolean colunaDiferente = true;
                    while(colunaDiferente){
                        for(int j=0; j<i; j++){
                            if(colunas[i]==colunas[j]){
                                colunaDiferente = false;
                            }
                        }
                        if(!colunaDiferente){
                            System.out.println("Coluna informada já está informada para visualização");
                            System.out.println("Informe a "+(i+1)+"º coluna que deseja visualizar:\n 0 - ID Funcionario\n 1 - Nome Funcionario\n 2 - Sobrenome Funcionario\n 3 - Idade Funcionario");
                            colunas[i] = scan.nextInt();
                            colunaDiferente = true;
                        }
                        else{
                            colunaDiferente = false;
                        }
                    }
                }
                Tabela tab = tabFuncionarios.ordenacaoExterna();
                tab.distincao(colunas);
            }
            else{
                System.out.println("Informe corretamente o arquivo que compreende a base de dados:");
                arq = scan.nextLine();
                tabFuncionarios.arq = arq;
            }
        }
    }
}