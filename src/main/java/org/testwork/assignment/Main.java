package org.testwork.assignment;

import org.testwork.assignment.calculator.CompetitionCalculator;
import org.testwork.assignment.event.EventRule;
import org.testwork.assignment.event.EventType;
import org.testwork.assignment.input.CSVParser;
import org.testwork.assignment.output.XMLFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    InputStream resultInput = null;
    InputStream constantsInput = null;
    PrintStream resultOutput = null;

    public InputStream tryToOpenFileRead(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            throw new RuntimeException(e);
        }
    }

    public PrintStream tryToOpenFileWrite(String fileName) {
        try {
            return new PrintStream(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            throw new RuntimeException(e);
        }
    }

    public void processCommandLine(String[] args) {
        for (int i = 0; i < args.length; i ++) {
            if (args[i].equals("-i") && i < args.length - 1) {
                resultInput = tryToOpenFileRead(args[i + 1]);
            }
            if (args[i].equals("-o") && i < args.length - 1) {
                resultOutput = tryToOpenFileWrite(args[i + 1]);
            }
            if (args[i].equals("-c") && i < args.length - 1) {
                constantsInput = tryToOpenFileRead(args[i + 1]);
            }
        }
    }

    public void run(String[] args) {
        processCommandLine(args);
        CompetitionCalculator competitionCalculator = new CompetitionCalculator();
        List<EventRule> eventRuleRules;
        if (constantsInput != null) {
            eventRuleRules = CSVParser.parseCSVFile(constantsInput, CSVParser.eventConverter);
        } else {
            eventRuleRules = new ArrayList<>();
            for (EventType eventType : EventType.values()) {
                eventRuleRules.add(eventType);
            }
        }
        if (resultInput == null) {
            resultInput = System.in;
        }
        if (resultOutput == null) {
            resultOutput = System.out;
        }
        Result competitionCalculationResult = competitionCalculator.calculateCompetitionResult(
                CSVParser.parseCSVFile(resultInput, CSVParser.athletConverter), eventRuleRules
        );
        new XMLFormatter().createXML(competitionCalculationResult.getResults(),
                resultOutput);
    }

    public static void main(String[] args) {
        new Main().run(args);
    }
}
