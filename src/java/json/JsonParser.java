package json;

import boardObjects.Card;
import boardObjects.Property;

/**
 *
 * @author Kyle
 */
public class JsonParser {
    public static Property parseProperty(String jsonString) {
        return new Property();
    }
    
    public static Card parseCard(String jsonString) {
        return new Card();
    }
}
