import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AddressBookTest {
    @Test
    public void givenIDAndURLOfApi_UsingRestAssured_ShouldReturnPassingStatusCode() {
        int statusCode = given().queryParam("id", 1)
                .when()
                .get("https://my-json-server.typicode.com/su481ns8u/AddressBookSampleDB/person")
                .getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void givenJSonStringToPost_UsingRestAssured_ShouldReturnPassingResponseStatus() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"FirstName\": \"Shashank\", \"LastName\": \"Ghinmine\", \"Mobile\": \"91 9822917991\", " +
                        "\"Address\": \"Kesha\", \"City\": \"Selu\",\"State\": \"Maha\",\"Zip\": \"465231\"}")
                .when()
                .post("https://my-json-server.typicode.com/su481ns8u/AddressBookSampleDB/person").then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void givenJsonStringToUpdate_UsingRestAssured_ShouldReturnPassingStatus() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"FirstName\": \"Shashank\", \"LastName\": \"Ghinmine\", \"Mobile\": \"91 9822917991\", " +
                        "\"Address\": \"Kesha\", \"City\": \"Selu\",\"State\": \"Maha\",\"Zip\": \"465231\"}")
                .when()
                .put("https://my-json-server.typicode.com/su481ns8u/AddressBookSampleDB/person/1").then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void givenURLToDelete_UsingRestAssured_ShouldReturnPassingStatus() {
        when()
                .delete("https://my-json-server.typicode.com/su481ns8u/AddressBookSampleDB/person/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}
