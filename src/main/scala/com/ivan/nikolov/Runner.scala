package com.ivan.nikolov

import com.ivan.nikolov.circles.{SmallestEnclosingCircle, Point}

/**
 * A simple runner class in scala that does some testing things.
 */
class Runner {

  def printMessage(message: String) =
    System.out.println("Your message is: %s".format(message))

  def testSmallestEnclosingCircle(points: Point*): Unit = {
    val circle = SmallestEnclosingCircle(points: _*).makeCircle
    System.out.println(
      "Resulting circle:\n%s"
      .format(circle)
    )
  }
}

object Runner {
  def main(args: Array[String]): Unit = {
    val message = if (args.size > 0) {
      args(0)
    } else {
      "nothing"
    }
    val runner = new Runner
    runner.printMessage(message)
    runner.testSmallestEnclosingCircle(Point(1, 2), Point(3, 2))
    runner.testSmallestEnclosingCircle(Point(1, 2), Point(3, 2), Point(5, 2))
  }
}
