/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minijavacompiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.StringCharacterIterator;

/**
 * @author bianca
 */
public class Scanner 
{
    private static String input;
    private StringCharacterIterator inputIt;
    
    public Scanner(String inputFileName)
    {
        File inputFile = new File(inputFileName);       
        
        try
        {
            FileReader fr = new FileReader(inputFile);
            
            int size = (int)inputFile.length();            
            char[] buffer = new char[size];
        
            fr.read(buffer, 0, size);
            
            input = new String(buffer);
            
            inputIt = new StringCharacterIterator(input);
            
            //System.out.println(input);
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Arquivo não encontrado");
        }
        catch(IOException e)
        {
            System.err.println("Erro na leitura do arquivo");
        }
    }
    
    public Token nextToken()
    {
        Token tok = new Token(EnumToken.UNDEF);               
     
        //TODO
        
        return tok;
    }
}