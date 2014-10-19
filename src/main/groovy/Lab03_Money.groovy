/**
 * @author yaroslav.yermilov
 */

def rangeMoney(Money money) {
    switch (money) {
        case (new Money(0, 'USD')..new Money(100, 'USD')) : return 'бедняк'
        case (new Money(100, 'USD')..new Money(1000, 'USD')) : return 'средняк'
        default : return 'богач'
    }
}

assert rangeMoney(new Money(5, 'USD')) == 'бедняк'
assert rangeMoney(new Money(500, 'USD')) == 'средняк'
assert rangeMoney(new Money(5000, 'USD')) == 'богач'

assert rangeMoney(new Money(10, 'UAH')) == 'бедняк'
assert rangeMoney(new Money(2400, 'UAH')) == 'средняк'
assert rangeMoney(new Money(1000000, 'UAH')) == 'богач'
