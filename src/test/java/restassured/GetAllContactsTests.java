package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactListDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsTests implements Helper {
    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void getAllContactsPositive() {

       given()
                .header(authHeader, token)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .assertThat().statusCode(200);
        }
        @Test
        public void getAllContactsUnauthorized () {

            given()
                    //    .header(authHeader, token)
                    .contentType(ContentType.JSON)
                    .when()
                    .get(endpoint)
                    .then()
                    .assertThat().statusCode(403);//status code is wrong 401

        }
    }


