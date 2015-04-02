/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.sql.ResultSet;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kyle
 */
public class JsonParserTest {
    
    public JsonParserTest() {
    }
    
    //Creates a json object that is used for testing.
    private JsonObject createJsonObject(int numberOfProperties, int numberOfChance, int numberOfCommunityChest, int numberOfBoardInfo) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        JsonObjectBuilder singleJson = Json.createObjectBuilder();
        JsonObjectBuilder fullJson = Json.createObjectBuilder();
        
        //Loops for the required amount of properties.
        for (int i = 0; i < numberOfProperties; i++) {
            singleJson.add("name", "testName")
                    .add("tax", "testTax")
                    .add("house", "testHouse")
                    .add("cost", "testCost")
                    .add("type", "testType");
            jsonArray.add(singleJson);
        }
        fullJson.add("property", jsonArray);
        
        //Chance cards.
        for (int i = 0; i < numberOfChance; i++) {
            singleJson.add("name", "testName")
                    .add("description", "testDesc")
                    .add("type", "testType");
            jsonArray.add(singleJson);
        }
        fullJson.add("chanceCard", jsonArray);
        
        //Community chest cards.
        for (int i = 0; i < numberOfCommunityChest; i++) {
            singleJson.add("name", "testName")
                    .add("description", "testDesc")
                    .add("type", "testType");
            jsonArray.add(singleJson);
        }
        fullJson.add("communityChest", jsonArray);
        
        //Board info.
        for (int i = 0; i < numberOfBoardInfo; i++) {
            singleJson.add("name", "testName")
                    .add("url", "testUrl");
            jsonArray.add(singleJson);
        }
        fullJson.add("board", jsonArray);
        
        return fullJson.build();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void checkWithValidJsonReturnTrue() {
        JsonObject json = createJsonObject(40, 16, 17, 1);
        boolean expResult = true;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingPropertyReturnFalse() {
        JsonObject json = createJsonObject(0, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingChanceReturnFalse() {
        JsonObject json = createJsonObject(40, 0, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingCommunityChestReturnFalse() {
        JsonObject json = createJsonObject(40, 16, 0, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingBoardInfoReturnFalse() {
        JsonObject json = createJsonObject(40, 16, 17, 0);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyPropertyReturnFalse() {
        JsonObject json = createJsonObject(41, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyChanceCardsReturnFalse() {
        JsonObject json = createJsonObject(40, 17, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyCommunityChestCardsReturnFalse() {
        JsonObject json = createJsonObject(40, 16, 18, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyBoardInfoReturnFalse() {
        JsonObject json = createJsonObject(40, 16, 17, 2);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
}
