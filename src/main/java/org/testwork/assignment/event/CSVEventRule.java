package org.testwork.assignment.event;

public class CSVEventRule implements EventRule {
    private String name;
    private EventRuleType eventRuleType;
    double A;
    double B;
    double C;
    private EventType eventType;

    public CSVEventRule(String name, EventRuleType eventRuleType, double a, double b, double c) {
        this.name = name;
        this.eventRuleType = eventRuleType;
        A = a;
        B = b;
        C = c;
        eventType = EventType.getByName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public EventRuleType getEventRuleType() {
        return eventRuleType;
    }

    public void setEventRuleType(EventRuleType eventRuleType) {
        this.eventRuleType = eventRuleType;
    }

    @Override
    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    @Override
    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    @Override
    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }

    @Override
    public int getColumn() {
        return eventType.getColumn();
    }
}
