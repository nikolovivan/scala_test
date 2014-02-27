package com.ivan.nikolov.circles

import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * Created by volcom on 27/02/14.
 */
class PointTest extends FlatSpec with ShouldMatchers {
  "subtract" should "subtract the x and y values of different points." in {
    val p1 = Point(21.5, 845.9)
    val p2 = Point(34.5, 56.7)
    val p3 = p1.subtract(p2)
    p3.x should equal(p1.x - p2.x)
    p3.y should equal(p1.y - p2.y)
  }

  "normal" should "be equal to the square of x + the square of y." in {
    val p1 = Point(3.45, 95.9)
    val normal = p1.normal
    normal should equal(p1.x * p1.x + p1.y * p1.y)
  }
}
