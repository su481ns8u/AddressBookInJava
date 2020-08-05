//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.json.simple.JSONObject;
//import org.junit.Assert;
//import org.junit.Test;
//
//public class AddressBookTest {
//    @Test
//    public void restAssuredPutTest() {
//        RequestSpecification requestSpecification = RestAssured.given();
//        requestSpecification.header("Content-Type", "application/json");
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("Mobile No.", "91 985564856");
//        jsonObject.put("Zip", "465231");
//        jsonObject.put("Address", "Harsh");
//        jsonObject.put("State", "Maha");
//        jsonObject.put("First Name", "");
//        jsonObject.put("City", "Sams");
//        jsonObject.put("Last Name", "Ghinm");
//
//        JSONObject personObject = new JSONObject();
//        personObject.put("Person", jsonObject);
//
//        requestSpecification.body(personObject.toJSONString());
//        Response response = requestSpecification.post("http://localhost:3000/Person");
//        int code = response.getStatusCode();
//        Assert.assertEquals(code, 201);
//    }
//
//    @Test
//    public void name() {
//        RequestSpecification requestSpecification = RestAssured.given();
//        Response response = requestSpecification.get("http://localhost:3000/Person");
//        System.out.println(response);
//    }
//}
