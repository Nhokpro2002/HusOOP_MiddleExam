package hus.oop.bookmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class BookManager {
    private List<Book> bookList;

    public BookManager() {
        bookList = new ArrayList<>();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Kiểm tra xem chỉ số index có nằm trong đoạn [0 - limit] hay không.
     * @param index
     * @param limit
     * @return
     */
    private boolean checkBoundaries(int index, int limit) {
        return index >= 0 && index < limit;
    }

    /**
     * Thêm book vào cuối danh sách.
     * @param book
     */
    public void insertAtEnd(Book book) {
        bookList.add(book);
    }

    /**
     * Thêm book vào danh sách ở vị trí index.
     * @param book
     * @param index
     */
    public void insertAPos(Book book, int index) {
        if (checkBoundaries(index, bookList.size())) {
            bookList.add(index, book);
        } else {
            System.out.println("Index out of bounds.");
        }
    }

    /**
     * Xóa book ở vị trí index.
     * @param index
     */
    public void remove(int index) {
        if (checkBoundaries(index, bookList.size())) {
            bookList.remove(index);
        } else {
            System.out.println("Index out of bounds.");
        }
    }

    /**
     * Bỏ book như tham số truyền vào.
     * @param book
     */
    public void remove(Book book) {
        bookList.remove(book);
    }

    /**
     * Lấy ra book ở vị trí index
     * @param index
     * @return
     */
    public Book bookAt(int index) {
        if (checkBoundaries(index, bookList.size())) {
            return bookList.get(index);
        }
        return null;
    }

    /**
     * Sắp xếp danh sách book theo thứ tự tăng dần theo title.
     * @return
     */
    public List<Book> sortIncreasingByTitle() {
        bookList.sort(Comparator.comparing(Book::getTitle));
        return bookList;
    }

    /**
     * Sắp xếp sách theo tiêu chí, đầu tiên theo title tăng dần, nếu title như nhau thì theo thứ tự giá giảm dần.
     * Sử dụng giao diện MyBookComparator để thực hiện tiêu chí sắp xếp.
     * @return
     */
    public List<Book> sortIncreasingTitleAndPrice() {
        bookList.sort(Comparator.comparing(Book::getTitle)
                .thenComparing(Comparator.comparingDouble(Book::getPrice).reversed()));
        return bookList;
    }

    /**
     * Sắp xếp sách theo tiêu chí, đầu tiên theo title giảm dần, nếu title như nhau thì theo thứ tự giá giảm dần.
     * Sử dụng giao diện MyBookComparator để thực hiện tiêu chí sắp xếp.
     * @return
     */
    public List<Book> sortDecreasingTitleAndPrice() {
        bookList.sort(Comparator.comparing(Book::getTitle).reversed()
                .thenComparing(Comparator.comparingDouble(Book::getPrice).reversed()));
        return bookList;
    }

    /**
     * Sắp xếp book theo giá tăng dần.
     * @return
     */
    public List<Book> sortIncreasingPrice() {
        bookList.sort(Comparator.comparingDouble(Book::getPrice));
        return bookList;
    }

    /**
     * Sắp xếp book theo giá giảm dần.
     * @return
     */
    public List<Book> sortDecreasingPrice() {
        bookList.sort(Comparator.comparingDouble(Book::getPrice).reversed());
        return bookList;
    }

    /**
     * Sắp xếp book theo số trang tăng dần.
     * @return
     */
    public List<Book> sortIncreasingPages() {
        bookList.sort(Comparator.comparingInt(Book::getPages));
        return bookList;
    }

    /**
     * Sắp xếp book theo số trang giảm dần.
     * @return
     */
    public List<Book> sortDecreasingPages() {
        bookList.sort(Comparator.comparingInt(Book::getPages).reversed());
        return bookList;
    }

    /**
     * Lọc ra howMany book có giá lớn nhất.
     * @param howMany
     * @return
     */
    public List<Book> filterHighestPrice(int howMany) {
        return bookList.stream()
                .sorted(Comparator.comparingDouble(Book::getPrice).reversed())
                .limit(howMany)
                .toList();
    }

    /**
     * Lọc ra những book có giá cao hơn lowerBound.
     * @param lowerBound
     * @return
     */
    public List<Book> filterBooksPriceHigherThan(double lowerBound) {
        return bookList.stream()
                .filter(book -> book.getPrice() > lowerBound)
                .toList();
    }

    /**
     * Lọc ra howMany book có số trang nhỏ nhất.
     * @param howMany
     * @return
     */
    public List<Book> filterBookLowestPages(int howMany) {
        return bookList.stream()
                .sorted(Comparator.comparingInt(Book::getPages))
                .limit(howMany)
                .toList();
    }

    /**
     * Lọc ra howMany book có số trang nhỏ hơn upperBound.
     * @param upperBound
     * @return
     */
    public List<Book> filterBooksPagesLowerThan(double upperBound) {
        return bookList.stream()
                .filter(book -> book.getPages() < upperBound)
                .toList();
    }

    /**
     * Lọc ra những book theo nhà xuất bản.
     * @param publisher
     * @return
     */
    public List<Book> filterBooksOfPublisher(String publisher) {
        return bookList.stream()
                .filter(book -> book.getPublisher().equals(publisher))
                .toList();
    }

    /**
     * Lọc ra những book theo thể loại.
     * @param genre
     * @return
     */
    public List<Book> filterBooksOfGenre(String genre) {
        return bookList.stream()
                .filter(book -> book.getGenre().equals(genre))
                .toList();
    }

    /**
     * Lọc ra những book theo tác giả.
     * @param author
     * @return
     */
    public List<Book> filterBooksOfAuthor(String author) {
        return bookList.stream()
                .filter(book -> book.getAuthor().equals(author))
                .toList();
    }

    public static String titleOfBooksToString(List<Book> bookList) {
        StringBuilder titleOfBooks = new StringBuilder();
        titleOfBooks.append("[\n");
        for (Book book : bookList) {
            titleOfBooks.append(book.getTitle()).append("\n");
        }
        return titleOfBooks.toString().trim() + "\n]";
    }

    public static void print(List<Book> bookList) {
        StringBuilder booksString = new StringBuilder();
        booksString.append("[\n");
        for (Book book : bookList) {
            booksString.append(book.toString()).append("\n");
        }
        System.out.print(booksString.toString().trim() + "\n]");
    }
}
