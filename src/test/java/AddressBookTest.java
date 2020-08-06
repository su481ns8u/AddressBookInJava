import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class AddressBookTest {
    @Before
    public void setUp() throws Exception {
        baseURI = "http://localhost:3000/person/";
    }

    @Test
    public void givenIDAndURLOfApi_UsingRestAssured_ShouldReturnPassingStatusCode() {
//        given().queryParam("firstName", "S")
//                .get("/person/?firstName="+"S")
//                .then()
//                .statusCode(200)
//                .log().all();

        Response response = (Response) given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("")
                .then()
                .extract()
                .body();
        ArrayList<Object> list = (ArrayList<Object>) given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().get().then().extract().response().jsonPath().getList("$");
        int size = response.jsonPath().getList("id").size();
        list.forEach(a -> System.out.println(a.toString()));
        System.out.println(list);
    }

    @Test
    public void givenJSonStringToPost_UsingRestAssured_ShouldReturnPassingResponseStatus() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "      \"firstName\": \"Shashank\"," +
                        "      \"lastName\": \"Ghinmine\"," +
                        "      \"address\": \"Murtiza\"," +
                        "      \"city\": \"Amrawati\"," +
                        "      \"state\": \"Maha\"," +
                        "      \"zip\": \"431654\"," +
                        "      \"phoneNumber\": \"91 9822917991\"" +
                        "    }")
                .when()
                .post("/person")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void givenJsonStringToUpdate_UsingRestAssured_ShouldReturnPassingStatus() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "      \"firstName\": \"Snehal\"," +
                        "      \"lastName\": \"Ghinmine\"," +
                        "      \"address\": \"Nashirabad\"," +
                        "      \"city\": \"Jalgaon\"," +
                        "      \"state\": \"Maha\"," +
                        "      \"zip\": \"431798\"," +
                        "      \"phoneNumber\": \"91 8007928757\"" +
                        "    }")
                .when()
                .put("/person/3")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void givenURLToDelete_UsingRestAssured_ShouldReturnPassingStatus() {
        when()
                .delete("/person/3")
                .then()
                .statusCode(200)
                .log().all();
    }
}
