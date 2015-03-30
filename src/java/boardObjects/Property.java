package boardObjects;

/**
 * A simple object used to hold the placeholder info about each property.
 * @author Kyle
 */
public class Property {
    private String name;
    private int[] tax;
    private int house;
    private int cost;
    private String type;
    
    //Used for the HTML Canvas.
    private int x, y, width, height;
    
    public Property() {
        this.name = "";
        this.tax = new int[]{0};
        this.house = 0;
        this.cost = 0;
        this.type = "";
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }
    
    public Property(String name, int[] tax, int cost, int house, String type) {
        this.name = name;
        this.tax = tax;
        this.house = house;
        this.cost = cost;
        this.type = type;
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public Property(String name, int[] tax, int cost, int house, String type, int x, int y, int width, int height) {
        this.name = name;
        this.tax = tax;
        this.house = house;
        this.cost = cost;
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getTax() {
        return tax;
    }

    public void setTax(int[] tax) {
        this.tax = tax;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
    public String getTaxString() {
        String taxString = "";
        
        for (int i = 0; i < this.tax.length; i++) {
            taxString += this.tax[i];
            if (i != this.tax.length - 1) {
                taxString += ",";
            }
        }
        return taxString;
    }
}
