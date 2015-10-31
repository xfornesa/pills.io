package com.prunatic.pills.cucumber.domain.pills;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.prunatic.pills.domain.pills.InMemoryPillsCollection;
import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.pills.PillsCollection;
import com.prunatic.pills.domain.pills.command.AddPillCommand;
import com.prunatic.pills.domain.pills.event.PillAddedEvent;
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
    private final EventBus eventBus;

    private List<Pill> pills;
    private PillAddedEventListener pillAddedEventListener;
    private PillId addedPillId;

    public PillsSteps() {
        eventBus = new EventBus();
        pillsCollection = new InMemoryPillsCollection();
        addPillCommand = new AddPillCommand(eventBus, pillsCollection);
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

    @Given("^I am listening for a PillAddedEvent$")
    public void addPillAddedEventListener() throws Throwable {
        pillAddedEventListener = new PillAddedEventListener();
        eventBus.register(pillAddedEventListener);
    }

    @When("^I add a pill$")
    public void addSomePill() throws Throwable {
        addedPillId = PillId.fromString("anId");
        addPillFromContent(addedPillId.toString(), "someTitle", "someContent", "aSurveyId");
    }

    @Then("^I should receive a PillAddedEvent with its pillId$")
    public void checkPillAddedEventReceived() throws Throwable {
        Assert.assertTrue(addedPillId.equals(pillAddedEventListener.pillId));
    }

    private class PillAddedEventListener {
        public PillId pillId;

        @Subscribe
        public void handle(PillAddedEvent event) {
            pillId = event.getPillId();
        }
    }
}
