package experiments.datastructure.hashset;

import experiments.Experiment;
import experiments.datastructure.common.Student;

public class SimpleHashSetExperiment extends Experiment {

    private static final String PRESENT = " is present.";
    private static final String MISSING = " is missing.";

    @Override
    protected void execute() {
        SimpleHashSet<Student> classRoom = new SimpleHashSet<>();

        Student student1 = new Student("Student 1");
        Student student2 = new Student("Student 2");
        Student student3 = new Student("Student 3");
        Student student4 = new Student("Student 4");
        Student student5 = new Student("Misbehaving student");

        classRoom.add(student1);
        classRoom.add(student2);
        classRoom.add(student3);
        classRoom.add(student4);
        classRoom.add(student5);

        // Ran away from class...
        classRoom.remove(student5);

        System.out.println(student1 + (classRoom.contains(student1) ? PRESENT : MISSING));
        System.out.println(student2 + (classRoom.contains(student2) ? PRESENT : MISSING));
        System.out.println(student3 + (classRoom.contains(student3) ? PRESENT : MISSING));
        System.out.println(student4 + (classRoom.contains(student4) ? PRESENT : MISSING));
        System.out.println(student5 + (classRoom.contains(student5) ? PRESENT : MISSING));
    }
}
