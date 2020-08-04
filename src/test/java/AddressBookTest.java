//import org.hamcrest.Matchers;
//import org.junit.Before;
//import org.junit.Test;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//
//public class AddressBookTest {
//    @Before
//    public void setUp() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 4000;
//    }
//
//    public Response getPersonList() {
//        Response response = RestAssured.get("/person/list");
//        return response;
//    }
//
//    @Test
//    public void onCallingList_ReturnPersonList() {
//        Response response = getPersonList();
//        System.out.println("AT FIRST: " + response.asString());
//        response.then().body("id", Matchers.hasItems(1, 2));
//        response.then().body("firstName", Matchers.hasItems("Amrutnandan"));
//    }
//}
