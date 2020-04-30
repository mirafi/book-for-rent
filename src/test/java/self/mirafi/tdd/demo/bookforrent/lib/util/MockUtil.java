package self.mirafi.tdd.demo.bookforrent.lib.util;

import org.mockito.Mockito;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
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
        BookService searchService = Mockito.mock(BookService.class);

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
            }).when(searchService)
                .get(any(BookSearchForm.class));


        doAnswer(i->{
            String isbn = i.getArgument(0);
            boolean throwException = i.getArgument(1);

            Book book = books.stream()
                    .filter(b -> b.getIsbn().equals(isbn))
                    .findFirst().orElse(null);
            if(book==null && throwException )throw new RuntimeException("Book not found");
            return book;

        }).when(searchService)
                .getByIsbn(any(String.class),any(Boolean.class));

        doAnswer(i->{
            String isbn = i.getArgument(0);
            return searchService.getByIsbn(isbn,false);

        }).when(searchService)
                .getByIsbn(any(String.class));

        return searchService;
    }


}