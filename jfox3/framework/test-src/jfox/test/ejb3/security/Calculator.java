/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package jfox.test.ejb3.security;

public interface Calculator {
    int add(int x, int y);

    int subtract(int x, int y);

    int plus(int x, int y);

    double devide(int x, int y);
}
