package org.testwork.assignment.event;

public enum EventType implements EventRule {
    SHORT_DISTANCE("100 m", EventRuleType.TRACK_EVENT, 25.4347, 18, 1.81),
    LONG_JUMP("Long jump", EventRuleType.FIELD_EVENT, 0.14354, 220, 1.4),
    SHOT_PUT("Shot put", EventRuleType.FIELD_EVENT,	51.39, 1.5,	1.05),
    HIGH_JUMP("High jump", EventRuleType.FIELD_EVENT, 0.8465, 75, 1.42),
    MIDDLE_DISTANCE("400 m", EventRuleType.TRACK_EVENT, 1.53775, 82, 1.81),
    HURDLES("110 m hurdles", EventRuleType.TRACK_EVENT,	5.74352, 28.5, 1.92),
    DISCUS("Discus throw", EventRuleType.FIELD_EVENT, 12.91, 4, 1.1),
    POLE("Pole vault", EventRuleType.FIELD_EVENT, 0.2797, 100, 1.35),
    JAVELIN("Javelin throw", EventRuleType.FIELD_EVENT, 10.14, 7, 1.08),
    LONG_DISTANCE("1500 m", EventRuleType.TRACK_EVENT, 0.03768, 480, 1.85),
    ;


    private String name;
    private EventRuleType eventRuleType;
    double A;
    double B;
    double C;

    EventType(String name, EventRuleType eventRuleType, double a, double b, double c) {
        this.name = name;
        this.eventRuleType = eventRuleType;
        A = a;
        B = b;
        C = c;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public EventRuleType getEventRuleType() {
        return eventRuleType;
    }

    @Override
    public double getA() {
        return A;
    }

    @Override
    public double getB() {
        return B;
    }

    @Override
    public double getC() {
        return C;
    }

    @Override
    public int getColumn() {
        return ordinal();
    }

    public static EventType getByName(String name) {
        for (EventType eventType : values()) {
            if (eventType.getName().equalsIgnoreCase(name)) {
                return eventType;
            }
        }
        return null;
    }

    public static EventType getByOrder(int order) {
        for (EventType eventType : values()) {
            if (eventType.ordinal() == order) {
                return eventType;
            }
        }
        return null;
    }


}
