package com.jayway.awaitility.core;

import org.hamcrest.Matcher;

/**
 * Contains properties of the condition at its current stage.
 *
 * @param <T> The condition evaluation result value type.
 */
public class EvaluatedCondition<T> {
    private final String description;
    private final Matcher<? super T> matcher;
    private final T currentConditionValue;
    private final long elapsedTimeInMS;
    private final long remainingTimeInMS;
    private final boolean conditionIsFulfilled;
    private final String alias;

    /**
     * @param description           A descriptive match message or mismatch message of the matcher. If <code>isConditionSatisfied</code> is <code>true</code> then it
     *                              describes a match message, if <code>false</code> then it describes a mismatch message.
     * @param matcher               The Hamcrest matcher used in the condition
     * @param currentConditionValue The current value of the condition.
     * @param elapsedTimeInMS       elapsed time in milliseconds.
     * @param remainingTimeInMS     remaining time to wait in milliseconds; <code>Long.MAX_VALUE</code>, if no timeout defined, i.e., running forever.
     * @param isConditionSatisfied  <code>true</code> if the condition is satisfied (i.e. hamcrest matcher matches the value), <code>false</code> otherwise (i.e. an intermediate value).
     */
    EvaluatedCondition(String description, Matcher<? super T> matcher, T currentConditionValue, long elapsedTimeInMS, long remainingTimeInMS,
                       boolean isConditionSatisfied, String alias) {
        this.description = description;
        this.matcher = matcher;
        this.currentConditionValue = currentConditionValue;
        this.elapsedTimeInMS = elapsedTimeInMS;
        this.remainingTimeInMS = remainingTimeInMS;
        this.conditionIsFulfilled = isConditionSatisfied;
        this.alias = alias;
    }

    /**
     * @return Descriptive message of the Hamcrest matcher.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return <code>true</code> if the condition has a matcher (i.e. it's a Hamcrest Based condition) which means that {@link #getMatcher()} will return a non-null value.
     */
    public boolean isHamcrestCondition() {
        return matcher != null;
    }

    /**
     * @return The Hamcrest matcher used in the condition if this condition is a Hamcrest condition
     * @see #isHamcrestCondition()
     */
    public Matcher<? super T> getMatcher() {
        return matcher;
    }

    /**
     * @return The current value of the condition.
     */
    public T getValue() {
        return currentConditionValue;
    }

    /**
     * @return Elapsed time in milliseconds.
     */
    public long getElapsedTimeInMS() {
        return elapsedTimeInMS;
    }

    /**
     * @return Remaining time to wait in milliseconds or <code>Long.MAX_VALUE</code> if no timeout defined, i.e., running forever.
     */
    public long getRemainingTimeInMS() {
        return remainingTimeInMS;
    }

    /**
     * @return <code>true</code> if the condition doesn't have a timeout, <code>false</code> otherwise.
     */
    public boolean isConditionRunningForever() {
        return getRemainingTimeInMS() == Long.MAX_VALUE;
    }

    /**
     * @return <code>true</code> if the condition is satisfied (i.e. hamcrest matcher matches the value), <code>false</code> otherwise (i.e. an intermediate value).
     */
    public boolean isSatisfied() {
        return conditionIsFulfilled;
    }

    /**
     * @return The Awaitility alias for the condition (if any).
     * @see #hasAlias()
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @return <code>true</code> if this condition defined an alias.
     * @see #getAlias()
     */
    public boolean hasAlias() {
        return alias != null;
    }
}
