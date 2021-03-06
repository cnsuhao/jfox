/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package jfox.test.ejb3.mdb;

import javax.jms.Message;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public interface MessageSender {

    /**
     * send queue message
     */
    void sendQueueMessage(Message message);

    /**
     * send topic message
     */
    void sendTopicMessage(Message message);
}
