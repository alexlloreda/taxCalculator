import org.scalatest.FlatSpec
import org.taxcalculator.Calculator

class CalculatorTest extends FlatSpec {

  val calculator = new Calculator()

  "A Calculator" should "return 18000 when net income is 18000 " in {
    assert(calculator.amountBeforeTax(18000) == 18000)
  }

  it should "return 0 when net income is 0" in {
    assert(calculator.amountBeforeTax(0) == 0)
  }

  it should "return 37000 when net income is 33428" in {
    assert(calculator.amountBeforeTax(33428) == 37000)
  }

  it should "return 80000 when net income is 62453" in {
    assert(calculator.amountBeforeTax(62453) == 80000)
  }

  it should "return 180000 when net income is 125453" in {
    assert(calculator.amountBeforeTax(125453) == 180000)
  }

  it should "return 110000 when net income is 81353" in {
    assert(calculator.amountBeforeTax(81353) == 110000)
  }

  it should "calculate medicare too" in {
    //assert(calculator.withMedicare(0) == 0)
    //assert(calculator.withMedicare(18200) == 18200)
    assert(calculator.withMedicare(32688) == 37000)
    assert(calculator.withMedicare(60853) == 80000)
    assert(calculator.withMedicare(121853) == 180000)
  }

}