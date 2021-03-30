/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancedbrackets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Fábio
 */
public class BalancedBrackets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        //Ler o arquivo
        
        File arquivo = new File("java gera prog.txt");        
        ArrayList<String> inputs = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();

        if (arquivo.exists()) 
        {
            //Salvar dados na lista
            
            try (Scanner scanner = new Scanner(arquivo)) {
                while (scanner.hasNext())
                {
                    inputs.add(scanner.nextLine());
                }
            }
            
            //Analisar as cadeias de caracteres
            
            try{
            for (String s : inputs)
            {
                builder.append(s);
                if (analisaString(s.trim())){                    
                    builder.append(" válido");                    
                }
                else
                {
                    builder.append(" inválido");
                }
                builder.append(System.lineSeparator());
            }
            }
            catch (Exception erro)
            {
                System.out.println(erro);
                System.exit(0);
            }
            
            //Salvar o resultado em arquivo            
            
            try
            {
                arquivo = new File("prog-check.txt");
                arquivo.createNewFile();
                try (FileWriter escritor = new FileWriter("prog-check.txt")) {
                    escritor.write(builder.toString());
                }
                System.out.print(builder.toString());
                System.out.println("\nArquivo de saída criado!");
            }
            catch (IOException erro){
                System.out.println("Ocorreu um erro!");                
            }                           
        } 
        else 
        {
            System.out.println("Arquivo de entrada não encontrado! O arquivo "
                    + "deve ser nomeado \'java gera prog.txt\', deve ser "
                    + "colocado na pasta raiz do projeto e deve incluir as "
                    + "sequencias de caracteres a ser analizada, uma por linha.");

        }
        
        
    }
    
    public static boolean analisaString (String s) throws Exception
    {
        Stack<Character> pilha = new Stack<>();
        
        
        //Pode ser feito melhor
        for(char c : s.toCharArray())
        {
            if (c == '{' || c == '[' || c == '(')
            {
                pilha.add(c);
            }
            else if (c == '}' )
            {
                if (!pilha.isEmpty() && pilha.peek() == '{')
                    pilha.pop();
                else
                    return false;
            }
            else if (!pilha.isEmpty() && c == ']')
            {
                if (pilha.peek() == '[')
                    pilha.pop();
                else
                    return false;
            }
            else if (!pilha.isEmpty() && c == ')')
            {
                if (pilha.peek() == '(')
                    pilha.pop();
                else
                    return false;
            }
            else
            {
                throw new Exception("Há caracteres não aceitos no arquivo. "
                        + "Apenas (, [, {, ), ] e } são aceitos");
            }
        }
        
        if (pilha.empty())
            return true;
        else
            return false;
    }
}
