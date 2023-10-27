package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactByIDTests implements Helper {
    ContactDTO contactDTO;
    String endpoint = "/v1/contacts";
    ContactResponseDTO contactsResponseDTO;
    String id;
    @BeforeMethod
    public void precondition(){



        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";

        ContactDTO contactDTO = ContactDTO.builder()
                .name("nana")
                .lastName("Kiki")
                .email("kiki" + i + "@mail.com")
                .phone("12345687" + i)
                .address("Tel")
                .description("pupil")
                .build();

      ContactResponseDTO response =  given()

                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);

        String message = response.getMessage();
        id = message.substring(message.lastIndexOf(" ") + 1);
        System.out.println(id);
    }
    @Test
    public void deleteContactByIDPositive (){

given()
        .header(authHeader, token)
        .body(id)
        .contentType(ContentType.JSON)
        .when()
        .delete(endpoint + "/" + id)
        .then()
        .assertThat().statusCode(200)
        .assertThat().body("message", containsString("Contact was deleted!"));

    }
    @Test
    public void deleteContactByIDNegative (){

given()
        .header(authHeader, token)
        .body(id)
        .contentType(ContentType.JSON)
        .when()
        .delete(endpoint +"/"+ id + "1")
        .then()
        .assertThat().statusCode(400);

    }
    @Test
    public void deleteContactByIDUnauthorized (){

given()
       // .header(authHeader, token)
        .body(id)
        .contentType(ContentType.JSON)
        .when()
        .delete(endpoint +"/"+ id)
        .then()
        .assertThat().statusCode(403); //status code is wrong

    }
    @Test
    public void deleteContactByIDNotFound (){

        given()
                .header(authHeader, token)
                .body(id)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint +"/"+ id + id)
                .then()
                .assertThat().statusCode(400);

    }
}