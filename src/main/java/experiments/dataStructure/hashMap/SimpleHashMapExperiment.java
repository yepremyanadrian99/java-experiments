package experiments.dataStructure.hashMap;

import experiments.Experiment;
import experiments.dataStructure.common.Student;

public class SimpleHashMapExperiment extends Experiment {

    private static final String MESSAGE = "your ticket is ";
    private static final String NOT_FOUND_MESSAGE = "not found";

    @Override
    protected void execute() {
        SimpleHashMap<Student, String> map = new SimpleHashMap<>();

        Student student1 = new Student("Student 1");
        Student student2 = new Student("Student 2");
        Student student3 = new Student("Student 3");
        Student student4 = new Student("Student 4");
        Student student5 = new Student("Unknown");

        map.put(student1, "Exam Ticket 1");
        map.put(student2, "Exam Ticket 2");
        map.put(student3, "Exam Ticket 3");
        map.put(student4, "Exam Ticket 4");

        System.out.println(student1 + ", " + MESSAGE + map.getOrDefault(student1, NOT_FOUND_MESSAGE));
        System.out.println(student2 + ", " + MESSAGE + map.getOrDefault(student2, NOT_FOUND_MESSAGE));
        System.out.println(student3 + ", " + MESSAGE + map.getOrDefault(student3, NOT_FOUND_MESSAGE));
        System.out.println(student4 + ", " + MESSAGE + map.getOrDefault(student4, NOT_FOUND_MESSAGE));
        System.out.println(student5 + ", " + MESSAGE + map.getOrDefault(student5, NOT_FOUND_MESSAGE));
    }
}
