package org.taxcalculator

class Threshold( val grossAmount: BigDecimal,
                 val netAmount: BigDecimal,
                 val baseTax: BigDecimal,
                 val rate: BigDecimal,
                 val lowerThreshold: BigDecimal) {
  override def toString: String = s"[$grossAmount, $netAmount, $baseTax, $rate, $lowerThreshold]"
}

class Calculator {

  val MEDICARE_FREE_THRESHOLD = 21335
  val MEDICARE_PHASE_IN = 26668
  val MEDICARE_LEVY = 0.02

  val taxThresholds = List(
    new Threshold(18200, 18200, 0, 0, 0),
    new Threshold(37000, 33428, 0, 0.19, 18200),
    new Threshold(80000, 62453, 3572, 0.325, 37000),
    new Threshold(180000, 125453, 17547, 0.37, 80000),
    new Threshold(999999999, 999999999, 54547, 0.45, 180000)
  )

  val medicareThresholds = {
    taxThresholds.map(t => new Threshold(
      t.grossAmount,
      if (t.grossAmount < MEDICARE_PHASE_IN) t.netAmount
      else t.netAmount - t.grossAmount * 0.02,
      t.baseTax,
      t.rate,
      t.lowerThreshold
    ))
  }

  def amountBeforeTax(netAmount: BigDecimal) = {
    def gross(t: Threshold) = {
      val partial = netAmount + t.baseTax - t.lowerThreshold
      t.lowerThreshold + (partial / (1-t.rate))
    }
    def checkThreshold(thresholds: List[Threshold]): BigDecimal = {
      if (netAmount < thresholds.head.netAmount) gross(thresholds.head)
      else checkThreshold(thresholds.tail)
    }
    checkThreshold(taxThresholds)
  }

  def withMedicare(netAmount: BigDecimal) = {
    //medicareThresholds.foreach( t => print(t))
    def gross(t: Threshold) = {
      val top = netAmount + t.baseTax
      val bottom = 1 - t.rate - MEDICARE_LEVY
      top / bottom
    }
    def checkThreshold(thresholds: List[Threshold]): BigDecimal = {
      if (netAmount < thresholds.head.netAmount) gross(thresholds.head)
      else checkThreshold(thresholds.tail)
    }
    checkThreshold(medicareThresholds)
  }
/*
  def withMedicareLevy(netAmount: BigDecimal) = {
    val x = netAmount
    x - (x * 0.3) - (x * 0.02)
    x * (1 - 0.3 - 0.02)
    x * (1 - y - 0.02) = netAmount
    x * (0.98 - y) = netAmount
    0.98x - yx = netAmount
    yx = netAmount - 0.98x
  }
*/
}

