package self.mirafi.tdd.demo.bookforrent.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class DataFileReader {
    final static ObjectMapper objectMapper = new ObjectMapper();
    public static  <T> T get(String fileName,Class<T> tClass){
        String path = DataFileReader
                        .class
                        .getClassLoader()
                        .getResource("test-data/"+fileName)
                        .getPath();

        Object object = null;
        try {
            object = objectMapper.readValue(FileHelper.getContent(path),tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tClass.cast(object);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(DataFileReader.get("primary-book-data.json",Book[].class)));
    }
}
