package com.example.firstproject;

public class TodoItem
{
    enum Priority {
        High,
        Medium,
        Low
    }

    enum Period {
        Day,
        Week
    }
    int id;
    String title;
    String description;
    Priority priority;
    Period period;

    TodoItem (int id, String title, String description, Priority priority, Period period) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.period = period;
    }

    // temporary
    TodoItem (int id, String title, String description, String priority, String period) {
        this.id = id;
        this.title = title;
        this.description = description;

        String lowerCasePriority = priority.toLowerCase();
        switch (lowerCasePriority) {
            case "high":
                this.priority = Priority.High;
                break;
            case "low":
                this.priority = Priority.Low;
                break;
            default:
                this.priority = Priority.Medium;
                break;
        }

        String lowerCasePeriod = period.toLowerCase();
        switch (lowerCasePeriod) {
            case "week":
                this.period = Period.Week;
                break;
            default:
                this.period = Period.Day;
                break;
        }
    }

    String getTitle () {
        return title;
    }
}
