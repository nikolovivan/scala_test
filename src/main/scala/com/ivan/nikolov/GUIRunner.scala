package com.ivan.nikolov

import scala.swing._
import javax.swing.JPanel
import scala.swing.event.{WindowClosing, MouseClicked, ButtonClicked}
import com.ivan.nikolov.circles.{SmallestEnclosingCircle, Circle}
import java.awt.Color
import java.awt.geom.{Rectangle2D, Ellipse2D}

/**
 * A runner class for a GUI application using scala swing.
 *
 * Created by volcom on 16/02/14.
 */
object GUIRunner extends SimpleSwingApplication {
  val frameWidth = 600
  val frameHeight = 600
  val frameDimensions = new Dimension(frameWidth, frameHeight)

  val points: collection.mutable.MutableList[com.ivan.nikolov.circles.Point] = collection.mutable.MutableList()

  val btnReset = new Button {
    text = "Reset"
  }

  val btnRandom = new Button {
    text = "Random"
  }

  val panButtons = new GridPanel(1, 2) {
    contents ++= Seq(btnReset, btnRandom)
  }

  val panCirclePanel = new Panel {
    listenTo(mouse.clicks, btnRandom, btnReset)
    reactions += {
      case me: MouseClicked => {
        val point = com.ivan.nikolov.circles.Point(me.point.getX, me.point.getY)
        points += point
        repaint()
      }
      case ButtonClicked(`btnReset`) => {
        points.clear
        repaint()
      }
      case ButtonClicked(`btnRandom`) => println("Random clicked.")
    }

    override def paint(g: Graphics2D) {
      super.paint(g)
      val circle = findSmallestCircle()
      if (circle != null) {
        // draw it
        drawCircle(g, Color.CYAN, circle)
      }
      points.foreach(point => drawPoint(g, point))
    }
  }

  def findSmallestCircle(): Circle =
    SmallestEnclosingCircle(points: _*).makeCircle

  def drawPoint(g: Graphics2D, point: com.ivan.nikolov.circles.Point) {
    drawCircle(g, Color.BLACK, Circle(point, 4))
  }

  def drawCircle(g: Graphics2D, color: Color, circle: Circle) {
    g.setColor(color)
    val x = circle.centre.x - circle.radius
    val y = circle.centre.y - circle.radius
    val ellipse = new Ellipse2D.Double(x, y, circle.radius * 2, circle.radius * 2)
    g.fill(ellipse)
  }

  override def top: Frame = new MainFrame {
    title = "Smallest Enclosing Circle app"
    contents = new BorderPanel {
      import scala.swing.BorderPanel.Position._
      layout(panCirclePanel) = Center
      layout(panButtons) = South
      minimumSize = frameDimensions
      preferredSize = frameDimensions
    }

    listenTo(this)
    reactions += {
      case WindowClosing(e) => {
        println("Exiting.")
        sys.exit(0)
      }
    }
  }
}
