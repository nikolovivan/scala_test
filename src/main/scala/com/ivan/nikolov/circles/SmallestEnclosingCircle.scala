package com.ivan.nikolov.circles

import scala.util.Random

/**
 * Created by volcom on 06/02/14.
 */
class SmallestEnclosingCircle {

  def makeCircle(points: Seq[Point]): Circle = {
    var c: Circle = null
    var current = 0
    val shuffled = Random.shuffle(points)
    shuffled.foreach(p => {
      if (c == null || !c.contains(p)) {
        c = makeCircleOnePoint(shuffled.slice(0, current + 1), p)
      }
      current += 1
    })
    c
  }

  private def makeCircleOnePoint(points: Seq[Point], point: Point): Circle = {
    var c = Circle(point, 0)
    var current = 0
    points.foreach(p => {
      c = c.radius match {
        case 0 => makeDiameterCircle(point, p)
        case _ => makeCircleTwoPoints(points.slice(0, current + 1), point, p)
      }
      current += 1
    })
    c
  }

  private def makeCircleTwoPoints(points: Seq[Point], p: Point, q: Point): Circle = {
    val tmp = makeDiameterCircle(p, q)
    if (tmp.contains(points)) {
      tmp
    } else {
      var left: Circle = null
      var right: Circle = null
      points.foreach(point => {
        val pq = q.subtract(p)
        val cross = pq.cross(point.subtract(p))
        val c = makeCircumCircle(p, q, point)
        if (c != null) {
          if (cross > 0 && (left == null || pq.cross(c.centre.subtract(p)) > pq.cross(left.centre.subtract(p))))
            left = c
          else if (cross < 0 && (right == null || pq.cross(c.centre.subtract(p)) < pq.cross(right.centre.subtract(p))))
            right = c
        }
      })
      if (right == null || left != null && left.radius <= right.radius)
        left
      else
        right
    }
  }


  private def makeDiameterCircle(a: Point, b: Point): Circle =
    Circle(Point((a.x + b.x) / 2, (a.y + b.y) / 2), a.distance(b) / 2)

  private def makeCircumCircle(a: Point, b: Point, c: Point): Circle =
    (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) * 2 match {
      case 0 => null
      case d: Double => {
        val x = (a.normal * (b.y - c.y) + b.normal * (c.y - a.y) * c.normal * (a.y - b.y)) / d
        val y = (a.normal * (c.x - b.x) + b.normal * (a.x - c.x) + c.normal * (b.x - a.x)) / d
        val centre = Point(x, y)
        Circle(centre, centre.distance(a))
      }
  }
}
