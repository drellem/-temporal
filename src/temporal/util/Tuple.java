/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.util;

/**
 *
 * @author drellem
 */
public class Tuple<X, Y> {
    public X x;
    public Y y;
    
    public Tuple(X x, Y y){
        this.x=x;
        this.y=y;
    }
}
