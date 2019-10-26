package self.mirafi.tdd.demo.bookforrent.lib.util;

import org.mockito.Mockito;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.form.BookSearchForm;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;
import self.mirafi.tdd.demo.bookforrent.service.SearchService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

public class MockUtil {
    public static <T> T getBehaviour(ENUMS.BEHAVIOUR behaviour){
        switch (behaviour){
            case SEACH:
            SearchService searchService = getSearchBehaviour();
            return (T)searchService;
        }

        return null;
    }
    private static SearchService getSearchBehaviour(){
        Collection<Book> books =  DataLoaderUtil.loadData(ENUMS.DATA_SET.PRIMARY_DATA_SET);
        books.stream().forEach(System.out::println);
        SearchService searchService = Mockito.mock(SearchService.class);

        doAnswer(i->{
            BookSearchForm form = i.getArgument(0);
            String keyWord = form.getTitle();
            List<Book> bookList = null;
            try {
                bookList = books.stream()
                        .filter(b -> b.getTitle().contains(keyWord))
                        .peek(System.out::println)
                        .collect(Collectors.toList());
            }catch (Exception e){
                e.printStackTrace();
            }
            return bookList;
            }).when(searchService)
                .get(any(BookSearchForm.class));



        return searchService;
    }

    public static void main(String[] args) {

        System.out.println();
    }
}