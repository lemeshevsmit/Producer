package com.mycompany.app.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;



class ProjectPropertiesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectPropertiesTest.class);

    @BeforeAll
    static void beforeAll() {
        LOGGER.info("Start testing pojo factory");
    }


    @Test
    void constructor(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        try {
            ProjectProperties properties = ProjectProperties.getProperties();
            Properties prop = new Properties();
            prop.load(ProjectProperties.class.getClassLoader().getResourceAsStream("application.properties"));
            String nameOfQueue = prop.getProperty("my.active.mq.name");
            String runTime = prop.getProperty("my.active.mq.time");
            String connectionLink = prop.getProperty("my.active.mq.connection.link");
            String poisonText = prop.getProperty("my.active.mq.poison.name");
            String username = prop.getProperty("my.active.mq.poison.connection.username");
            String password = prop.getProperty("my.active.mq.poison.connection.password");
            Assertions.assertEquals(nameOfQueue,properties.nameOfQueue);
            Assertions.assertEquals(runTime,properties.runTime);
            Assertions.assertEquals(connectionLink,properties.connectionLink);
            Assertions.assertEquals(poisonText,properties.poisonMessage);
            Assertions.assertEquals(username,properties.username);
            Assertions.assertEquals(password,properties.password);
        } catch (IOException ignored) {
        }
        LOGGER.info("Status OK");
    }

}