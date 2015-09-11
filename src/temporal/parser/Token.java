/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.parser;

/**
 *
 * @author drellem
 */
public class Token {
    public enum TOKENTYPE  { ID, NUM, UNKNOWN, FINAL };
    
    private final TOKENTYPE type;
    private final String meta;
    
    public Token(TOKENTYPE type, String meta){
        this.type = type;
        this.meta = meta;
    }
    
    public TOKENTYPE getType() { return this.type; }
    public String getMeta() { return this.meta; }
}
