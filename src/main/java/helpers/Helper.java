package helpers;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYnViYUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjk4NjcyMTM0LCJpYXQiOjE2OTgwNzIxMzR9.sncsfrCY517NjcMBDjoHKnquHRKOLHUCpP5sy_BgV2c";
    public  static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    Gson gson = new Gson();

    OkHttpClient client = new OkHttpClient();

    String baseURL = "https://contactapp-telran-backend.herokuapp.com";

String authHeader = "Authorization";

    int i = new Random().nextInt(1000) + 1000;

}
