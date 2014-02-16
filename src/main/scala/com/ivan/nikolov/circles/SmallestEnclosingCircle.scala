package com.ivan.nikolov.circles

import scala.util.Random

/**
 * Created by volcom on 06/02/14.
 *
 * Adapted with permission from Nayuki's source code
 * (http://nayuki.eigenstate.org/page/smallest-enclosing-circle)
 */
case class SmallestEnclosingCircle(points: Point*) {

  def makeCircle: Circle = makeCircleShuffled(Random.shuffle(points))

  private def makeCircleShuffled(shuffled: Seq[Point]): Circle =
    shuffled.foldLeft((None: Option[Circle], 0))((agg, p) => {
      var currCircle = agg._1
      val index = agg._2
      if (currCircle.isEmpty || !currCircle.get.contains(p)) {
        currCircle = Some(makeCircleOnePoint(shuffled.slice(0, index + 1), p))
      }
      (currCircle, index + 1)
    })._1.getOrElse(null)

  private def makeCircleOnePoint(pointsSeq: Seq[Point], point: Point): Circle =
    pointsSeq.foldLeft((Circle(point, 0), 0))((agg, p) => {
      val index = agg._2
      val circle = if (agg._1.contains(p)) {
        agg._1
      } else agg._1.radius match {
        case 0 => makeDiameterCircle(point, p)
        case _ => makeCircleTwoPoints(pointsSeq.slice(0, index + 1), point, p)
      }
      (circle, index + 1)
    })._1

  private def makeCircleTwoPoints(pointsSeq: Seq[Point], p: Point, q: Point): Circle = {
    val tmp = makeDiameterCircle(p, q)
    if (tmp.contains(pointsSeq)) {
      tmp
    } else {
      val leftAndRight = pointsSeq.foldLeft((None: Option[Circle], None: Option[Circle]))((agg, point) => {
        var left = agg._1
        var right = agg._2
        val pq = q.subtract(p)
        val cross = pq.cross(point.subtract(p))
        val c = makeCircumCircle(p, q, point)
        if (c != null) {
          if (cross > 0 && (left.isEmpty || pq.cross(c.centre.subtract(p)) > pq.cross(left.get.centre.subtract(p))))
            left = Some(c)
          else if (cross < 0 && (right.isEmpty || pq.cross(c.centre.subtract(p)) < pq.cross(right.get.centre.subtract(p))))
            right = Some(c)
        }
        (left, right)
      })

      if (leftAndRight._2.isEmpty || leftAndRight._1.isDefined && leftAndRight._1.get.radius <= leftAndRight._2.get.radius)
        leftAndRight._1.getOrElse(null)
      else
        leftAndRight._2.getOrElse(null)
    }
  }


  private def makeDiameterCircle(a: Point, b: Point): Circle =
    Circle(Point((a.x + b.x) / 2, (a.y + b.y) / 2), a.distance(b) / 2)

  private def makeCircumCircle(a: Point, b: Point, c: Point): Circle =
    (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) * 2 match {
      case 0 => null
      case d: Double => {
        val x = (a.normal * (b.y - c.y) + b.normal * (c.y - a.y) + c.normal * (a.y - b.y)) / d
        val y = (a.normal * (c.x - b.x) + b.normal * (a.x - c.x) + c.normal * (b.x - a.x)) / d
        val centre = Point(x, y)
        Circle(centre, centre.distance(a))
      }
  }
}
