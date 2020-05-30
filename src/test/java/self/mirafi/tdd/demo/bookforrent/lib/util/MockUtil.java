package self.mirafi.tdd.demo.bookforrent.lib.util;

import org.mockito.Mockito;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.form.BookCreateForm;
import self.mirafi.tdd.demo.bookforrent.form.BookSearchForm;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;
import self.mirafi.tdd.demo.bookforrent.service.BookService;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

public class MockUtil {
    public static <T> T getBehaviour(ENUMS.BEHAVIOUR behaviour){
        switch (behaviour){
            case BOOK_SERVICE:
            BookService searchService = getBookServiceStubs();
            return (T)searchService;
        }

        return null;
    }
    private static BookService getBookServiceStubs(){
        Collection<Book> books =  DataLoaderUtil.loadData(ENUMS.DATA_SET.PRIMARY_DATA_SET);
        BookService bookService = Mockito.mock(BookService.class);


        doAnswer(i->{
            BookSearchForm form = i.getArgument(0);
            String keyWord = form.getTitle();
            Collection<Book> bookList = null;
            try {
                bookList = books.stream()
                        .filter(b -> b.getTitle().startsWith(keyWord))
                        .peek(System.out::println)
                        .collect(Collectors.toList());
            }catch (Exception e){
                e.printStackTrace();
            }
            return bookList;
            }).when(bookService)
                .get(any(BookSearchForm.class));


        doAnswer(i->{
            String isbn = i.getArgument(0);
            boolean throwException = i.getArgument(1);

            Book book = books.stream()
                    .filter(b -> b.getIsbn().equals(isbn))
                    .findFirst().orElse(null);
            if(book==null && throwException )throw new RuntimeException("Book not found");
            return book;

        }).when(bookService)
                .getByIsbn(any(String.class),any(Boolean.class));

        doAnswer(i->{
            String isbn = i.getArgument(0);
            return bookService.getByIsbn(isbn,false);

        }).when(bookService)
                .getByIsbn(any(String.class));


        /**
         * Create method Stubs
         * */
        doAnswer(i->{
            BookCreateForm form = i.getArgument(0);

            Book book = bookService.getByIsbn(form.getIsbn());

            if(book!=null)throw new RuntimeException("Book already exist");

            book =new Book();
            book.setTitle(form.getTitle());
            book.setIsbn(form.getIsbn());
            book.setStatus(ENUMS.STATUS.AVAILABLE);
            book.setRentalStatus(ENUMS.RENTAL_STATUS.AVAILABLE);

            books.add(book);

            return book;

        }).when(bookService)
            .create(any(BookCreateForm.class));

        return bookService;
    }


}