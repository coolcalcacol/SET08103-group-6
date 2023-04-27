# USE CASE: 2 - Produce a Report on Areas with Low Store Coverage

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to produce a report on areas with high waiting times* so that *I can sustain business in existing locations by opening new locations to relieve load from existing stores.*

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains current stores and sales information for each store.

### Success End Condition

A report is available for HR to review.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for new locations is sent to HR.

## MAIN SUCCESS SCENARIO

1. Branch complains about excessive waiting times for customers due to lack of capacity.
2. HR advisor captures the country and region of the branch.
3. HR advisor extracts a key location to expand into.
4. HR advisor provides report to HR.

## EXTENSIONS

3. **Branch is in a country with no stores.**
    1. HR advisor informs branch that there are no stores in the country.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Sprint 2.