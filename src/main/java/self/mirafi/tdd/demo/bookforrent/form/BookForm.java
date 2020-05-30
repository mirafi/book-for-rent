package self.mirafi.tdd.demo.bookforrent.form;

import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;

public class BookForm {
    protected String isbn;
    protected String title;

    public String getIsbn() {
        return isbn;
    }

    public BookForm setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookForm setTitle(String title) {
        this.title = title;
        return this;
    }



}
