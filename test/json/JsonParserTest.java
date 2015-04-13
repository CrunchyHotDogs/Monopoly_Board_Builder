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
    private JsonObject createJsonObject(String[][] fieldNames, int numberOfProperties, int numberOfChance, int numberOfCommunityChest, int numberOfBoardInfo) {
        JsonArrayBuilder jArrayProp = Json.createArrayBuilder();
        JsonArrayBuilder jArrayChance = Json.createArrayBuilder();
        JsonArrayBuilder jArrayChest = Json.createArrayBuilder();
        JsonArrayBuilder jArrayBoard = Json.createArrayBuilder();
        JsonObjectBuilder fullJson = Json.createObjectBuilder();
        
        //Loops for the required amount of properties.
        for (int i = 0; i < numberOfProperties; i++) {
            JsonObjectBuilder singleJson = Json.createObjectBuilder();
            singleJson.add(fieldNames[0][0], "testName")
                    .add(fieldNames[0][1], "testTax")
                    .add(fieldNames[0][2], "testHouse")
                    .add(fieldNames[0][3], "testCost")
                    .add(fieldNames[0][4], "testType");
            jArrayProp.add(singleJson);
        }
        fullJson.add("property", jArrayProp);
        
        //Chance cards.
        for (int i = 0; i < numberOfChance; i++) {
            JsonObjectBuilder singleJson = Json.createObjectBuilder();
            singleJson.add(fieldNames[1][0], "testName")
                    .add(fieldNames[1][1], "testDesc")
                    .add(fieldNames[1][2], "testType");
            jArrayChance.add(singleJson);
        }
        fullJson.add("chanceCard", jArrayChance);
        
        //Community chest cards.
        for (int i = 0; i < numberOfCommunityChest; i++) {
            JsonObjectBuilder singleJson = Json.createObjectBuilder();
            singleJson.add(fieldNames[2][0], "testName")
                    .add(fieldNames[2][1], "testDesc")
                    .add(fieldNames[2][2], "testType");
            jArrayChest.add(singleJson);
        }
        fullJson.add("communityChest", jArrayChest);
        
        //Board info.
        for (int i = 0; i < numberOfBoardInfo; i++) {
            JsonObjectBuilder singleJson = Json.createObjectBuilder();
            singleJson.add(fieldNames[3][0], "testName")
                    .add(fieldNames[3][1], "testUrl");
            jArrayBoard.add(singleJson);
        }
        fullJson.add("board", jArrayBoard);
        
        return fullJson.build();
    }
    
    private String[][] createFieldNames() {
        String[][] fieldNames = {   {"name", "tax", "house", "cost", "type"},
                                    {"name", "description", "type"}, 
                                    {"name", "description", "type"}, 
                                    {"name", "url"}};
        
        return fieldNames;
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
        JsonObject json = createJsonObject(createFieldNames(), 40, 16, 17, 1);
        boolean expResult = true;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingPropertyReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 0, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingChanceReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 40, 0, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingCommunityChestReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 40, 16, 0, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonMissingBoardInfoReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 40, 16, 17, 0);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyPropertyReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 41, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyChanceCardsReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 40, 17, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyCommunityChestCardsReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 40, 16, 18, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonTooManyBoardInfoReturnFalse() {
        JsonObject json = createJsonObject(createFieldNames(), 40, 16, 17, 2);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongPropertyFieldNameReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[0][0] = "notName";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongPropertyFieldTaxReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[0][1] = "notTax";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongPropertyFieldHouseReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[0][2] = "notHouse";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongPropertyFieldCostReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[0][3] = "notCost";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongPropertyFieldTypeReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[0][4] = "notType";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongChanceFieldNameReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[1][0] = "notName";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongChanceFieldDescReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[1][1] = "notDesc";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongChanceFieldTypeReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[1][2] = "notType";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongChestFieldNameReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[2][0] = "notName";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongChestFieldDescReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[2][1] = "notDesc";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongChestFieldTypeReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[2][2] = "notType";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongBoardInfoFieldNameReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[3][0] = "notName";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
    
    @Test
    public void checkWithInvalidJsonWrongBoardInfoFieldUrlReturnFalse() {
        String[][] fieldNames = createFieldNames();
        fieldNames[3][1] = "notUrl";
        
        JsonObject json = createJsonObject(fieldNames, 40, 16, 17, 1);
        boolean expResult = false;
        boolean result = JsonParser.validJsonCheck(json);
        assertEquals(expResult, result);
    }
}
