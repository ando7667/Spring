package org.ignatov.hw2;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Student {

    private static long idCounter = 1L;

    private final long id;
    private String name;
    private String groupName;

    @JsonCreator
    public Student(long id, String name, String groupName) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
    }

    public Student(String name, String groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }
}
