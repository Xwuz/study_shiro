package com.spring.shiro.session;

import com.spring.shiro.util.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tiandao
 * @date 2018/9/4 15:26
 */
@Component
public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private JedisUtil jedisUtil;

    private final String SHIRO_SESSION_PREFIX = "shiro-session:";

    private byte[] getKey(String key){
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    // 提取出一个保存session的方法
    private void saveSession(Session session){
        if (session != null && session.getId() != null){
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            jedisUtil.set(key,value);
            jedisUtil.expire(key,600); // 10分钟
        }
    }


    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("create session-------------------");
        // 创建session
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("read session-------------------");
        // 获得session
        if (sessionId == null){
            return null;
        }

        byte[] key = getKey(sessionId.toString());
        byte[] value = jedisUtil.getValue(key);
        // 反序列化为session对象
        return (Session) SerializationUtils.deserialize(value);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        // 更新
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        // 删除
        if (session == null || session.getId() == null){
            return;
        }
        byte[] key = getKey(session.getId().toString());
        jedisUtil.del(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        // 获取指定存活的session
        // 获取到所有key
        Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions = new HashSet<>();
        // 如果keys为空集合，则直接返回
        if (CollectionUtils.isEmpty(keys)){
            return sessions;
        }
        for (byte[] key : keys){
            Session session = (Session)
                    SerializationUtils.deserialize(jedisUtil.getValue(key));
            sessions.add(session);
        }
        return sessions;
    }
}
