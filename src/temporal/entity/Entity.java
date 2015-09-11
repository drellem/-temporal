/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import temporal.map.Map;

/**
 *
 * @author drellem
 */
public class Entity {
    private final Integer id;
    private static List<Integer> allEntities = new LinkedList<>();
    private static HashMap<Class, HashMap<Integer, ? extends Component>> componentStores = new HashMap<>();
    private static int entityNum = 0; //The unique id for the entities produced
    
    public Integer getId(){ return this.id; }

    public static <T> T getComponent( Integer e, Class<T> exampleClass ){
        HashMap<Integer, ? extends Component> store = componentStores.get( exampleClass );
        T result = (T) store.get( e );
        if( result == null )
            throw new IllegalArgumentException( "GET FAIL: "+e+" does not possess Component of class\n   missing: "+exampleClass );
        return result;
    }

    public static Integer makeEntity(){
            Entity e = new Entity(++entityNum);
            allEntities.add(e.id);
            return e.id;
    }
    
    public static <T extends Component> void addComponent(int entity, T component){
        HashMap<Integer, ? extends Component> store = componentStores.get(component.getClass());
        if(store==null){
            store = new HashMap<>();
            componentStores.put(component.getClass(),store);
        }
        ((HashMap<Integer, T>)store).put(entity, component);
    }
    
    public static <T extends Component> Set<Integer> allEntitiesWithComponent(Class<T> componentType){
        HashMap<Integer, ? extends Component> store = componentStores.get(componentType);
        if(store==null)return new HashSet<>();
        return componentStores.get(componentType).keySet();
    }
    
    public static Set<Integer> allNPCsInCurrentLocation(){
        Set<Integer> npcs = Entity.allEntitiesWithComponent(NPC.class);
        Predicate<Integer> p = (Integer i) -> !Entity.getComponent(i, NPC.class).getLocation().equals(Map.getCurrentNode().getMeta());
        npcs.removeIf(p);
        return npcs;
    }
    
    private Entity(int id){
        this.id = id;       
    }
    
}
