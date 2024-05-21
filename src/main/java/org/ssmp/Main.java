package org.ssmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try{
            SpringApplication.run(Main.class, args);
        }catch (Exception e){
            System.out.println("aaaaaaaaa" + e);
        }

      }
}