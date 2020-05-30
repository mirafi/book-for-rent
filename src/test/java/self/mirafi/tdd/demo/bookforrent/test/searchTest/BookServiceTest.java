package self.mirafi.tdd.demo.bookforrent.test.searchTest;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import org.junit.Rule;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.commons.util.StringUtils;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.form.BookCreateForm;
import self.mirafi.tdd.demo.bookforrent.form.BookSearchForm;
import self.mirafi.tdd.demo.bookforrent.lib.util.MockUtil;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;
import self.mirafi.tdd.demo.bookforrent.service.BookService;
import org.junit.jupiter.api.function.Executable;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringBootTest
public class BookServiceTest {

    private BookService bookService;

    @Rule
    public ExpectedException expectedException;

    @PostConstruct
    public void setUpStabs(){

        this.bookService =  MockUtil.getBehaviour(ENUMS.BEHAVIOUR.BOOK_SERVICE);
        this.expectedException = ExpectedException.none();
    }



    @TestFactory
    public Collection<DynamicTest> searchByTitleTest() {


        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        String[] keywords = {"A","B"};
        for (String keyword:keywords) {
            DynamicTest dynamicTest =  this.searchByTitleTest(keyword);

            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;

    }


    @Test
    public void getByIsbTest(){
        String unknownIsbn = "12j2k";
        String correctIsbn = "0-2265-4291-2";


        Book book = this.bookService.getByIsbn(unknownIsbn);
        assertThat(book)
                .as("Must return null")
                .isNull();
        verify(this.bookService,times(1)).getByIsbn(unknownIsbn,false);



        book = this.bookService.getByIsbn(correctIsbn);
        assertThat(book)
                .as("Must return not null")
                .isNotNull();

        assertThat(book.getIsbn())
                .as("Isbn does not match")
                .isEqualToIgnoringCase(correctIsbn);

        verify(this.bookService,times(1)).getByIsbn(correctIsbn,false);

        try{
            this.bookService.getByIsbn(unknownIsbn,true);
            assertFalse(true,"Must throw exception");
        }catch (Exception e){

        }


    }
    private DynamicTest searchByTitleTest(String keyWord){


        BookSearchForm form = new BookSearchForm();
        form.setTitle(keyWord);

        List<Book> bookList = bookService.get(form);

        if(StringUtils.isBlank(form.getTitle()))return DynamicTest.dynamicTest("Search test for keyword null",null);
        if(bookList==null || bookList.size()==0)return DynamicTest.dynamicTest("Search test for keyword "+keyWord,null);

        boolean flag = bookList.stream().allMatch(book -> book.getTitle().startsWith(keyWord));

        Executable executable = ()->   assertThat(flag)
                                            .as("Keyword '"+keyWord+"' does not found in the search result")
                                            .isTrue();






        return DynamicTest.dynamicTest("Search test for keyword "+keyWord, executable);

    }

    @Test
    public void createTest(){

        BookCreateForm form = new BookCreateForm();

        form.setIsbn("0-2265-4291-3");
        form.setTitle("Test Book");


        Book book = this.bookService.create(form);

        /**
         * After create book properties test
         * */
        assertThat(book.getIsbn()).isEqualTo(form.getIsbn());
        assertThat(book.getTitle()).isEqualTo(form.getTitle());
        assertThat(book.getStatus()).isEqualTo(ENUMS.STATUS.AVAILABLE);
        assertThat(book.getRentalStatus()).isEqualTo(ENUMS.RENTAL_STATUS.AVAILABLE);

        /**
         * Isbn unique test
         * */
        try{
            this.bookService.create(form);
            assertFalse(true,"Must throw exception");
        }catch (RuntimeException e){

        }

        Book searchBook = this.bookService.getByIsbn(form.getIsbn());
        /**
         * After create search by isbn test
         * */
        assertThat(searchBook).isEqualTo(book);


    }

}