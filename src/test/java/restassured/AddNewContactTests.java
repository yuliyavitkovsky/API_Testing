package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class AddNewContactTests implements Helper {

    String endpoint = "/v1/contacts";
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void addNewContactPositive(){
        ContactDTO contactDTO = ContactDTO.builder()
                .name("nana")
                .lastName("Kiki")
                .email("kiki" + i + "@mail.com")
                .phone("12345687" + i)
                .address("Tel")
                .description("pupil")
                .build();

        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
        .assertThat().body("message", containsString("Contact was added!"));
    }
    @Test
    public void addNewContactNegative(){
        ContactDTO contactDTO = ContactDTO.builder()
                .name("nana")
                .lastName("Kiki")
                .email("kiki" + i + "mail.com")
                .phone("12345687" + i)
                .address("Tel")
                .description("pupil")
                .build();

        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400);
    }
    @Test
    public void addNewContactUnauthorized(){
        ContactDTO contactDTO = ContactDTO.builder()
                .name("nana")
                .lastName("Kiki")
                .email("kiki" + i + "mail.com")
                .phone("12345687" + i)
                .address("Tel")
                .description("pupil")
                .build();

        given()
             //   .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(403); //status code is wrong
    }
//    @Test
//    public void addNewContactDuplicate(){
//        ContactDTO contactDTO = ContactDTO.builder()
//                .name("Misha")
//                .lastName("Ivanov")
//                .email("mw@mail.com")
//                .phone("5356523213")
//                .address("Tel Aviv")
//                .description("Boyfriend")
//                .build();
//
//        given()
//                .header(authHeader, token)
//                .body(contactDTO)
//                .contentType(ContentType.JSON)
//                .when()
//                .post(endpoint)
//                .then()
//                .assertThat().statusCode(409); bug
//             }

}
