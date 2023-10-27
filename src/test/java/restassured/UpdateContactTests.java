package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactTests implements Helper {

    ContactDTO contactDTO;
    String id;
    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";

            contactDTO = ContactDTO.builder()
                    .name("nana")
                    .lastName("Kiki")
                    .email("kiki" + i + "@mail.com")
                    .phone("12345687" + i)
                    .address("Tel")
                    .description("pupil")
                    .build();

         String message = given()
                    .header(authHeader, token)
                    .body(contactDTO)
                    .contentType(ContentType.JSON)
                    .when()
                    .post(endpoint)
                    .then()
                    .extract()
                    .path("message");
            id = message.substring(message.lastIndexOf(" ") + 1);
        }
        @Test
    public void UpdateContactPositive(){
        contactDTO.setId(id);
        contactDTO.setName("nana_updated");

            given()
                    .header(authHeader, token)
                    .body(contactDTO)
                    .contentType(ContentType.JSON)
                    .when()
                    .put(endpoint)
                    .then()
                    .assertThat().statusCode(200)
            .assertThat().body("message", containsString("Contact was updated"));
    }
    @Test
    public void UpdateContactNegative(){
        contactDTO.setId(id);
        contactDTO.setName(" ");

            given()
                    .header(authHeader, token)
                    .body(contactDTO)
                    .contentType(ContentType.JSON)
                    .when()
                    .put(endpoint)
                    .then()
                    .assertThat().statusCode(400);

    }
    @Test
    public void UpdateContactUnauthorized(){
        contactDTO.setId(id);
        contactDTO.setName("nana_updated");

            given()
             //       .header(authHeader, token)
                    .body(contactDTO)
                    .contentType(ContentType.JSON)
                    .when()
                    .put(endpoint)
                    .then()
                    .assertThat().statusCode(403); //status code is wrong

    }

}