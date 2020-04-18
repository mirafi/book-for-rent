package self.mirafi.tdd.demo.bookforrent.lib.util;

import com.fasterxml.jackson.core.type.TypeReference;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.lib.helper.DataFileReader;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;

import java.util.Collection;

public class DataLoaderUtil {
    static public Collection<Book> loadData(ENUMS.DATA_SET dataSet){
        Collection<Book> books = DataFileReader.get(dataSet.getFileName(),new TypeReference<Collection<Book>>(){});
        return books;
    }

}
