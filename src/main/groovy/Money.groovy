import groovyx.net.http.RESTClient

/**
 * @author yaroslav.yermilov
 */
class Money implements Comparable<Money> {
    BigDecimal amount
    String currency

    RESTClient restClient = new RESTClient('http://openexchangerates.org/api/')

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
                    rate1 = restClient.get(path: "/api/latest.json", params: [app_id: '1167968b58814e429ed93644a887cd65']).data.rates."${this.currency}"
                } else {
                    rate1 = 1.0
                }
                BigDecimal rate2
                if (other.currency != 'USD') {
                    rate2 = restClient.get(path: "/api/latest.json", params: [app_id: '1167968b58814e429ed93644a887cd65']).data.rates."${other.currency}"
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
        if (this.currency == null && other.currency == null) {
            return this.amount <=> other.amount
        }
        if (this.currency != null && other.currency == null) {
            return toUsd(this.amount, this.currency) <=> toUsd(other.amount, this.currency)
        }
        if (this.currency == null && other.currency != null) {
            return toUsd(this.amount, other.currency) <=> toUsd(other.amount, other.currency)
        }
        if (this.currency != null && other.currency != null) {
            return toUsd(this.amount, this.currency) <=> toUsd(other.amount, other.currency)
        }
    }

    BigDecimal toUsd(BigDecimal amount, String currency) {
        BigDecimal rate
        if (currency != 'USD') {
            rate = restClient.get(path: "/api/latest.json", params: [app_id: '1167968b58814e429ed93644a887cd65']).data.rates."${currency}"
        } else {
            rate = 1.0
        }
        return amount / rate
    }

    Money next() {
        new Money(amount + 1, currency)
    }

    Money previous() {
        new Money(amount - 1, currency)
    }
}
