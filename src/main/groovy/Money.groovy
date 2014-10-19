import groovyx.net.http.RESTClient

/**
 * @author yaroslav.yermilov
 */
class Money implements Comparable<Money> {
    BigDecimal amount
    String currency

    RESTClient restClient = new RESTClient('http://api.exchangeratelab.com/api/')

    public Money(BigDecimal amount, String currency) {
        this.amount = amount
        this.currency = currency
    }

    public Money(BigDecimal amount) {
        this(amount, null)
    }

    Money plus(Money other) {
        BigDecimal newAmount = this.amount + other.amount
        String newCurrency

        if (this.currency == null && other.currency == null) {
            newCurrency = null
        }
        if (this.currency != null && other.currency == null) {
            newCurrency = this.currency
        }
        if (this.currency == null && other.currency != null) {
            newCurrency = other.currency
        }
        if (this.currency != null && other.currency != null) {
            if (this.currency == other.currency) {
                newCurrency = this.currency
            } else {
                BigDecimal rate1
                if (this.currency != 'USD') {
                    rate1 = restClient.get(path: "single/${this.currency}", params: [apikey: '7A8A33405E90BB0B9996736BCC7131A6']).data.rate.rate
                } else {
                    rate1 = 1.0
                }
                BigDecimal rate2
                if (other.currency != 'USD') {
                    rate2 = restClient.get(path: "single/${other.currency}", params: [apikey: '7A8A33405E90BB0B9996736BCC7131A6']).data.rate.rate
                } else {
                    rate2 = 1.0
                }

                newCurrency = this.currency
                newAmount = (this.amount / rate1 + other.amount / rate2) * rate1
            }
        }

        return new Money(newAmount, newCurrency)
    }



    String toString() {
        "$amount $currency"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Money money = (Money) o

        if (Math.abs(amount - money.amount) > 0.01)  return false
        if (money.currency != null && currency != money.currency) return false

        return true
    }

    int hashCode() {
        int result
        result = amount
        result = 31 * result + (currency != null ? currency.hashCode() : 0)
        return result
    }

    @Override
    int compareTo(Money other) {
        BigDecimal thisUsd = toUsd(this)
        BigDecimal otherUsd = toUsd(other)

        return thisUsd.compareTo(otherUsd)
    }

    BigDecimal toUsd(Money money) {
        BigDecimal rate
        if (money.currency != 'USD') {
            rate = restClient.get(path: "single/${money.currency}", params: [apikey: '7A8A33405E90BB0B9996736BCC7131A6']).data.rate.rate
        } else {
            rate = 1.0
        }

        return money.amount / rate
    }

    Money next() {
        new Money(amount + 1, currency)
    }

    Money previous() {
        new Money(amount - 1, currency)
    }
}
