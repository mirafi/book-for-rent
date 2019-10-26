package self.mirafi.tdd.demo.bookforrent.lib.util;

import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.lib.helper.DataFileReader;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataLoaderUtil {
    static public Collection<Book> loadData(ENUMS.DATA_SET dataSet){
        Collection<Book> books = DataFileReader.get(dataSet.getFileName(),Collection.class);
        return books;
    }

}
