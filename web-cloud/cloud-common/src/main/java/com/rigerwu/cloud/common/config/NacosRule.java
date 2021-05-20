package com.rigerwu.cloud.common.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.rigerwu.cloud.common.consts.CloudConsts.METADATA_API_VERSION;


/**
 * created by riger on 2021/2/9
 */
@Slf4j
public class NacosRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    private NacosServiceManager nacosServiceManager;
    @Resource(name = "thisNacosProperties")
    private Properties nacosProperties;

    public NacosRule() {
    }

    @Override
    public Server choose(Object key) {
        try {
            String clusterName = this.nacosDiscoveryProperties.getClusterName();
            String currentVersion = this.nacosDiscoveryProperties.getMetadata().get(METADATA_API_VERSION);
            String group = this.nacosDiscoveryProperties.getGroup();
            DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer)this.getLoadBalancer();
            String name = loadBalancer.getName();
            NamingService namingService = this.nacosServiceManager.getNamingService(nacosProperties);
            List<Instance> instances = namingService.selectInstances(name, group, true);
            if (CollectionUtils.isEmpty(instances)) {
                log.warn("no instance in service {}", name);
                return null;
            } else {
                List<Instance> instancesToChoose = instances;
                if (StrUtil.isNotEmpty(clusterName)) {
                    List<Instance> sameClusterInstances = instances.stream()
                            .filter((instancex) -> Objects.equals(clusterName, instancex.getClusterName()))
                            .filter(instance -> {
                                String targetVersion = instance.getMetadata().get(METADATA_API_VERSION);
                                if (!StrUtil.isEmpty(targetVersion)) {
                                    return "*".equals(targetVersion) || targetVersion.equals(currentVersion);
                                }
                                return true;
                            })
                            .collect(Collectors.toList());

                    if (!CollectionUtils.isEmpty(sameClusterInstances)) {
                        instancesToChoose = sameClusterInstances;
                    } else {
                        log.debug("A cross-cluster call occurs，name = {}, clusterName = {}, instance = {}",
                                new Object[]{name, clusterName, instances});
                    }
                }

                if(log.isDebugEnabled()){
                    instancesToChoose.forEach(instance -> log.debug(instance.toString()));
                }
                Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToChoose);
                log.info("final instance: " + instance.toString());
                return new NacosServer(instance);
            }
        } catch (Exception var10) {
            log.warn("NacosRule error", var10);
            return null;
        }
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        // do nothing
    }
}
