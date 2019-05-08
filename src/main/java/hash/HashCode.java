package hash;

import java.util.Objects;

public class HashCode {

    int grade;
    int cls;
    String firstName;
    String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashCode)) return false;
        HashCode hashCode = (HashCode) o;
        return grade == hashCode.grade &&
                cls == hashCode.cls &&
                Objects.equals(firstName, hashCode.firstName) &&
                Objects.equals(lastName, hashCode.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(grade, cls, firstName, lastName);
    }
}
