package edu.workana.web.testing.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class BaseMongoContainer {

  static final MongoDBContainer MONGO_DB_CONTAINER;

  static {
    MONGO_DB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:latest"));
    MONGO_DB_CONTAINER.start();
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.data.mongodb.auto-index-creation", () -> true);
    dynamicPropertyRegistry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
  }
}
