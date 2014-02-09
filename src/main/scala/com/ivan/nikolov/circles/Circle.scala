package com.ivan.nikolov.circles

/**
 * Created by volcom on 06/02/14.
 */
case class Circle(val centre: Point, val radius: Double) {
  val EPSILON = 1e-12

  def contains(point: Point): Boolean =
    centre.distance(point) <= radius + EPSILON

  def contains(points: Seq[Point]): Boolean =
    !points.exists(p => !contains(p))

  override def toString = "Centre: %s\nRadius: %f".format(centre.toString, radius)
}
