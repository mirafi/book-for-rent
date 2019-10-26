package self.mirafi.tdd.demo.bookforrent.form;


import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;

public class BookSearchForm {
    private String isbn;
    private String title;
    private ENUMS.RENTAL_STATUS rentalStatus;


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

    public ENUMS.RENTAL_STATUS getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(ENUMS.RENTAL_STATUS rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
