/**
 * @author yaroslav.yermilov
 */

def group(List list, Closure equalator) {
    def result = [:]

    list.each { elem1 ->
        result[elem1] = list.count { elem2 ->
            equalator elem1, elem2
        }
    }

    return result
}

assert group([1,2,3,4,5]) { o1, o2 -> o1 % 2 == o2 % 2 } == [ 0:2, 1:3 ]