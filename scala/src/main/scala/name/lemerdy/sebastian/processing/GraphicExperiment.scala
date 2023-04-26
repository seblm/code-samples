package name.lemerdy.sebastian.processing

import processing.core.PApplet

import scala.collection.mutable
import scala.util.Random

class GraphicExperiment extends PApplet:

  private case class Signal(initialX: Int, y: Int, color: Int):
    var x: Int = initialX
    def increment(): Unit =
      x = x + 1

  private val signals: mutable.ArrayBuffer[Signal] = mutable.ArrayBuffer.empty
  private var y: Int = 0

  override def settings(): Unit =
    size(640, 320)

  override def draw(): Unit =
    background(color(255))
    stroke(color(0))
    line(width / 2f, 0, width / 2f, height.toFloat)
    if (mousePressed)
      signals.append(Signal(width / 2, y, color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))))
      y = y + 1
    signals.foreach { signal =>
      stroke(signal.color)
      point(signal.x.toFloat, signal.y.toFloat)
      signal.increment()
    }
    signals.zipWithIndex.filter(_._1.x >= width).foreach((_, b) => signals.remove(b))

@main def launch(): Unit = PApplet.main(classOf[GraphicExperiment])
