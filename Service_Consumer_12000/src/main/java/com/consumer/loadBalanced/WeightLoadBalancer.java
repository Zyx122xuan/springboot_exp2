package com.consumer.loadBalanced;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeightLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    final String serviceId;
    ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public WeightLoadBalancer(ObjectProvider<ServiceInstanceListSupplier>
                                      serviceInstanceListSupplierProvider, String serviceId) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                .getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map(this::select);
    }

    private Response<ServiceInstance> select(List<ServiceInstance> instances) {
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }
        // 简单模拟权重，仅作演示
        // 11000：3  11001：1
        int[] ports = {11000, 11000, 11000, 11001};
        int port = ports[ThreadLocalRandom.current().nextInt(ports.length)];
        for (ServiceInstance instance : instances) {
            if (port == instance.getPort()) {
                return new DefaultResponse(instance);
            }
        }
        return new DefaultResponse(instances.get(0));
    }
}

