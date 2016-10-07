package org.taxcalculator

class Calculator {
  val MEDICARE_FREE_THRESHOLD = 21335
  val MEDICARE_PHASE_IN = 26668
  val MEDICARE_LEVY = 2

  val TAX_FREE_THRESHOLD = BigDecimal(18200)

  val rates = Map(0 -> 0, 18200 -> 19, 37000 -> 32.5, 80000 -> 37, 180000 -> 45)
  val thresholds = Seq(
    BigDecimal(18200) -> BigDecimal(18200),
    BigDecimal(37000) -> BigDecimal(33428),
    BigDecimal(80000) -> BigDecimal(62453),
    BigDecimal(180000) -> BigDecimal(125453))

  def grossTaxOf(netAmount: BigDecimal):BigDecimal = {
    def incomeTax(baseTax: BigDecimal, lowerThreshold: BigDecimal, taxRate: BigDecimal) = {
      val partial = netAmount + baseTax - lowerThreshold
      lowerThreshold + (partial / (1-taxRate))
    }

    if (netAmount <= 18200) netAmount
    else if (netAmount <= 33428) incomeTax(0, 18200, 0.19)
    else if (netAmount <= 62453) incomeTax(3572, 37000, 0.325)
    else if (netAmount <= 125453) incomeTax(17547, 80000, 0.37)
    else incomeTax(54547, 180000, 0.45)
  }


}
