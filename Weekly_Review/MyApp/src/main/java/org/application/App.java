package org.application;

import org.first.Basket;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Basket basket=new Basket();
        String[] eats=basket.display();
        System.out.println(Arrays.toString(eats));
    }
}
