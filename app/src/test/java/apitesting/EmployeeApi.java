package apitesting;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class EmployeeApi {

    private final String BASE_URL = "https://dummy.restapiexample.com/api/v1";

    public HttpResponse getAllEmployees() throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/employee");
        return httpClient.execute(httpGet);
    }

    public HttpResponse getSingleEmployee(int id) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/employee/" + id);
        return httpClient.execute(httpGet);
    }

    public HttpResponse createEmployee(String requestBody) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL + "/create");

        StringEntity requestEntity = new StringEntity(requestBody);
        httpPost.setEntity(requestEntity);

        httpPost.setHeader("Content-type", "application/json");

        return httpClient.execute(httpPost);
    }

    public HttpResponse updateEmployee(int id, String requestBody) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(BASE_URL + "/update/" + id);

        StringEntity requestEntity = new StringEntity(requestBody);
        httpPut.setEntity(requestEntity);

        httpPut.setHeader("Content-type", "application/json");

        return httpClient.execute(httpPut);
    }

    public HttpResponse deleteEmployee(int id) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(BASE_URL + "/delete/" + id);
        return httpClient.execute(httpDelete);
    }
}
