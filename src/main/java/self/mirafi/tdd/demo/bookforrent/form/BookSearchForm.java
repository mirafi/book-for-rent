package self.mirafi.tdd.demo.bookforrent.form;


import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;

public class BookSearchForm extends BookForm {
    protected ENUMS.RENTAL_STATUS rentalStatus;

    public ENUMS.RENTAL_STATUS getRentalStatus() {
        return rentalStatus;
    }

    public BookForm setRentalStatus(ENUMS.RENTAL_STATUS rentalStatus) {
        this.rentalStatus = rentalStatus;
        return this;
    }

}
