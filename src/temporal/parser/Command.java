/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.parser;

import temporal.Time;
/**
 *
 * @author drellem
 */
public interface Command {
    public Time exec(Lexer lexer);
}
