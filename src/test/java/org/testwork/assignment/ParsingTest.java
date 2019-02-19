package org.testwork.assignment;

import org.testng.annotations.Test;
import org.testwork.assignment.calculator.CompetitionCalculator;
import org.testwork.assignment.event.EventRule;
import org.testwork.assignment.input.CSVParser;
import org.testwork.assignment.output.XMLFormatter;

import java.io.InputStream;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ParsingTest {
    @Test
    public void testParsing() throws Exception {
        InputStream resultsInput = getClass().getResourceAsStream("/example_results.csv");
        InputStream eventsInput = getClass().getResourceAsStream("/events_description.csv");
        List<Athlet> athlets = CSVParser.parseCSVFile(resultsInput, CSVParser.athletConverter);
        List<EventRule> eventRuleRules = CSVParser.parseCSVFile(eventsInput, CSVParser.eventConverter);
        Result result = new CompetitionCalculator().calculateCompetitionResult(athlets, eventRuleRules);

        assertEquals(result.getResults().get(0).getScore(), 4200);
        assertEquals(result.getResults().get(4).getScore(), 3099);

        new XMLFormatter().createXML(result.getResults(), System.out);
    }
}
