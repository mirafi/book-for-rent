package self.mirafi.tdd.demo.bookforrent.test.searchTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.form.BookSearchForm;
import self.mirafi.tdd.demo.bookforrent.lib.util.MockUtil;
import self.mirafi.tdd.demo.bookforrent.service.SearchService;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {

    private SearchService searchService;


    @PostConstruct
    public void setUpData(){
        BookSearchForm form = new BookSearchForm();
        form.setTitle("A");
        searchService =  MockUtil.getBehaviour(ENUMS.BEHAVIOUR.SEACH);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println(searchService.get(form));
    }
    @Test
    public void contextLoads() {
    }
}
