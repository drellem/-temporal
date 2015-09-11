/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.parser;
import java.util.ArrayList;

/**
 * Takes a Stream string, breaks it into its constituent parts and sends it to the interpreter.
 * The lexer is also a string itself. 
 * This may be overkill, but if the commands ever need to become more complex, it will be handy to have this step.
 * @author drellem
 */
public class Lexer {

    private final StringStream stream;
    
    /**
     *
     * @param stream
     */
    public Lexer(StringStream stream){
        this.stream = stream;
    }
    
    
    public Token next(){
        char curr = this.stream.next();
        if(curr=='\n')return new Token(Token.TOKENTYPE.FINAL, "");
        if (Character.isWhitespace(curr))return next();
        if (Character.isAlphabetic(curr))return id(curr);
        if (Character.isDigit(curr))return num(curr);
        return new Token(Token.TOKENTYPE.UNKNOWN, String.valueOf(curr));
        
    }
    
    public Token id(char curr){
        ArrayList<Character> ret = new ArrayList<>();
        ret.add(curr);
        char curr2 = stream.next();
        while(Character.isAlphabetic(curr2)){
            ret.add(curr2);
            curr2 = stream.next();
        }
        String ret2 = "";
        for(char c : ret)
            ret2 = ret2 + c;
        return new Token(Token.TOKENTYPE.ID, ret2);
    }
    
    public Token num(char curr){
        ArrayList<Character> ret = new ArrayList<>();
        ret.add(curr);
        char curr2 = stream.next();
        while(Character.isDigit(curr2)){
            ret.add(curr2);
            curr2 = stream.next();
        }
        String ret2 = "";
        for(char c : ret)
            ret2 = ret2 + c;
        return new Token(Token.TOKENTYPE.NUM, ret2);
        
    }

}
