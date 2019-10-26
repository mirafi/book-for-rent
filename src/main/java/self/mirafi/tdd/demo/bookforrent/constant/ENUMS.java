package self.mirafi.tdd.demo.bookforrent.constant;

public class ENUMS {
    public enum DATA_SET{
        PRIMARY_DATA_SET("primary-book-data.json");
        String fileName;

        DATA_SET(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
    public enum  RENTAL_STATUS {
        AVAILABLE,UNAVAILABLE;
    }
    public enum  STATUS {
        AVAILABLE,UNAVAILABLE,LOST,DAMAGED
    }

    public enum BEHAVIOUR{
        SEACH;
    }
}
