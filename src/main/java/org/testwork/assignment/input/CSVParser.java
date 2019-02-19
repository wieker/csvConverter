package org.testwork.assignment.input;

import org.testwork.assignment.Athlet;
import org.testwork.assignment.event.CSVEventRule;
import org.testwork.assignment.event.EventRule;
import org.testwork.assignment.event.EventRuleType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVParser {

    public static Function<String, EventRule> eventConverter = (csvLine) -> {
        String[] splittedLine = csvLine.split(",");
        if (splittedLine.length != 5) {
            return null;
        }
        EventRule eventRule = new CSVEventRule(splittedLine[0], EventRuleType.valueOf(splittedLine[1]),
                Double.parseDouble(splittedLine[2]), Double.parseDouble(splittedLine[3]), Double.parseDouble(splittedLine[4]));
        return eventRule;
    };

    public static Function<String, Athlet> athletConverter = (csvLine) -> {
        String[] splittedLine = csvLine.split(";");
        if (splittedLine.length != 11) {
            return null;
        }
        double[] decathlon = new double[10];
        for (int i = 0; i < decathlon.length; i ++) {
            decathlon[i] = EventRule.parseByColumnNumber(i, splittedLine[i + 1]);
        }
        return new Athlet(splittedLine[0], decathlon);
    };

    public static <T> List<T> parseCSVFile(InputStream inputStream, Function<String, T> converter) {
        List<T> parsedList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            parsedList = br.lines().map(converter).filter(Objects::nonNull).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {


        }

        return parsedList;
    }
}
