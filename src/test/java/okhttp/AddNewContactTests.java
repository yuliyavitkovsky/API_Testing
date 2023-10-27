package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTests implements Helper {
    String endpoint = "/v1/contacts";

    @Test
    public void addNewContactPositive() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("nana")
                .lastName("Kiki")
                .email("kiki" + i + "@mail.com")
                .phone("12345687" + i)
                .address("Tel")
                .description("pupil")
                .build();

        RequestBody requestBody =
                RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ContactResponseDTO contactsResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        String message = contactsResponseDTO.getMessage();
        System.out.println(message);

        String id = message.substring(message.lastIndexOf(" ") + 1);
        System.out.println(id);

    }


}
