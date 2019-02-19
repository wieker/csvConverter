package org.testwork.assignment.calculator;

import org.testng.annotations.Test;
import org.testwork.assignment.Athlet;
import org.testwork.assignment.Result;
import org.testwork.assignment.event.EventRule;
import org.testwork.assignment.input.CSVParser;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CalculationTest {
    @Test
    public void checkPlacesSame() {
        Athlet firstAthlet = new Athlet("First Athlet", null);
        Athlet secondAthlet = new Athlet("Second Athlet", null);
        firstAthlet.setScore(100);
        secondAthlet.setScore(100);
        CompetitionCalculator calculator = new CompetitionCalculator();
        calculator.assignPlaces(Arrays.asList(firstAthlet, secondAthlet));
        assertEquals(firstAthlet.getPlace(), secondAthlet.getPlace());
        assertEquals(firstAthlet.getPlace(), "1-2");
        System.out.println("Places test result:" + secondAthlet.getPlace());
    }

    @Test
    public void checkPlacesOrdered() {
        Athlet firstAthlet = new Athlet("First Athlet", null);
        Athlet secondAthlet = new Athlet("Second Athlet", null);
        firstAthlet.setScore(200);
        secondAthlet.setScore(100);
        CompetitionCalculator calculator = new CompetitionCalculator();
        calculator.assignPlaces(Arrays.asList(firstAthlet, secondAthlet));
        assertEquals(firstAthlet.getPlace(), "1");
        assertEquals(secondAthlet.getPlace(), "2");
        System.out.println("Places test result:" + secondAthlet.getPlace());
    }

    @Test
    public void checkPlacesFromFile() {
        InputStream resultsInput = getClass().getResourceAsStream("/example_results.csv");
        InputStream eventsInput = getClass().getResourceAsStream("/events_description.csv");
        List<Athlet> athlets = CSVParser.parseCSVFile(resultsInput, CSVParser.athletConverter);
        List<EventRule> eventRuleRules = CSVParser.parseCSVFile(eventsInput, CSVParser.eventConverter);
        Result result = new CompetitionCalculator().calculateCompetitionResult(athlets, eventRuleRules);

        assertEquals(result.getResults().get(2).getPlace(), "2-3");
    }
}
