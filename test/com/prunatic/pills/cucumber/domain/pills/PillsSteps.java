package com.prunatic.pills.cucumber.domain.pills;

import com.prunatic.pills.domain.pills.InMemoryPillsCollection;
import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillsCollection;
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
    private PillsCollection pillsCollection;
    private List<Pill> pills;

    public PillsSteps() {
        pillsCollection = new InMemoryPillsCollection();
    }

    @Given("^the following pills collection:$")
    public void addPillsToCollection(List<Map<String, String>> rows) throws Throwable {
        rows.parallelStream()
            .map(row -> Pill.fromContent(row.get("title"), row.get("content")))
            .forEach(pillsCollection::add);
    }

    @When("^I get all the pills$")
    public void getPills() throws Throwable {
        pills = pillsCollection.findAll();
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
