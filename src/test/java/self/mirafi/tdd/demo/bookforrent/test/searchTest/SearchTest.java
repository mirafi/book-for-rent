package self.mirafi.tdd.demo.bookforrent.test.searchTest;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.form.BookSearchForm;
import self.mirafi.tdd.demo.bookforrent.lib.util.MockUtil;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;
import self.mirafi.tdd.demo.bookforrent.service.SearchService;

import javax.annotation.PostConstruct;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {

    private SearchService searchService;


    @PostConstruct
    public void setUpStabs(){

        this.searchService =  MockUtil.getBehaviour(ENUMS.BEHAVIOUR.SEACH);

    }

    @Test
    public void searchByTitleTest() {
        String keyWord = "A";
        BookSearchForm form = new BookSearchForm();
        form.setTitle(keyWord);

        List<Book> bookList = searchService.get(form);

        if(StringUtils.isBlank(form.getTitle()))return;
        if(bookList==null || bookList.size()==0)return;

        boolean flag = bookList.stream().allMatch(book -> book.getTitle().startsWith(keyWord));

        assertThat(flag)
                .as("Keyword '"+keyWord+"' does not found in the search result")
                .isTrue();
    }


}