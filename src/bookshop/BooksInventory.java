
package bookshop;

/**
 * This BooksInventory class
 * @author HP
 */
public class BooksInventory {
    
    
    private String bookname,author;
    private int quantity;
    private double price;
    
    /**
     * This is default constructor
     */
    public BooksInventory() {
    }

    /**
     * This argument Constructor
     * @param bookname
     * @param author
     * @param quantity
     * @param price 
     */
    public BooksInventory(String bookname, String author, int quantity, double price) {
        this.bookname = bookname;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }
    
    /**
     * This is getter method Book Name
     * @return String
     */
    public String getBookname() {
        return bookname;
    }
    
    /**
     * This getter method for author
     * @return String
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * This getter  method for quantity
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * This is getter method for price
     * @return double
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * This is setter method for book name
     * @param bookname 
     */
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    
    /**
     * This setter method for author
     * @param author 
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * This setter method for quantity
     * @param quantity 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * This setter method for price
     * @param price 
     */
    public void setPrice(double price) {
        this.price = price;
    }   
}
