import dataProvider.ConfigFileReader;
import dataProvider.FileReafer;
import dataProvider.general;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.hamcrest.Matcher.*;
import static org.junit.Assert.assertTrue;


public class MyStepdefs {
    int responsecode;
String method;
String Url;
String Query;
String endurl;
String validation;
String Testdata;
StringBuilder endpoint;
String arg3;
String ip_legacy;
String endurl_legacy;
String validation_legacy;
int responsecode_legacy;

    ConfigFileReader configFileReader= new ConfigFileReader();
String ip ;
    FileReafer readfromExcel = new FileReafer();
general urlconstructor = new general();

    @Given("I want to execute service of method {string}")
    public void i_want_to_execute_service_of_method(String method1) {
method=method1;
System.out.println(method);
        System.out.println("the ip is "+ip);

    }


    @When("the relative url is {string}")
    public void the_relative_url_is(String url) {
        Url=url;
        System.out.println(Url);

    }


    @When("the query parameter is passed {string}")
    public void the_query_parameter_is_passed(String query) {
        if (query.equals("null")) {
            endurl = ip + Url;
            endurl_legacy = ip_legacy + Url;
            System.out.println("end urlis " + endurl);
            System.out.println("end urlis " + endurl_legacy);
        }
        else
        {

            Query = query;
            endurl = ip + endpoint + "?" + Query;
            endurl_legacy = ip_legacy + endpoint + "?" + Query;
            System.out.println("end urlis " + endurl);
            System.out.println("end urlis " + endurl_legacy);
        }

    }
    @When("the relative url is {string} and {string}")
    public void theRelativeUrlIsAnd(String arg0, String arg1) {
        int row =Integer.parseInt(arg0);
        int col =Integer.parseInt(arg1);
        Url=readfromExcel.fetchvalue(row,col);
        System.out.println(Url);
    }
    @And("the given test data is in  {string} and {string}")
    public void theGivenTestDataIsInAnd(String arg0, String arg1) {
        int row =Integer.parseInt(arg0);
        int col =Integer.parseInt(arg1);
        Testdata=readfromExcel.fetchvalue(row,col);
        System.out.println("Test data" +Testdata);
        if(Url.contains("{")) {
            endpoint = urlconstructor.constructURL(Url, Testdata);
            System.out.println("endpoint" + endpoint);
        }

    }

    @Then("execute the URL")
    public void execute_the_url() {
        if(method.equalsIgnoreCase("get"))
        {
            Response response = RestAssured.get(endurl);
            System.out.println(response.getStatusCode());
            responsecode = response.getStatusCode();
            System.out.println(response.asString());
            validation= response.getBody().asString();
            System.out.println("body"+validation);

            Response response2 = RestAssured.get(endurl_legacy);
            System.out.println(response2.getStatusCode());
            responsecode_legacy = response2.getStatusCode();
            System.out.println(response2.asString());
            validation_legacy= response2.getBody().asString();
            System.out.println("body"+validation);

        }

        else if(method.equalsIgnoreCase("post")) {
            Response response = RestAssured.given().contentType("application/json").header("apigw-authenticated-client", "smoke").body(Testdata).post(endurl);
            System.out.println("statuscodeid" + response.getStatusCode());
            responsecode = response.getStatusCode();
            validation = response.getBody().asString();
            validation=validation + "hello";
            System.out.println("final response" + response.asString());

            Response response2 = RestAssured.given().contentType("application/json").header("apigw-authenticated-client", "smoke").body(Testdata).post(endurl_legacy);
            System.out.println("statuscodeid" + response.getStatusCode());
            responsecode_legacy = response2.getStatusCode();
            validation_legacy = response2.getBody().asString();
            System.out.println("final response" + response.asString());
            if (Objects.equals(validation, validation_legacy)) {
                System.out.println("matchimg");
            } else {
                System.out.println("mot matching ");
            }
        }
        else
        {
            System.out.println("give proper method");
        }

    }
    @Then("I validate status code {string}")
    public void iValidateStatusCode( String status) {
        int i=Integer.parseInt(status);
        assertEquals(responsecode,i);
    }

    @And("I validate status code {string} and validate msisdn {string}")
    public void iValidateStatusCodeAndValidateMsisdn(String status, String arg1) {
String a=arg1;

        int i=Integer.parseInt(status);
        assertEquals(responsecode,i);
        assertTrue(validation.contains(a));


    }


    @Given("I want to execute service of method {string} and in environment {string}")
    public void iWantToExecuteServiceOfMethodAndInEnvironment(String arg0, String arg1) {
        method=arg0;
        arg3= arg1+"_legacy";
        ip=configFileReader.getenvironmentUrl(arg1);
        ip_legacy=configFileReader.getenvironmentUrl(arg3);
        System.out.println(method);
        System.out.println("the ip is "+ip);
    }
}
