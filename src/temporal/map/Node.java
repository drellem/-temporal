/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.map;

/**
 *
 * @author drellem
 */
public class Node<T> {
    private T meta;
    
    public Node(T meta){
        this.meta = meta;
    }
        
    public T getMeta(){ return this.meta; }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Node){
            Node n = (Node)o;
            return meta.equals(n.getMeta());
        }
        return false;
    }
    
}

