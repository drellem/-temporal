/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.map;

import java.util.ArrayList;
import temporal.Temporal;
import temporal.Time;
import temporal.util.Tuple;

/**
 *
 * @author drellem
 */
public class Map  {
    private ArrayList<MapEdge> edges;
    private ArrayList<Node<Location>> nodes;
    private static Map currentMap;
    private static Node<Location> currentNode;
    public static Location bar;
    public static Location house;
    
    public static Map getCurrentMap(){ return currentMap; }
    public static Node<Location> getCurrentNode(){ return currentNode; }
    public static Map setDefaultMap(){
        Location locus1, locus2;
        locus1 = new Location("HOUSE", lexer -> {
            System.out.println("You are in the house\n");
            return Temporal.nullTime;
        });
        house = locus1;
        locus2 = new Location("BAR", lexer -> {
            System.out.println("You are in the bar\n");
            return Temporal.nullTime;
        });
        bar = locus2;
        Node<Location> node1, node2;
        node1 = new Node(locus1);
        node2 = new Node(locus2);
        MapEdge e = new MapEdge(node1, node2, Time.deltaMinute);
        ArrayList<MapEdge> tEdges = new ArrayList<>();
        tEdges.add(e);
        ArrayList<Node<Location>> tNodes = new ArrayList<>();
        tNodes.add(node1);
        tNodes.add(node2);
        currentMap = new Map(tEdges);
        currentMap.setNodes(tNodes);
        currentNode = node2;
        return currentMap;
    }
    
    private Map(){
        edges = new ArrayList<>();
    }
    
    public Map(ArrayList<MapEdge> edges){
        this.edges = edges;
    }
    
    public static Map init(){
        currentMap = new Map();
        return currentMap;
    }
    
    public static Map init(ArrayList<MapEdge> edges){
        currentMap = new Map(edges);
        return currentMap;
    }
    
    public void setNodes(ArrayList<Node<Location>> nodes){
        this.nodes = nodes;
    }
    private MapEdge isAdjacentP(String s){
        for(MapEdge e : this.edges){
            if(e.containsNode(s)){
                if(e.containsNode(currentNode.getMeta().getId()))return e;
            }
        }
        return null;
    }
    
    private Time goP(String s, MapEdge e){
        for(MapEdge m : this.edges){
            if(m.containsNode(s)){
                Tuple<Node<Location>,Time> t = m.getNode(s);
                currentNode = t.x;
                currentNode.getMeta().getCommand().exec(null);
                return t.y;
            }
        }
        return new Time();
    }
    
    /**
     * Checks to see if a given node is adjacent to the current node.
     * @param s Id of the node to be checked.
     * @return 
     */
    public static MapEdge isAdjacent(String s){
        return currentMap.isAdjacentP(s);
    }
    
    public static Time go(String s, MapEdge e){
        return currentMap.goP(s, e);
    }
  
}
