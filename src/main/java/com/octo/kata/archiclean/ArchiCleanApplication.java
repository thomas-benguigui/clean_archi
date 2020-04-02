package com.octo.kata.archiclean;

import com.octo.kata.archiclean.controller.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = MainController.class)
public class ArchiCleanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchiCleanApplication.class, args);
    }

}
