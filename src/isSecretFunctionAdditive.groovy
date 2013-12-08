
int secret(int n) {
    n
}

void isAdditive(int yourNumber) {

    ArrayList primedArray = constructPrimeArray(yourNumber)
    boolean additive = true
    //save secret function results, so we calculate each value exactly once, performance enhancement, perhaps?
    Map secretResults = [:]

    primedArray.eachWithIndex { it, index ->
        if (it != null) {  //not sure whether null checks are needed
            secretResults[it] = secretResults[it] ?: secret(it)
            primedArray[index..primedArray.size() - 1].each {that ->

                if (that != null && it!=that) {  //do not sum itself

                    secretResults[that] = secretResults[that] ?: secret(that)
                    secretResults[(it + that)] = secretResults[(it + that)] ?: secret(it + that)

                    if (secretResults[it] + secretResults[that] != secretResults[(it + that)]) {
                        additive = false;
                    }
                }
            }
        }
    }
    println additive ? "secret is additive" : "secret is not additive"
}

private ArrayList constructPrimeArray(int yourNumber) {
    def initialArray = []
    def primedArray = []

    //cause 1 is not a prime
    (2..yourNumber).each {
        initialArray.add it
        primedArray.add(it)
    }

    initialArray.each {it ->
        if (!(it ** 2 > yourNumber)) {
            primedArray.removeAll { num ->
                num % it == 0 && num != it
            }
        }
    }
    return primedArray
}
isAdditive(this.args[0].toInteger())

