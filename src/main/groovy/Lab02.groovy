/**
 * @author yaroslav.yermilov
 */

assert new Money(8, 'UAH') == new Money(8, 'UAH')

assert new Money(8, 'UAH') == new Money(8)

assert new Money(8, 'UAH') + new Money(9, 'UAH') == new Money(17, 'UAH')

assert new Money(8) + new Money(9, 'UAH') == new Money(17, 'UAH')

assert new Money(8) + new Money(9) == new Money(17)

assert new Money(8, 'UAH') + new Money(9) == new Money(17, 'UAH')

assert new Money(8, 'UAH') + new Money(9, 'USD') == new Money(124.29260000037762, 'UAH')

assert new Money(8, 'USD') + new Money(9, 'UAH') == new Money(8.69651895310, 'USD')