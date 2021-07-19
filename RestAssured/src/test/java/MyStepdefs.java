import dataProvider.ConfigFileReader;
import dataProvider.FileReafer;
import dataProvider.general;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import static org.hamcrest.Matcher.*;



public class MyStepdefs {
    int responsecode;
String method;
String Url;
String Query;
String endurl;
String validation;
String Testdata;
StringBuilder endpoint;
    ConfigFileReader configFileReader= new ConfigFileReader();
String ip = configFileReader.getenvironmentUrl();
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
        Query=query;
        endurl=ip+endpoint+"?"+Query;
        System.out.println("end urlis "+endurl);

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
        endpoint= urlconstructor.constructURL(Url , Testdata);
        System.out.println("endpoint"+endpoint);


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

        }

        else if(method.equalsIgnoreCase("put"))
        {
            Response response = RestAssured.put(endurl);
            System.out.println(response.getStatusCode());
            responsecode = response.getStatusCode();
            System.out.println(response.asString());

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



}
