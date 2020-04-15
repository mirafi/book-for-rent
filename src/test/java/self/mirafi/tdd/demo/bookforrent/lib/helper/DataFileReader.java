package self.mirafi.tdd.demo.bookforrent.lib.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class DataFileReader {
    final static ObjectMapper objectMapper = new ObjectMapper();
    public static  <T> T get(String fileName,TypeReference<?> typeReference){
        String path = DataFileReader
                        .class
                        .getClassLoader()
                        .getResource("test-data/"+fileName)
                        .getPath();

        Object object = null;
        try {
            object = objectMapper.readValue(FileHelper.getContent(path),typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (T)object;
    }

    public static void main(String[] args) {
      Collection<Book> books =  DataFileReader.get(ENUMS.DATA_SET.PRIMARY_DATA_SET.getFileName(),new TypeReference<Collection<Book>>(){});
      books.stream().forEach(System.out::println);
        System.out.println();
    }
}



