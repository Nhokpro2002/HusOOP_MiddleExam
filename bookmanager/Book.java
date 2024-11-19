package hus.oop.bookmanager;

public class Book implements MyBookComparable {
    private String title;
    private String author;
    private String genre;
    private int pages;
    private double price;
    private String publisher;

    private Book() {}

    public static class BookBuilder {
        private String title;
        private String author;
        private String genre;
        private int pages;
        private double price;
        private String publisher;

        public BookBuilder(String title) {
            this.title = title;
        }

        public BookBuilder withAuthor(String author) {
            /* TODO */
            return null;
        }

        public BookBuilder withGenre(String genre) {
            /* TODO */
            return null;
        }

        public BookBuilder withPages(int pages) {
            /* TODO */
            return null;
        }

        public BookBuilder withPrice(double price) {
            /* TODO */
            return null;
        }

        public BookBuilder withPublisher(String publisher) {
            /* TODO */
            return null;
        }

        public Book build() {
            Book book = new Book();
            book.title = this.title;
            book.author = this.author;
            book.genre = this.genre;
            book.pages = this.pages;
            book.price = this.price;
            book.publisher = this.publisher;
            return book;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        /* TODO */
        return null;
    }

    /**
     * Phương thức đưa ra tiêu chí so sánh 2 đối tượng kiểu Book
     *  sao chothứ tự tăng dần theo title.
     * @param another
     * @return
     */
    @Override
    public int compareTo(Book another) {
        /* TODO */
        return 0;
    }
}

