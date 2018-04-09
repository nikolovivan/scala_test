package com.ivan.nikolov.circles

/**
 * Adapted with permission from Nayuki's source code
 * (http://nayuki.eigenstate.org/page/smallest-enclosing-circle)
 */
case class Circle(centre: Point, radius: Double) {
  val EPSILON = 1e-12

  def contains(point: Point): Boolean =
    centre.distance(point) <= radius + EPSILON

  def contains(points: Seq[Point]): Boolean =
    points.forall(p => contains(p))

  override def toString: String = "Centre: %s\nRadius: %f".format(centre.toString, radius)
}
