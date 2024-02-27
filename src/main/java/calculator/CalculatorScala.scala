package calculator


trait CalculatorScala {
  def enter(i: Int): Unit
  def add(): Unit
  def multiply(): Unit
  def subtract(): Unit
  def divide(): Unit
  def getResult: Int
  def binaryOperation(operation: (Int, Int) => Int): Unit

}

object CalculatorScala {
  private class CalculatorScalaImpl() extends CalculatorScala{
    private var numbers = List.empty[Int]
    override def enter(i: Int): Unit =
      numbers = numbers :+ i
      if (numbers.length > 2)
        throw new IllegalStateException

    override def add(): Unit = binaryOperation(_ + _)

    override def multiply(): Unit = binaryOperation(_ * _)

    override def subtract(): Unit =
      if (numbers.head >= numbers(1))
        binaryOperation(_ - _)
      else
        throw new ArithmeticException

    override def divide(): Unit =
      if (numbers(1) != 0 && (numbers.head != 0 && numbers(1) != 0))
        binaryOperation(_ / _)
      else
        throw new ArithmeticException

    override def getResult: Int = {
      if (numbers.length != 1)
        throw new IllegalStateException
      numbers.head
    }

    override def binaryOperation(operation: (Int, Int) => Int): Unit = {
      if (numbers.length != 2)
        throw new IllegalStateException
      numbers = List(operation(numbers.head, numbers(1)))
    }
  }

  def apply() : CalculatorScala = new CalculatorScalaImpl
}

