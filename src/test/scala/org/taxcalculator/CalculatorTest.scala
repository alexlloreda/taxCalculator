import org.scalatest.FlatSpec
import org.taxcalculator.Calculator

class CalculatorTest extends FlatSpec {

  val calculator = new Calculator()

  "A Calculator" should "return 18000 when net income is 18000 " in {
    assert(calculator.grossTaxOf(18000) == 18000)
  }

  it should "return 0 when net income is 0" in {
    assert(calculator.grossTaxOf(0) == 0)
  }

  it should "return 37000 when net income is 33428" in {
    assert(calculator.grossTaxOf(33428) == 37000)
  }

  it should "return 80000 when net income is 62453" in {
    assert(calculator.grossTaxOf(62453) == 80000)
  }

  it should "return 180000 when net income is 125453" in {
    assert(calculator.grossTaxOf(125453) == 180000)
  }

}