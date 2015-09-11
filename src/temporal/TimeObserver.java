/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal;

import temporal.Time;

/**
 *
 * @author drellem
 */
public interface TimeObserver {
    public void observe(Time absolute, Time delta);
}
