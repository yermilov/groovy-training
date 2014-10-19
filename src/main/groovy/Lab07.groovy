/**
 * @author yaroslav.yermilov
 */

def group(List list, Closure equalator) {
    def result = []

    list.each { elem1 ->
        def dest = result.find { elem2 ->
            equalator(elem1, elem2[0])
        }
        if (dest) {
            dest << elem1
        } else {
            result << [ elem1 ]
        }
    }

    return result
}

assert group([1,2,3,4,5]) { o1, o2 -> o1 % 2 == o2 % 2 } == [ [1, 3, 5], [2, 4] ]

assert group(["a1","a2","b3","b4","c5"]) { o1, o2 -> o1[0] == o2[0] } == [ ["a1","a2"], ["b3","b4"], ["c5"] ]