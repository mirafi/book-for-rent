package self.mirafi.tdd.demo.bookforrent.test.searchTest;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.form.BookSearchForm;
import self.mirafi.tdd.demo.bookforrent.lib.util.MockUtil;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;
import self.mirafi.tdd.demo.bookforrent.service.SearchService;
import org.junit.jupiter.api.function.Executable;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringBootTest
public class SearchTest {

    private SearchService searchService;


    @PostConstruct
    public void setUpStabs(){

        this.searchService =  MockUtil.getBehaviour(ENUMS.BEHAVIOUR.SEACH);

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

    private DynamicTest searchByTitleTest(String keyWord){


        BookSearchForm form = new BookSearchForm();
        form.setTitle(keyWord);

        List<Book> bookList = searchService.get(form);

        if(StringUtils.isBlank(form.getTitle()))return DynamicTest.dynamicTest("Search test for keyword null",null);
        if(bookList==null || bookList.size()==0)return DynamicTest.dynamicTest("Search test for keyword "+keyWord,null);

        boolean flag = bookList.stream().allMatch(book -> book.getTitle().startsWith(keyWord));

        Executable executable = ()->   assertThat(flag)
                                            .as("Keyword '"+keyWord+"' does not found in the search result")
                                            .isTrue();






        return DynamicTest.dynamicTest("Search test for keyword "+keyWord, executable);

    }


}