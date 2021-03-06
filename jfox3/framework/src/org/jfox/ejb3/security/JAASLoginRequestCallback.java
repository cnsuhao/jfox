/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package org.jfox.ejb3.security;

import java.util.List;
import java.util.ArrayList;
import javax.security.auth.callback.Callback;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public class JAASLoginRequestCallback implements Callback {

    private List<String> params = new ArrayList<String>();

    public List<String> getParams() {
        return params;
    }

    public void addParam(String pram){
        params.add(pram);
    }

    public void removeParam(String param){
        params.remove(param);
    }

    public static void main(String[] args) {

    }
}
