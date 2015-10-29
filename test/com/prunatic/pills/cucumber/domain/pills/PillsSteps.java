package com.prunatic.pills.cucumber.domain.pills;

import com.prunatic.pills.domain.pills.InMemoryPillsCollection;
import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.pills.PillsCollection;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

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
            .map(row -> Pill.fromContent(row.get("id"), row.get("title"), row.get("content")))
            .forEach(pillsCollection::add);
    }

    @When("^I get all the pills$")
    public void getPills() throws Throwable {
        pills = pillsCollection.findAll();
    }

    @Then("^I found the following pills:$")
    public void checkPillsList(List<String> rows) throws Throwable {
        rows.parallelStream()
            .map(PillId::fromString)
            .forEach(pillId -> {
                final boolean found = pills.parallelStream()
                        .anyMatch(pill -> pillId.equals(pill.getId()));
                Assert.assertTrue(String.format("Have not found any pill with id '%s'", pillId), found);
            });
    }
}
