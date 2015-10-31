package com.prunatic.pills.cucumber.domain.pills;

import com.prunatic.pills.domain.pills.command.AddPillCommand;
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
    private final PillsCollection pillsCollection;
    private final AddPillCommand addPillCommand;

    private List<Pill> pills;

    public PillsSteps() {
        pillsCollection = new InMemoryPillsCollection();
        addPillCommand = new AddPillCommand(pillsCollection);
    }

    @Given("^the following pills collection:$")
    public void addPillsToCollection(List<Map<String, String>> rows) throws Throwable {
        rows.parallelStream()
            .forEach(row -> addPillFromContent(row.get("id"), row.get("title"), row.get("content"), row.get("surveyId")));
    }

    private void addPillFromContent(String id, String title, String content, String surveyId) {
        addPillCommand.execute(id, title, content, surveyId);
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
                final boolean found = checkPillExists(pillId);
                Assert.assertTrue(String.format("Have not found any pill with id '%s'", pillId), found);
            });
    }

    private boolean checkPillExists(PillId pillId) {
        return pills.parallelStream().anyMatch(pill -> pillId.equals(pill.getId()));
    }
}
