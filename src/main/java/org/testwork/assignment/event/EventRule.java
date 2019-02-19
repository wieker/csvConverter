package org.testwork.assignment.event;

import org.testwork.assignment.Athlet;

public interface EventRule {
    int CM_IN_METER = 100;

    static double parseByColumnNumber(int column, String cellValue) throws NumberFormatException {
        if (EventType.SHORT_DISTANCE.ordinal() == column) {
            return Double.parseDouble(cellValue);
        } else if (EventType.LONG_JUMP.ordinal() == column) {
            return Double.parseDouble(cellValue) * CM_IN_METER;
        } else if (EventType.SHOT_PUT.ordinal() == column) {
            return Double.parseDouble(cellValue);
        } else if (EventType.HIGH_JUMP.ordinal() == column) {
            return Double.parseDouble(cellValue) * CM_IN_METER;
        } else if (EventType.MIDDLE_DISTANCE.ordinal() == column) {
            return Double.parseDouble(cellValue);
        } else if (EventType.HURDLES.ordinal() == column) {
            return Double.parseDouble(cellValue);
        } else if (EventType.DISCUS.ordinal() == column) {
            return Double.parseDouble(cellValue);
        } else if (EventType.POLE.ordinal() == column) {
            return Double.parseDouble(cellValue) * CM_IN_METER;
        } else if (EventType.JAVELIN.ordinal() == column) {
            return Double.parseDouble(cellValue);
        } else if (EventType.LONG_DISTANCE.ordinal() == column) {
            int delimiter = cellValue.indexOf(".");
            return Integer.parseInt(cellValue.substring(0, delimiter)) * 60 + Double.parseDouble(cellValue.substring(delimiter + 1));
        } else {
            throw new NumberFormatException("Wrong cell number");
        }
    }
    default int calculateScore(Athlet athlet) {
        double result = 0.0;
        double P = athlet.getDecathlon()[getColumn()];
        double delta = EventRuleType.TRACK_EVENT.equals(getEventRuleType()) ? getB() - P : P - getB();
        result += getA() * Math.pow(delta, getC());
        return (int) result;
    }

    String getName();

    EventRuleType getEventRuleType();

    double getA();

    double getB();

    double getC();

    int getColumn();
}
