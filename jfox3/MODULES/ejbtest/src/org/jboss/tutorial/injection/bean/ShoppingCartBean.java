/*
* JBoss, Home of Professional Open Source
* Copyright 2005, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.tutorial.injection.bean;

import java.util.HashMap;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.EJB;


@Stateful
@Remote(ShoppingCart.class)
public class ShoppingCartBean implements ShoppingCart, java.io.Serializable {
    private HashMap<String, Integer> cart = new HashMap<String, Integer>();

    @EJB
    private Calculator calculator;

    @EJB
    private Calculator set;


    public void setCalculator(Calculator c) {
        set = c;
    }


    public void buy(String product, int quantity) {
        if (cart.containsKey(product)) {
            int currq = cart.get(product);
            currq = calculator.add(currq, quantity);
            cart.put(product, currq);
        }
        else {
            cart.put(product, quantity);
        }
    }

    public HashMap<String, Integer> getCartContents() {
        return cart;
    }

    @Remove
    public void checkout() {
        System.out.println("To be implemented");
    }
}