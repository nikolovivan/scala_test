package com.ivan.nikolov.circles

import org.scalatest.{FlatSpec, Matchers}

class PointTest extends FlatSpec with Matchers {
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

  "cross" should "be the crossed between the two points." in {
    val p1 = Point(83.34, 48.3)
    val p2 = Point(34.4, 23.3)
    val cross = p1.cross(p2)
    cross should equal(p1.x * p2.y - p1.y * p2.x)
  }

  "distance" should "be the hypot definition." in {
    val p1 = Point(2, 3)
    val p2 = Point(6, 7)
    val distance = p1.distance(p2)
    distance should equal(math.sqrt(32))
  }
}
