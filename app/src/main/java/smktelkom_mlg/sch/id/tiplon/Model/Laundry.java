package smktelkom_mlg.sch.id.tiplon.Model;


public class Laundry {
    private String Name, Image, Price, Description, Discount, LaundryId;

    public Laundry() {
    }

    public Laundry(String name, String image, String price, String description, String discount, String laundryId) {
        Name = name;
        Image = image;
        Price = price;
        Description = description;
        Discount = discount;
        LaundryId = laundryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getLaundryId() {
        return LaundryId;
    }

    public void setLaundryId(String laundryId) {
        LaundryId = laundryId;
    }
}
