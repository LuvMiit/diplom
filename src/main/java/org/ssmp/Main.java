package org.ssmp;

import com.aspose.words.*;
import com.aspose.words.Document;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.ssmp.model.Post;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
public class Main {

    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(Main.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }



    }

}