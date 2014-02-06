package com.ivan.nikolov

/**
 * A simple runner class in scala that does some testing things.
 *
 * Created by volcom on 06/02/14.
 */
class Runner {

  def printMessage(message: String) =
    System.out.println("Your message is: %s".format(message))
}

object Runner {
  def main(args: Array[String]) {
    val message = if (args.size > 0) args(0) else "nothing"
    val runner = new Runner
    runner.printMessage(message)
  }
}
