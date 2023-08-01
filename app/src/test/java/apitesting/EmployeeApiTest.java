package apitesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

public class EmployeeApiTest {

    @Test
    public void testGetAllEmployees() throws ClientProtocolException, IOException, InterruptedException {
        EmployeeApi employeeApi = new EmployeeApi();
        HttpResponse response = employeeApi.getAllEmployees();

        if (response.getStatusLine().getStatusCode() == 429) {
            System.out.println("Aserción obtener todos los empleados: Too Many Requests");
            Thread.sleep(60000);
        } else {
            assertEquals(200, response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            assertNotNull(entity);
            String responseBody = EntityUtils.toString(entity);
            assertTrue(responseBody.contains("employee_name"));
            assertTrue(responseBody.contains("Successfully! All records has been fetched."));
        }

    }

    @Test
    public void testGetSingleEmployee() throws ClientProtocolException, IOException, InterruptedException {
        EmployeeApi employeeApi = new EmployeeApi();
        HttpResponse response = employeeApi.getSingleEmployee(1);

        if (response.getStatusLine().getStatusCode() == 429) {
            System.out.println("Aserción obtener empleado unico: Too Many Requests");
            Thread.sleep(60000);
        } else {
            assertEquals(200, response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            assertNotNull(entity);
            String responseBody = EntityUtils.toString(entity);
            assertTrue(responseBody.contains("Tiger Nixon"));
            assertTrue(responseBody.contains("Successfully! All records has been fetched."));
        }

        
    }

    @Test
    public void testCreateEmployee() throws ClientProtocolException, IOException, InterruptedException {
        EmployeeApi employeeApi = new EmployeeApi();
        String requestBody = "{\"employee_name\":\"Pedro Navaja\",\"employee_salary\":\"40000\",\"employee_age\":\"30\",\"profile_image\":\"\"}";
        HttpResponse response = employeeApi.createEmployee(requestBody);


        if (response.getStatusLine().getStatusCode() == 429) {
            System.out.println("Aserción crear empleado: Too Many Requests");
            Thread.sleep(60000);
        } else {
            assertEquals(200, response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            assertNotNull(entity);        
            String responseBody = EntityUtils.toString(entity);
            assertTrue(responseBody.contains("Successfully! Record has been added."));
        }
        
    }

    @Test
    public void testUpdateEmployee() throws ClientProtocolException, IOException, InterruptedException {
        EmployeeApi employeeApi = new EmployeeApi();
        String requestBody = "{\"employee_name\":\"Prueba\",\"employee_salary\":\"700000\",\"employee_age\":\"45\",\"profile_image\":\"\"}";
        HttpResponse response = employeeApi.updateEmployee(21, requestBody);
        
        
        if (response.getStatusLine().getStatusCode() == 429) {
            System.out.println("Aserción actualizar empleado: Too Many Requests");
            Thread.sleep(60000);
        } else {
            assertEquals(200, response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            assertNotNull(entity);
            String responseBody = EntityUtils.toString(entity);
            assertTrue(responseBody.contains("Successfully! Record has been updated."));
        }

        
    }

    @Test
    public void testDeleteEmployee() throws ClientProtocolException, IOException, InterruptedException {
        EmployeeApi employeeApi = new EmployeeApi();
        HttpResponse response = employeeApi.deleteEmployee(2);

        if ( response.getStatusLine().getStatusCode() == 429) {
            System.out.println("Aserción eliminar empleado: Too Many Requests");
            Thread.sleep(60000);
        } else {
            assertEquals(200, response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            assertNotNull(entity);
            String responseBody = EntityUtils.toString(entity);
            assertTrue(responseBody.contains("Successfully! Record has been deleted"));
        }
        
    }
}
