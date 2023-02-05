package com.rest.order.config;



import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

@Configuration
public class NeptuneDBReaderConnection {

    @Bean
    public GraphTraversalSource graphTraversalSource() {
        GraphTraversalSource g = traversal().withRemote(DriverRemoteConnection.using("localhost",8182,"g"));
        return g;
    }
}
