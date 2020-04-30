package self.mirafi.tdd.demo.bookforrent.service;

import self.mirafi.tdd.demo.bookforrent.form.BookSearchForm;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;

import java.util.List;



public interface BookService {
    List<Book> get(BookSearchForm form);
    Book getByIsbn(String isbn,boolean throwExceptionIfNotFounds)throws RuntimeException;
    Book getByIsbn(String isbn);

}
