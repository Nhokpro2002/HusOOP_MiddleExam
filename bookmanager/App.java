package hus.oop.bookmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String COMMA_DELIMITER = ",";
    private static final BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        init();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output3.txt"))) {
            testOriginalData(writer);
            testSortIncreasingTitleAndPrice(writer);
            testSortDecreasingTitleAndPrice(writer);
            testPriceOfBooksIncreasing(writer);
            testPriceOfBooksDecreasing(writer);
            testFilterBooksLowestPrice(writer);
            testFilterBooksHighestPrice(writer);
            testFilterBooksOfAuthor(writer);
            testFilterBooksOfPublisher(writer);
            testFilterBooksOfGenre(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        String filePath = "C:\\Users\\Admin\\Desktop\\data\\books.csv";
        readListData(filePath);
    }

    public static void readListData(String filePath) {
        BufferedReader dataReader = null;
        try {
            String line;
            dataReader = new BufferedReader(new FileReader(filePath));

            while ((line = dataReader.readLine()) != null) {
                List<String> dataList = parseDataLineToList(line);
                if (dataList.size() != 6) {
                    continue;
                }

                if (dataList.get(0).equals("title")) { // Skip header
                    continue;
                }

                // Extract data
                String title = dataList.get(0);
                String author = dataList.get(1);
                String genre = dataList.get(2);
                int pages = Integer.parseInt(dataList.get(3));
                double price = Double.parseDouble(dataList.get(4));
                //int pages = Integer.parseInt(dataList.get(4));
                String publisher = dataList.get(5);

                // Use BookBuilder to create Book object
                Book newBook = new Book.BookBuilder(title)
                        .withAuthor(author)
                        .withGenre(genre)
                        .withPrice(price)
                        .withPages(pages)
                        .withPublisher(publisher)
                        .build();

                // Add book to bookManager
                bookManager.insertAtEnd(newBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataReader != null)
                    dataReader.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
    }

    public static List<String> parseDataLineToList(String dataLine) {
        List<String> result = new ArrayList<>();
        if (dataLine != null) {
            String[] splitData = dataLine.split(COMMA_DELIMITER);
            for (String data : splitData) {
                result.add(data);
            }
        }
        return result;
    }

    public static String[] parseDataLineToArray(String dataLine) {
        if (dataLine == null) {
            return null;
        }

        return dataLine.split(COMMA_DELIMITER);
    }

    public static void testOriginalData(BufferedWriter writer) throws IOException {
        List<Book> books = bookManager.getBookList();
        writer.write("Original Data:\n");
        writer.write(books.toString() + "\n");
    }

    public static void testSortIncreasingTitleAndPrice(BufferedWriter writer) throws IOException {
        List<Book> sortedBooks = bookManager.sortIncreasingTitleAndPrice();
        writer.write("Sorted by title increasing and price decreasing:\n");
        writer.write(sortedBooks.toString() + "\n");
    }

    public static void testSortDecreasingTitleAndPrice(BufferedWriter writer) throws IOException {
        List<Book> sortedBooks = bookManager.sortDecreasingTitleAndPrice();
        writer.write("Sorted by title decreasing and price decreasing:\n");
        writer.write(sortedBooks.toString() + "\n");
    }

    public static void testPriceOfBooksIncreasing(BufferedWriter writer) throws IOException {
        List<Book> sortedBooks = bookManager.sortIncreasingPrice();
        writer.write("Sorted by price increasing:\n");
        writer.write(sortedBooks.toString() + "\n");
    }

    public static void testPriceOfBooksDecreasing(BufferedWriter writer) throws IOException {
        List<Book> sortedBooks = bookManager.sortDecreasingPrice();
        writer.write("Sorted by price decreasing:\n");
        writer.write(sortedBooks.toString() + "\n");
    }

    public static void testFilterBooksLowestPrice(BufferedWriter writer) throws IOException {
        List<Book> filteredBooks = bookManager.filterHighestPrice(1);  // Get one book with the highest price
        writer.write("Filtered by lowest price:\n");
        writer.write(filteredBooks.toString() + "\n");
    }

    public static void testFilterBooksHighestPrice(BufferedWriter writer) throws IOException {
        List<Book> filteredBooks = bookManager.filterHighestPrice(1);  // Get one book with the highest price
        writer.write("Filtered by highest price:\n");
        writer.write(filteredBooks.toString() + "\n");
    }

    public static void testFilterBooksOfAuthor(BufferedWriter writer) throws IOException {
        List<Book> filteredBooks = bookManager.filterBooksOfAuthor("Some Author");
        writer.write("Filtered by author:\n");
        writer.write(filteredBooks.toString() + "\n");
    }

    public static void testFilterBooksOfPublisher(BufferedWriter writer) throws IOException {
        List<Book> filteredBooks = bookManager.filterBooksOfPublisher("Some Publisher");
        writer.write("Filtered by publisher:\n");
        writer.write(filteredBooks.toString() + "\n");
    }

    public static void testFilterBooksOfGenre(BufferedWriter writer) throws IOException {
        List<Book> filteredBooks = bookManager.filterBooksOfGenre("Fiction");
        writer.write("Filtered by genre:\n");
        writer.write(filteredBooks.toString() + "\n");
    }
}
