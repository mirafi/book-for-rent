package self.mirafi.tdd.demo.bookforrent.form;

import self.mirafi.tdd.demo.bookforrent.constant.RENTAL_STATUS;

public class BookSearchForm {
    private String isbn;
    private String title;
    private RENTAL_STATUS rentalStatus;


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RENTAL_STATUS getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RENTAL_STATUS rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
