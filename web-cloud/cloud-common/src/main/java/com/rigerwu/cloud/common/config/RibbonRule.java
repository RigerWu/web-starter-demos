package com.rigerwu.cloud.common.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

/**
 *
 * @author : riger
 * @date : 2021/5/16
 */
@Configuration
public class RibbonRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Bean
    Properties thisNacosProperties() {
        return nacosDiscoveryProperties.getNacosProperties();
    }
    /**
     * 该配置会对全局生效
     */
    @Bean
    @Scope("prototype")
    IRule loadBalancerRule() {
        return new NacosRule();
    }
}
