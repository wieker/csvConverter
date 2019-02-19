package org.testwork.assignment.calculator;

import org.testwork.assignment.Athlet;
import org.testwork.assignment.Result;
import org.testwork.assignment.event.EventRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionCalculator {

    public Result calculateCompetitionResult(List<Athlet> athlets, List<EventRule> eventRuleRules) {
        athlets.stream().forEach(athlet -> {
            eventRuleRules.stream().map(e -> e.calculateScore(athlet)).reduce((a, b) -> a + b).ifPresent(athlet::setScore);
        });
        athlets = athlets.stream().sorted((a1, a2) -> {
            if (a1.getScore() < a2.getScore()) {
                return 1;
            } else if (a1.getScore() > a2.getScore()) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());

        assignPlaces(athlets);

        Result result = new Result();
        result.setResults(athlets);
        return result;
    }

    void assignPlaces(List<Athlet> athlets) {
        int previousScore = athlets.get(0).getScore();
        List<Integer> fromPlace = new ArrayList<>();
        List<Integer> toPlace = new ArrayList<>();
        fromPlace.add(1);
        for (int i = 1; i < athlets.size(); i ++) {
            int currentScore = athlets.get(i).getScore();
            if (currentScore != previousScore) {
                toPlace.add(i);
                fromPlace.add(i + 1);
                previousScore = currentScore;
            }
        }
        toPlace.add(athlets.size());
        for (int i = 0; i < toPlace.size(); i ++) {
            int athletNumber = fromPlace.get(i) - 1;
            if (toPlace.get(i).equals(fromPlace.get(i))) {
                athlets.get(athletNumber).setPlace(Integer.toString(toPlace.get(i)));
            } else {
                int sharedSize = toPlace.get(i) - fromPlace.get(i);
                for (int j = 0; j <= sharedSize; j ++) {
                    athlets.get(athletNumber + j).setPlace(fromPlace.get(i) + "-" + toPlace.get(i));
                }
            }
        }
    }

}
