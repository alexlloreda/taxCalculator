import org.taxcalculator.Calculator

val threshold = BigDecimal(18200)
val rate = BigDecimal(0.19)
val remainingRate = 1 - rate
val step1 = BigDecimal(37000)
val amount = BigDecimal(33428)

val rest = amount - threshold
val adjust = rest / remainingRate
val result = adjust + threshold

val c = new Calculator
c.grossTaxOf(48953)
c.grossTaxOf(63000)

