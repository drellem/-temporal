/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.entity;

import temporal.Time;

/**
 *
 * @author drellem
 */
public abstract class Updatable extends Component {
    public abstract void update(Time t); //Requires change in time
}
