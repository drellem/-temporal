/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.parser;
/**
 * Contains the code that parses and interprets commands received from the console.
 * Obviously this needs to be easily extensible to add new commands. We only tokenize since parsing is command-dependent and extremely simple.
 * Adding new commands merely requires adding a Command class to the HashMap in Temporal.java, which is made slightly easier with Java 8's lambda expressions.
 */