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
public class Edge {
    protected Node a, b;
    
    public Edge(Node a, Node b){
        this.a = a;
        this.b = b;
    }
    
    public boolean containsNode(String s){
        Object oa = a.getMeta();
        Object ob = b.getMeta();
        if(oa instanceof Location){
            Location la = (Location)oa;
            String sa = la.getId();
            if(s.equals(sa))return true;
        }
        if(ob instanceof Location){
            Location lb = (Location)ob;
            String sb = lb.getId();
            if(s.equals(sb))return true;
        }
        return false;
    }
}
