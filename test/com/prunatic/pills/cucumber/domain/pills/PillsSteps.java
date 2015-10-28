package com.prunatic.pills.cucumber.domain.pills;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

/**
 */
public class PillsSteps {
    @Given("^the following pills:$")
    public void the_following_pills(List<Map<String, String>> rows) throws Throwable {
        rows.parallelStream()
                .forEach(row -> {
                    System.out.println(String.format("Adding pill titled '%s' with content '%s'", row.get("title"), row.get("content")));
                });
        throw new PendingException();
    }

    @When("^I get all the pills$")
    public void i_get_all_the_pills() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I found the following pills:$")
    public void i_found_the_following_pills(DataTable data) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }
}
