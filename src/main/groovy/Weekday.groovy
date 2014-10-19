/**
 * @author yaroslav.yermilov
 */
class Weekday implements Comparable<Weekday> {

   def DAYS = [ 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday' ]

    int idx

    private Weekday(int idx) {
        this.idx = idx
    }

    Weekday(String name) {
        idx = DAYS.indexOf(name.toLowerCase())
        assert idx != -1
    }

    Weekday next() {
        new Weekday((this.idx + 1) % DAYS.size())
    }

    Weekday previous() {
        new Weekday((this.idx - 1 + DAYS.size()) % DAYS.size())
    }

    @Override
    int compareTo(Weekday o) {
        return this.idx.compareTo(o.idx)
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Weekday weekday = (Weekday) o

        if (idx != weekday.idx) return false

        return true
    }

    int hashCode() {
        return idx
    }
}
