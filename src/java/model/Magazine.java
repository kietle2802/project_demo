package model;

/**
 *
 * @author MrEnd
 */
public class Magazine {
    String ID, title, publisher;
    double price;

    public Magazine() {
    }

    public Magazine(String ID, String title, String publisher, double price) {
        this.ID = ID;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
