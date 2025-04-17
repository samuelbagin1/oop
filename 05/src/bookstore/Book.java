package bookstore;


import java.util.Objects;

public class Book implements Comparable<Book> {


    private String ISBN;
    private String title;
    private String author;
    private int year;

    /**
     * Calculate the checksum of the ISBN.
     * ISBN may be ISBN-10 or ISBN-13.
     * ISBN is represented as a string without hyphens.
     * If the input contains any hyphens, spaces, or other characters which are not digits or the character X,
     * the potentialISBN check returns false.
     * Only check the checksum, you may disregard any other information the ISBN contains (e.g. country code).
     * @param potentialISBN the string the check. It must only contain characters 0-9, X.
     * @return true if the potentialISBN is a valid ISBN, false otherwise
     */
    public static boolean isISBNValid(String potentialISBN) {
        if (potentialISBN == null) return false;

        if (potentialISBN.length()==10) {
            int checksum = 0;
            for (int i=0; i<potentialISBN.length(); i++) {

                if (potentialISBN.charAt(i)=='X' && i==9) {
                    checksum+=10;
                } else if (Character.isDigit(potentialISBN.charAt(i))) {
                    checksum+=Character.getNumericValue(potentialISBN.charAt(i))*(potentialISBN.length()-i);
                } else {
                    return false;
                }

            }

            return checksum%11==0;

        } else if (potentialISBN.length()==13) {
            int checksum = 0;
            for (int i=0; i<potentialISBN.length(); i++) {

                if (Character.isDigit(potentialISBN.charAt(i))) {

                    if (i%2==1) {
                        checksum+=Character.getNumericValue(potentialISBN.charAt(i))*3;
                    } else {
                        checksum+=Character.getNumericValue(potentialISBN.charAt(i));
                    }

                } else {
                    return false;
                }

            }

            return checksum%10==0;
        }

        return false;
    }

    /**
     * Initialize the book.
     * @param ISBN a valid ISBN
     * @param title a non-null, non-empty title of the book
     * @param author a non-null, non-empty full name of the author
     * @param year a non-negative year of publication
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public Book(String ISBN, String title, String author, int year) {
        if (title==null || title.isEmpty()) throw new IllegalArgumentException("Title must not be null or empty");
        if (author==null || author.isEmpty()) throw new IllegalArgumentException("Author must not be null or empty");
        if (year<0) throw new IllegalArgumentException("Year must not be negative");
        if (ISBN == null || !isISBNValid(ISBN)) throw new IllegalArgumentException("Invalid ISBN: " + ISBN);


        this.ISBN=ISBN;
        this.title=title;
        this.author=author;
        this.year=year;
    }


    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && Objects.equals(ISBN, book.ISBN) && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, title, author, year);
    }

    @Override
    public int compareTo(Book o) {
        // First compare by year
        int yearComparison = Integer.compare(this.year, o.year);
        if (yearComparison != 0) {
            return yearComparison;
        }
        // If years are equal, compare by ISBN lexicographically
        return this.ISBN.compareTo(o.ISBN);
    }
}