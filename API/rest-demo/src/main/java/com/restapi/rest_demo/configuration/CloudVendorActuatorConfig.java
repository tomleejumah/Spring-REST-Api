package com.restapi.rest_demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Endpoint(id = "management-dashboard")
public class CloudVendorActuatorConfig {

    @Autowired
    private Environment env;

    @Bean
    public HealthIndicator customHealthIndicator() {
        return () -> {
            try {
                if (isServiceUp()) {
                    return Health.up().withDetail("Dummy Service", "is working good").build();
                } else {
                    return Health.down().withDetail("Dummy Service", "is DOWN").build();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return Health.down().withDetail("Dummy Service", "Exception: " + exception.getMessage()).build();
            }
        };
    }

    @ReadOperation
    public Map<String, String> customEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "This is a custom endpoint");
        return response;
    }

    private boolean isServiceUp() throws IOException {
        String address = env.getProperty("dummyService.address", "127.0.0.1");
        int port = Integer.parseInt(env.getProperty("dummyService.port", "8080"));

        return isAddressReachable(address, port, 3000);
    }

    private static boolean isAddressReachable(String address, int port, int timeout) throws IOException {
        try (Socket isSocket = new Socket()) {
            // Connects this socket to the server with a specified timeout value.
            isSocket.connect(new InetSocketAddress(address, port), timeout);
            // Return true if connection successful
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            // Return false if connection fails
            return false;
        }
    }
}
