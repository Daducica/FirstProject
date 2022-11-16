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

    String getTitle () {
        return title;
    }
}
