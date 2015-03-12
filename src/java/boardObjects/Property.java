package boardObjects;

/**
 *
 * @author Kyle
 */
public class Property {
    private String name;
    private int[] tax;
    private int house;
    private int cost;
    private String type;
    
    public Property() {
        this.name = "";
        this.tax = new int[]{0};
        this.house = 0;
        this.cost = 0;
        this.type = "";
    }
    
    public Property(String name, int[] tax, int house, int cost, String type) {
        this.name = name;
        this.tax = tax;
        this.house = house;
        this.cost = cost;
        this.type = type;
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
