package experiments.datastructure.common;

public class Student {

    private final String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student p = (Student) o;
        return p.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
