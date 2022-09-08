//package ru.netology.cloudstorage;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.containers.Network;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.Map;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
//class CloudStorageApplicationTests {
//
//    private static final int PORT_DATABASE = 3306;
//    private static final int PORT_SERVER = 5500;
//    private static final String DATABASE_NAME = "netology";
//    private static final String DATABASE_USERNAME = "root";
//    private static final String DATABASE_PASSWORD = "root";
//    private static final Network CLOUD_NETWORK = Network.newNetwork();
//
//    @Container
//    public static MySQLContainer<?> databaseContainer = new MySQLContainer<>("mysql")
//            .withNetwork(CLOUD_NETWORK)
//            .withExposedPorts(PORT_DATABASE)
//            .withDatabaseName(DATABASE_NAME)
//            .withUsername(DATABASE_USERNAME)
//            .withPassword(DATABASE_PASSWORD);
//
//    @Container
//    public static GenericContainer<?> serverContainer = new GenericContainer<>("backend")
//            .withNetwork(CLOUD_NETWORK)
//            .withExposedPorts(PORT_SERVER)
//            .withEnv(Map.of("SPRING_DATASOURCE_URL", "jdbc:mysql://database:" + PORT_DATABASE + "/" + DATABASE_NAME))
//            .dependsOn(databaseContainer);
//
//    @Test
//    void contextDatabase() {
//        Assertions.assertTrue(databaseContainer.isRunning());
//    }
//
//    @Test
//    void contextServer() {
//        Assertions.assertFalse(serverContainer.isRunning());
//    }
//
//}
