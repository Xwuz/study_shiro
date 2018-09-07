package com.spring.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @author tiandao
 * @date 2018/9/4 16:53
 */
public class CustomSessionManager extends DefaultWebSessionManager {


    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey){
            // 转为servletRequest
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        // 如果request和sessionId中有则直接取出返回
        if (request != null && sessionId != null){
            Session session = (Session) request.getAttribute(sessionId.toString());
            if (session != null){
                return session;
            }
        }
        // 如果request和sessionId中没有则从redis读取并存放到request中
        Session session = super.retrieveSession(sessionKey);
        if (request != null && sessionId != null){
            request.setAttribute(sessionId.toString(),session);
        }
        return session;
    }
}
