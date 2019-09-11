package cucumber.stepdefs.example.addmovie;

import java.io.FileReader;
import org.springframework.boot.web.server.LocalServerPort;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.stepdefs.StepDefsContext;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

public class AddMovieStepDefs {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:";

    StepDefsContext context = StepDefsContext.CONTEXT;

    @When("^User request to add a movie in video store$")
    public void user_request_to_add_a_movie_in_video_store() throws Exception {
        String url = baseUrl + port + "/api/v1/movie";

        JSONObject jsonRequest = (JSONObject) new JSONParser()
                .parse(new FileReader("src/test/java/cucumber/stepdefs/example/addmovie/request.to.addmovie.json"));

        Response response = given().log().all().contentType("application/json").body(jsonRequest.toString()).when()
                .post(url).andReturn();

        response.then().log().all();
        context.response(response);
    }

    @Then("^the movie should be added$")
    public void the_movie_should_be_added() throws Exception {
        //ObjectMapper mapper = new ObjectMapper();
        Response response = context.response();
        String actualResponse = response.body().asString();
        assertEquals(response.getStatusCode(), 200);

        JSONObject expectedResponse = (JSONObject) new JSONParser()
                .parse(new FileReader("src/test/java/cucumber/stepdefs/example/addmovie/response.from.addmovie.json"));
        // assertEquals(mapper.readTree(expectedResponse.toString()),
        // mapper.readTree(actualResponse));
        assertThatJson(expectedResponse.toString()).whenIgnoringPaths("id").isEqualTo(actualResponse);
    }   
}