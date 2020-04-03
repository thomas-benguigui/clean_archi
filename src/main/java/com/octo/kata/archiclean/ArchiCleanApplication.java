package com.octo.kata.archiclean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.octo.kata.archiclean")
public class ArchiCleanApplication {

    private static final Logger logger = LoggerFactory.getLogger("kata-logback");

    public static void main(String[] args) {
        SpringApplication.run(ArchiCleanApplication.class, args);

        logger.warn("LOGBACK KATA - Test WARN message");
        logger.info("LOGBACK KATA - Test INFO message");
        logger.error("LOGBACK KATA - Test ERROR message");

        logger.debug("LOGBACK KATA - Test DEBUG message");
        logger.trace("LOGBACK KATA - Test TRACE message");
    }

}
