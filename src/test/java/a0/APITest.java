/**
 * @author Jasmine Chu (xiyinc)
 */
package a0;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class APITest {
    @BeforeAll
    static void setUp() {
        API.main(new String[0]);
    }

    @Test
    public void dai_returns_200_with_expected_current_date()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/current/date");

        int statusCode = response.statusCode();
        int responseDate = response.jsonPath().get("current.date");
        int currentDate = LocalDate.now().getDayOfMonth();

        assertEquals(200, statusCode, "Response status code 200");
        assertEquals(currentDate, responseDate, "Response body returns correct current date");
    }

    @Test
    public void dai_returns_404_with_invalid_current_date_path()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/date");

        int statusCode = response.statusCode();

        assertEquals(404, statusCode, "Response status code 404");
    }

    @Test
    public void dai_returns_200_with_expected_current_month()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/current/month");

        int statusCode = response.statusCode();
        int responseMonth = response.jsonPath().get("current.month");
        int currentMonth = LocalDate.now().getMonthValue();

        assertEquals(200, statusCode, "Response status code 200");
        assertEquals(currentMonth , responseMonth, "Response body returns correct current month");
    }

    @Test
    public void dai_returns_404_with_invalid_current_month_path()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/month");

        int statusCode = response.statusCode();

        assertEquals(404, statusCode, "Response status code 404");
    }

    @Test
    public void dai_returns_200_with_expected_current_year()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/current/year");

        int statusCode = response.statusCode();
        int responseYear = response.jsonPath().get("current.year");
        int currentYear = LocalDate.now().getYear();

        assertEquals(200, statusCode, "Response status code 200");
        assertEquals(currentYear , responseYear, "Response body returns correct current year");
    }

    @Test
    public void dai_returns_404_with_invalid_current_year_path()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/year");

        int statusCode = response.statusCode();

        assertEquals(404, statusCode, "Response status code 404");
    }

    @Test
    public void dai_returns_200_with_expected_all_events_given_date()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/allEvents/year/{yyyy}/month/{mm}/date/{dd}", 2022, 11, 5);

        int statusCode = response.statusCode();
        String responseBody = response.getBody().asString();

        assertEquals(200, statusCode, "Response status code 200");
        assertEquals("[{\"eventID\":\"1\",\"month\":11,\"date\":5,\"year\":2022,\"patient\":\"Jason123\",\"doctor\":\"M.D. Julie\",\"comment\":\"The patient got a Flu.\"},{\"eventID\":\"2\",\"month\":11,\"date\":5,\"year\":2022,\"patient\":\"LucyLee\",\"doctor\":\"M.D. Patrick\",\"comment\":\"The patient had a wisdom tooth extraction.\"}]", responseBody, "Return all events on that date");
    }

    @Test
    public void dai_returns_500_with_internal_server_error_all_events()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/allEvents/year/{yyyy}/month/{mm}/date/{dd}", 2022, 11, "s");

        int statusCode = response.statusCode();

        assertEquals(500, statusCode, "Response status code 500");
    }

    @Test
    public void dai_returns_200_post_new_event()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.post("/event?month={mm}&date={dd}&year={yyyy}&patient={p}&doctor{d}}&comment={c}",11,7,2022,"kelly","M.D. Andrew", "The patient got a headache.");

        int statusCode = response.statusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, 200, "Response status code 200");
        assertEquals(true, responseBody.contains("Added"));
    }

    @Test
    public void dai_returns_500_post_new_event_internal_server_error()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.post("/event?month={mm}&date={dd}&year={yyyy}&patient={p}&doctor{d}}&comment={c}",11,"Jul",2022,"kelly","M.D. Andrew", "The patient got a headache.");

        int statusCode = response.statusCode();

        assertEquals(500, statusCode, "Response status code 500");
    }

    @Test
    public void dai_returns_200_revise_event()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.put("/event/{id}?month={mm}&date={dd}&year={yyyy}&patient={p}&doctor{d}&comment={c}",3,12,8,2022,"p","d","c");

        int statusCode = response.statusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, 200, "Response status code 200");
        assertEquals(true, responseBody.contains("Revised"));
    }

    @Test
    public void dai_returns_500_revise_event_internal_server_error()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.put("/event/{id}?date={dd}",1000,8);

        int statusCode = response.statusCode();

        assertEquals(500, statusCode, "Response status code 500");
    }

    @Test
    public void dai_returns_200_delete_event()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.delete("/event/{id}",3);

        int statusCode = response.statusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, 200, "Response status code 200");
        assertEquals(true, responseBody.contains("Deleted"));
    }

    @Test
    public void dai_returns_500_delete_event_internal_server_error()
    {
        RestAssured.baseURI = "http://localhost:4567/DAI";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.put("/{id}",3);

        int statusCode = response.statusCode();

        assertEquals(404, statusCode, "Response status code 404");
    }
}