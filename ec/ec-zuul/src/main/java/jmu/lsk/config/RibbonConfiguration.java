package jmu.lsk.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnBean(SpringClientFactory.class)
public class RibbonConfiguration {

    private static final Log log = LogFactory.getLog(RibbonConfiguration.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public IPing ribbonPing() {
        return new DummyPing();
    }

    @Bean
    public IRule ribbonRule(IClientConfig clientConfig) {
        AvailabilityFilteringRule rule = new AvailabilityFilteringRule();
        rule.initWithNiwsConfig(clientConfig);
        return rule;
    }

    @Bean
    public ServerList<?> ribbonServerList(IClientConfig clientConfig) {

        return new ServerList<Server>() {
            @Override
            public List<Server> getInitialListOfServers() {
                return new ArrayList<>();
            }

            @Override
            public List<Server> getUpdatedListOfServers() {
                List<Server> serverList = new ArrayList<>();
                List<ServiceInstance> instances = discoveryClient.getInstances(clientConfig.getClientName());
                if (instances != null && instances.size() == 0) {
                    return serverList;
                }
                for (ServiceInstance instance : instances) {
                    if (instance.isSecure()) {
                        serverList.add(new Server("https", instance.getHost(), instance.getPort()));
                    } else {
                        serverList.add(new Server("http", instance.getHost(), instance.getPort()));
                    }
                }
                return serverList;
            }
        };
    }

}
