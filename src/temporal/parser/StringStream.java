/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.parser;

/**
 * 
 * Is the wrapper used for strings before they are sent to the lexer.
 * @author drellem
 */
public class StringStream {
    private final char[] stream;
    private int index = -1;
    
    public StringStream(char[] stream){
        this.stream = stream;
    }
    
    public char next(){
        return stream[++index];
    }
    
}
