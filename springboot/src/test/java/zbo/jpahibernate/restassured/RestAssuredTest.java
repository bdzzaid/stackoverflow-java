package zbo.jpahibernate.restassured;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class RestAssuredTest
{
    // https://stackoverflow.com/questions/63469732/code-throws-invalid-json-expression-but-online-evaluator-shows-correct-output
    @Test
    public void restPost2()
    {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
        RequestSpecification req = RestAssured.given().log().all();
        String res1 = req.given().when().get().then().extract().asString();
        JsonPath js = new JsonPath(res1);
        System.out.println("specific title : " + js.get(".[{userId > 0}].title"));
    }

    @Test
    public void listTest()
    {
        String[] a_list = {"bob", "kate", "jaguar", "mazda", "honda", "civic", "grasshopper"};
        Arrays.sort(a_list, Comparator.comparing(s -> s.substring(1)));
        System.out.println(Arrays.toString(a_list));
    }
}
