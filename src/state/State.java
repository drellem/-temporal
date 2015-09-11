/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import temporal.Time;
import temporal.parser.Command;
import temporal.parser.Lexer;
import temporal.util.Tuple;

/**
 *
 * @author drellem
 */
public class State extends Tuple<String, Command> implements Command{
    
    private static State currentState;
    
    public static State getCurrentState(){ return currentState; }
    
    public static void init(){
        //TODO Initialize currentState
    }
    
    public State(String s, Command c){
        super(s,c);
    }

    @Override
    public Time exec(Lexer lexer) {
        return y.exec(lexer);
    }
    
}
