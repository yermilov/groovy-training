/**
 * @author yaroslav.yermilov
 */

def rangeWeekday(Weekday weekday) {
    switch (weekday) {
        case (new Weekday('TUESDAY')..new Weekday('FRIDAY')) : return 1
        case (new Weekday('SATURDAY')..new Weekday('MONDAY')) : return 2
        default: assert false
    }
}

assert rangeWeekday(new Weekday('MONDAY')) == 2
assert rangeWeekday(new Weekday('TUESDAY')) == 1
assert rangeWeekday(new Weekday('WEDNESDAY')) == 1
assert rangeWeekday(new Weekday('THURSDAY')) == 1
assert rangeWeekday(new Weekday('FRIDAY')) == 1
assert rangeWeekday(new Weekday('SATURDAY')) == 2
assert rangeWeekday(new Weekday('SUNDAY')) == 2