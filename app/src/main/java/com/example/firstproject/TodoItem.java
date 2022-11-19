package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="todoItems")
public class TodoItem
{
    public static final String ID_COLUMN = "idColumn";
    public static final String TITLE_COLUMN = "titleColumn";
    public static final String DESC_COLUMN = "descColumn";
    public static final String PRIORITY_COLUMN = "priorityColumn";
    public static final String PERIOD_COLUMN = "periodColumn";

    public enum Priority {
        High,
        Medium,
        Low
    }

    public enum Period {
        Day,
        Week
    }

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name=ID_COLUMN)
    private int id;

    @ColumnInfo (name=TITLE_COLUMN)
    String title;

    @ColumnInfo (name=DESC_COLUMN)
    String description;
    @ColumnInfo (name=PRIORITY_COLUMN)
    Priority priority;
    @ColumnInfo (name=PERIOD_COLUMN)
    Period period;

    TodoItem (String title, String description, Priority priority, Period period) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.period = period;
    }

    //temporary
    TodoItem (String title, String description, String priority, String period) {
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

    public String getTitle () {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
