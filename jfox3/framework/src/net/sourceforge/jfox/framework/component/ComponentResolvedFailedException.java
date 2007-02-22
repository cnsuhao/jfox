package net.sourceforge.jfox.framework.component;

import net.sourceforge.jfox.framework.BaseException;

/**
 * @author <a href="mailto:yy.young@gmail.com">Young Yang</a>
 */
public class ComponentResolvedFailedException extends BaseException {


    public ComponentResolvedFailedException(String message) {
        super(message);
    }

    public ComponentResolvedFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void main(String[] args) {

    }
}