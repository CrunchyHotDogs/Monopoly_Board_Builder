package boardObjects;

/**
 * A simple object used to hold the placeholder info about the cards.
 * @author Kyle
 */
public class Card {
    private String name;
    private String description;
    private String type;
    
    //Used for the HTML part. Tells the user what each card does.
    private String details;
    
    public Card() {
        this.name = "";
        this.description = "";
        this.type = "";
        this.details = "";
    }
    
    public Card(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.details = "";
    }

    public Card(String name, String description, String type, String details) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.details = details;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
