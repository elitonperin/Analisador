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
    
    public Scanner(SymbolTable<STEntry> st, String inputFileName)
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
    
    public Scanner( String inputFileName)
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
        int state = 0;
        int begin = 0, end = 0;
        String lexema;        

        if (inputIt.getIndex() >= inputIt.getEndIndex())            
            tok.name = EnumToken.EOF;
        else{

            while(state != -1){
                switch(state){
                    case 0:
                        if (inputIt.current() == '<')
                            state = 4;
                        else if (inputIt.current() == '=')
                            state = 7;
                        else if (inputIt.current() == '>')
                            state = 10;
                        else if (inputIt.current() == '[')
                            state = 0;
                        else if (inputIt.current() == ']')
                            state = 0;
                        else if (inputIt.current() == '}')
                            state = 0;
                        else if (inputIt.current() == '{')
                            state = 0;
                        else if (inputIt.current() == '(')
                            state = 0;
                        else if (inputIt.current() == ')')
                            state = 0;
                        else if (inputIt.current() == '/')
                            state = 0;
                        else if (inputIt.current() == '%')
                            state = 0;
                        else if (inputIt.current() == '-')
                            state = 0;
                        else if (inputIt.current() == '+')
                            state = 0;
                        else if (inputIt.current() == '*')
                            state = 0;
                        else if (inputIt.current() == ',')
                            state = 0;
                        else if (inputIt.current() == ';')
                            state = 0;
                        else if (inputIt.current() == '.')
                            state = 0;
                        else if (Character.isLetter(inputIt.current()))
                            state = 1;
                        else if (inputIt.current() == '_')
                            state = 1;
                        else if (Character.isDigit(inputIt.current()))
                            state = 13;  
                        else if (Character.isWhitespace(inputIt.current()))
                            state = 3;
                            
                        begin = inputIt.getIndex();
                        inputIt.next();
                        
                        break;
                    case 1:
                        while (Character.isLetterOrDigit(inputIt.current()))
                              inputIt.next();            
                          state = 2;
                          inputIt.next();
                        break;
                    case 2:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                                inputIt.previous();
                            end = inputIt.getIndex();
                            lexema = new String(input.substring(begin, end));

                            if (lexema.equals("if"))                    
                                tok.name = EnumToken.IF;
                            else if (lexema.equals("else"))
                                tok.name = EnumToken.ELSE;
                            else if (lexema.equals("class"))
                                tok.name = EnumToken.CLASS;
                            else if (lexema.equals("extends"))
                                tok.name = EnumToken.EXTENDS ;
                            else if (lexema.equals("int"))
                                tok.name = EnumToken.INT ;
                            else if (lexema.equals("double"))
                                tok.name = EnumToken.DOUBLE ;
                            else if (lexema.equals("string"))
                                tok.name = EnumToken.STRING ;
                            else if (lexema.equals("constructor"))
                                tok.name = EnumToken.CONSTRUCTOR;
                            else if (lexema.equals("break"))
                                tok.name = EnumToken.BREAK ;
                            else if (lexema.equals("print"))
                                tok.name = EnumToken.PRINT ;
                            else if (lexema.equals("read"))
                                tok.name = EnumToken.READ ;
                            else if (lexema.equals("for"))
                                tok.name = EnumToken.FOR;
                            else if (lexema.equals("super"))
                                tok.name = EnumToken.SUPER ;
                            else if (lexema.equals("return"))
                                tok.name = EnumToken.RETURN;
                            else if (lexema.equals("new"))
                                tok.name = EnumToken.NEW ;
                            else
                                tok.name  = EnumToken.ID;

                            state = -1;
                        break;
                    case 3:
                        while(Character.isWhitespace(inputIt.current()))
                            inputIt.next();
                        
                        state = 0;
                        break;
                    case 4:
                        if (inputIt.current() == '=')
                            state = 5;
                        else
                            state = 6;
                        inputIt.next(); 
                        break;
                    case 5:
                        tok.name = EnumToken.LE;
                        tok.attribute = EnumToken.LE;
                        state = -1;
                        break;
                    case 6:
                        tok.name = EnumToken.LT;
                        tok.attribute = EnumToken.LT;
                        state = -1;
                        break;
                    case 7:
                        if(inputIt.current() == '=')
                            state = 8;
                        else
                            state = 9;
                        
                        inputIt.next();
                        break;
                    case 8:
                        tok.name = EnumToken.EQ;
                        tok.attribute = EnumToken.EQ;
                        state = -1;
                        break;
                    case 9:
                        tok.name = EnumToken.IGUAL;
                        tok.attribute = EnumToken.IGUAL;
                        state = -1;
                        break;
                    case 10:
                        if (inputIt.current() == '=')
                            state = 11;
                        else
                            state = 12;
                        inputIt.next();  
                        break;
                    case 11:
                        tok.name = EnumToken.GE;
                        tok.attribute = EnumToken.GE;
                        state = -1;                    
                        break;
                    case 12:
                        tok.name = EnumToken.GT;
                        tok.attribute = EnumToken.GT;
                        state = -1;                    
                        break;
                    case 13:
                        while (Character.isDigit(inputIt.current()))
                            inputIt.next();                    

                        if (inputIt.current() == '.')
                            state = 14; 
                        else if (inputIt.current() == 'E')
                            state = 16; 
                        else
                            state = 20; 

                        inputIt.next();
                        
                        break;
                    case 14:
                        if (Character.isDigit(inputIt.current()))
                        {
                            state = 15; 
                            inputIt.next();
                        }
                        else
                        {
                            System.out.println("Erro no reconhecimento de float");
                            state = -1;
                        }   
                        break;
                    case 15:
                        while (Character.isDigit(inputIt.current()))
                            inputIt.next();                    

                        if (inputIt.current() == 'E')
                            state = 16; 
                        else
                            state = 21; 

                        inputIt.next();
                        break;
                    case 16:
                        if (inputIt.current() == '+' || inputIt.current() == '-')
                        state = 17; 
                        else if (Character.isDigit(inputIt.current()))
                            state = 18; 
                        else
                        {
                            System.out.println("Erro no reconhecimento de float");
                            state = -1;
                        }  
                        break;
                    case 17:
                        if (Character.isDigit(inputIt.current()))
                            state = 18;
                        else
                        {
                            System.out.println("Erro no reconhecimento de float");
                            state = -1;
                        }

                        inputIt.next();
                        break;
                    case 18:
                        while (Character.isDigit(inputIt.current()))
                            inputIt.next();                    

                        state = 19;
                        inputIt.next();

                        break;
                    case 19:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();

                        end = inputIt.getIndex();                    
                        lexema = new String(input.substring(begin, end));

                        tok.name = EnumToken.DOUBLE_LITERAL;
                        tok.attribute = EnumToken.DOUBLE_LITERAL;

                        state = -1;
                        break;
                    case 20:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();

                        end = inputIt.getIndex();                    
                        lexema = new String(input.substring(begin, end));

                        tok.name = EnumToken.INTEGER_LITERAL;
                        tok.attribute = EnumToken.INTEGER_LITERAL;                        

                        state = -1;

                        break;
                    case 21:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();

                        end = inputIt.getIndex();                    
                        lexema = new String(input.substring(begin, end));

                        tok.name = EnumToken.DOUBLE_LITERAL;
                        tok.attribute = EnumToken.DOUBLE_LITERAL;

                        state = -1;
                        break;
                    case 22:
                        break;
                    case 23:
                        break;
                    case 24:
                        break;
                    case 25:
                        break;
                    case 26:
                        break;
                    case 27:
                        break;
                    case 28:
                        break;
                    case 29:
                        break;
                }
            }
        }
        
        return tok;
    }
}
/**
 * MAIS,
    MENOS,
    VEZES,
    DIV,
    REAL,
    ID,
    APAREN,
    FPAREN,
 */

/**public Token nextToken()
    {
        Token tok = new Token(EnumToken.UNDEF);        
        
        int state = 0;
        int begin = 0, end = 0;
        String lexema;        

        if (inputIt.getIndex() >= inputIt.getEndIndex())            
            tok.name = EnumToken.EOF;
        else
        {
            while (state != -1)
            {                            
                switch (state)
                {
                    case 0:
                        if (inputIt.current() == '<')
                            state = 1;
                        else if (inputIt.current() == '=')
                            state = 5;
                        else if (inputIt.current() == '>')
                            state = 6;
                        else if (Character.isLetter(inputIt.current()))
                            state = 9;//10
                        else if (Character.isDigit(inputIt.current()))
                            state = 11; //13       
                        else if (Character.isWhitespace(inputIt.current()))
                            state = 20;
                        else
                        {
                            System.err.println("Símbolo desconhecido.");
                            state = -1;
                        }

                        begin = inputIt.getIndex();
                        inputIt.next();

                        break;
                    case 1:
                        if (inputIt.current() == '=')
                            state = 2;
                        else if (inputIt.current() == '>')
                            state = 3;
                        else
                            state = 4;

                        inputIt.next();                           

                        break;
                    case 2:
                        tok.name = EnumToken.RELOP;
                        tok.attribute = EnumToken.LE;
                        state = -1;

                        break;
                    case 3:
                        tok.name = EnumToken.RELOP;
                        tok.attribute = EnumToken.NE;
                        state = -1;

                        break;
                    case 4:
                        tok.name = EnumToken.RELOP;
                        tok.attribute = EnumToken.LT;
                        state = -1;

                        break;
                    case 5:
                        tok.name = EnumToken.RELOP;
                        tok.attribute = EnumToken.EQ;
                        state = -1;

                        break;
                    case 6:
                        if (inputIt.current() == '=')                        
                            state = 7;                            
                        else
                            state = 8;

                        inputIt.next();                           

                        break;
                    case 7:
                        tok.name = EnumToken.RELOP;
                        tok.attribute = EnumToken.GE;
                        state = -1;

                        break;
                    case 8:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();

                        tok.name = EnumToken.RELOP;
                        tok.attribute = EnumToken.GT;
                        state = -1;

                        break;

                    case 9:
                        while (Character.isLetterOrDigit(inputIt.current()))
                            inputIt.next();                   

                        state = 10;
                        inputIt.next();

                        break;

                    case 10:
                        //Verificar se é identificador ou palavra reservada
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();
                        end = inputIt.getIndex();
                        lexema = new String(input.substring(begin, end));

                        if (lexema.equals("if"))                    
                            tok.name = EnumToken.IF;
                        else if (lexema.equals("else"))
                            tok.name = EnumToken.ELSE;
                        else if (lexema.equals("then"))
                            tok.name = EnumToken.THEN;
                        else
                            tok.name = EnumToken.ID;
                        
                        state = -1;

                        break;
                    case 11:
                        while (Character.isDigit(inputIt.current()))
                            inputIt.next();                    

                        if (inputIt.current() == '.')
                            state = 12; //14
                        else if (inputIt.current() == 'E')
                            state = 14; //16
                        else
                            state = 18; //20

                        inputIt.next();

                        break;
                    case 12:
                        //inputIt.next();
                        if (Character.isDigit(inputIt.current()))
                        {
                            state = 13; //15
                            inputIt.next();
                        }
                        else
                        {
                            System.out.println("Erro no reconhecimento de float");
                            state = -1;
                        }                
                        

                        break;
                    case 13:
                        while (Character.isDigit(inputIt.current()))
                            inputIt.next();                    

                        if (inputIt.current() == 'E')
                            state = 14; //16
                        else
                            state = 19; //21
                        
                        inputIt.next();

                        break;
                    case 14:
                        if (inputIt.current() == '+' || inputIt.current() == '-')
                            state = 15; //17
                        else if (Character.isDigit(inputIt.current()))
                            state = 16; //18
                        else
                        {
                            System.out.println("Erro no reconhecimento de float");
                            state = -1;
                        }                        

                        inputIt.next();

                        break;
                    case 15:
                        if (Character.isDigit(inputIt.current()))
                            state = 16;//18
                        else
                        {
                            System.out.println("Erro no reconhecimento de float");
                            state = -1;
                        }

                        inputIt.next();

                        break;
                    case 16:
                        while (Character.isDigit(inputIt.current()))
                            inputIt.next();                    

                        state = 17;
                        inputIt.next();

                        break;
                    case 17:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();
                        
                        end = inputIt.getIndex();                    
                        lexema = new String(input.substring(begin, end));

                        tok.name = EnumToken.NUMBER;
                        tok.attribute = EnumToken.FLOAT;
                        
                        state = -1;

                        break;
                    case 18:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();
                        
                        end = inputIt.getIndex();                    
                        lexema = new String(input.substring(begin, end));

                        tok.name = EnumToken.NUMBER;
                        tok.attribute = EnumToken.INTEGER;                        
                        
                        state = -1;

                        break;
                    case 19:
                        if (inputIt.current() != StringCharacterIterator.DONE)
                            inputIt.previous();
                        
                        end = inputIt.getIndex();                    
                        lexema = new String(input.substring(begin, end));

                        tok.name = EnumToken.NUMBER;
                        tok.attribute = EnumToken.FLOAT;
                        
                        state = -1;

                        break;
                    case 20:
                        //Consome espaços em branco e volta para o estado inicial
                        while (Character.isWhitespace(inputIt.current()))
                            inputIt.next();

                        state = 0;

                    //default:
                      //  System.err.println("Erro no reconhecimento de tokens.");
                }// Fim switch
            }// Fim while
        }// Fim else

        return tok;
    }
}

*/