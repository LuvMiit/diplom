package org.ssmp.Utils;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DocumentUtil {



    public static byte[] fillTemplate(Map<String, String> placeholderMap, String path) throws FileNotFoundException {

        System.out.println(placeholderMap);
        try {
            FileInputStream fis = new FileInputStream(path);
            XWPFDocument document = new XWPFDocument(fis);

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {

                            text = text.replace(entry.getKey(), entry.getValue());
                        }
                        run.setText(text, 0);
                    }
                }
            }
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            for (XWPFRun run : paragraph.getRuns()) {
                                String text = run.getText(0);
                                if (text != null) {
                                    for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {
                                        text = text.replace(entry.getKey(), entry.getValue());
                                    }
                                    run.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileOutputStream fos = new FileOutputStream("output.docx");
            document.write(fos);
            document.write(bos);
            bos.close();
            System.out.println(document);
            document.close();
            System.out.println("Шаблон успешно заполнен и сохранен");
            return bos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при заполнении шаблона документа.");
        }
        return null;
    }
}
