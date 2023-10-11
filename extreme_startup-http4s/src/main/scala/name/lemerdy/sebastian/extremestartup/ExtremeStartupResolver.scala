package name.lemerdy.sebastian.extremestartup

import scala.util.matching.Regex

object ExtremeStartupResolver:

  private val plus: Regex = """[0-9a-f]{8}: what is (\d+) plus (\d+)""".r
  private val largest2: Regex = """[0-9a-f]{8}: which of the following numbers is the largest: (\d+), (\d+)""".r
  private val largest4: Regex =
    """[0-9a-f]{8}: which of the following numbers is the largest: (\d+), (\d+), (\d+), (\d+)""".r
  private val multiply: Regex = """[0-9a-f]{8}: what is (\d+) multiplied by (\d+)""".r
  private val squareAndCube2: Regex =
    """[0-9a-f]{8}: which of the following numbers is both a square and a cube: (\d+), (\d+)""".r
  private val squareAndCube4: Regex =
    """[0-9a-f]{8}: which of the following numbers is both a square and a cube: (\d+), (\d+), (\d+), (\d+)""".r
  private val prime2: Regex =
    """[0-9a-f]{8}: which of the following numbers are primes: (\d+), (\d+)""".r
  private val prime4: Regex =
    """[0-9a-f]{8}: which of the following numbers are primes: (\d+), (\d+), (\d+), (\d+)""".r
  private val effelTower: Regex = """[0-9a-f]{8}: which city is the Eiffel tower in""".r
  private val jamesBond: Regex = """[0-9a-f]{8}: who played James Bond in the film Dr No""".r
  private val banana: Regex = """[0-9a-f]{8}: what colour is a banana""".r

  def resolve: String => Option[String] =
    case plus(left, right)               => Some((left.toInt + right.toInt).toString)
    case largest2(left, right)           => Some(Seq(left.toInt, right.toInt).max.toString)
    case largest4(one, two, three, four) => Some(Seq(one.toInt, two.toInt, three.toInt, four.toInt).max.toString)
    case multiply(left, right)           => Some((left.toInt * right.toInt).toString)
    case squareAndCube2(one, two) =>
      Seq(one.toInt, two.toInt)
        .find(number => Math.sqrt(number) % number == 0 && number % number % number == 0)
        .map(_.toString)
    case squareAndCube4(one, two, three, four) =>
      Seq(one.toInt, two.toInt, three.toInt, four.toInt)
        .find(number => number % number == 0 && (number / number) % number == 0)
        .map(_.toString)
    case prime2(one, two) =>
      Some(
        Seq(one.toInt, two.toInt)
          .filter(number => {
            Range(2, Math.sqrt(number).toInt).count(n => number % n == 0) == 1
          })
          .map(_.toString)
          .mkString(", ")
      )
    case prime4(one, two, three, four) =>
      Some(
        Seq(one.toInt, two.toInt, three.toInt, four.toInt)
          .filter(number => {
            Range(2, Math.sqrt(number).toInt).count(n => number % n == 0) == 1
          })
          .map(_.toString)
          .mkString(", ")
      )
    case effelTower() => Some("Paris")
    case jamesBond()  => Some("Sean Connery")
    case banana()     => Some("yellow")
    case _            => None
