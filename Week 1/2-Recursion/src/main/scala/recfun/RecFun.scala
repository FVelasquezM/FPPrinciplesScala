package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do print(s"${pascal(col, row)} ")
      println()

    println("Balance ")
    println(
      s"(if (zero? x) max (/ 1 x)) - ${balance("(if (zero? x) max (/ 1 x))".toList)}"
    )
    println(
      s"I told him (that it's not (yet) done). (But he wasn't listening) - ${balance("I told him (that it's not (yet) done). (But he wasn't listening)".toList)}"
    )
    println(s":-) - ${balance(":-)".toList)}")
    println(s"())( - ${balance("())(".toList)}")

  /** Exercise 1
    */
  def pascal(c: Int, r: Int): Int =
    if (c == r || c == 0) then 1 else pascal(c, r - 1) + pascal(c - 1, r - 1)

  /** Exercise 2
    */
  def balance(chars: List[Char]): Boolean =
    def go(open: Int, curr: List[Char]): Boolean =
      if (open < 0) false
      else if (curr.isEmpty) open == 0
      else if (curr.head == '(') go(open + 1, curr.tail)
      else if (curr.head == ')') go(open - 1, curr.tail)
      else go(open, curr.tail)
    go(0, chars)

  /** Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money == 0) 1
    else if (money < 0) 0
    else if (coins.isEmpty && money >= 1) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
