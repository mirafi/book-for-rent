package self.mirafi.tdd.demo.bookforrent.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;

public class Book {
    private String isbn;
    private String title;
    private ENUMS.STATUS status;
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

    public ENUMS.STATUS getStatus() {
        return status;
    }

    public void setStatus(ENUMS.STATUS status) {
        this.status = status;
    }

    public ENUMS.RENTAL_STATUS getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(ENUMS.RENTAL_STATUS rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (status != book.status) return false;
        return rentalStatus == book.rentalStatus;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (rentalStatus != null ? rentalStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", rentalStatus=" + rentalStatus +
                '}';
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(new Book()));
    }
}
