package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author xuan
 * @date 18/6/4 下午3:15
 */
@Component
public class WebSocketConfig {

    /**
     * 测试该句会出现异常ServerEndpointExporter causes refresh to fail with java.lang.IllegalStateException: javax.websocket.server.ServerContainer not available
     * 请前去把cn.chenhaoxiang.config.WebSocketConfig中serverEndpointExporter方法上的Bean注解注释
     * 问题解决: https://jira.spring.io/browse/SPR-12340
     *          https://jira.spring.io/browse/SPR-12109
     *
     * @throws Exception
     */

//    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
