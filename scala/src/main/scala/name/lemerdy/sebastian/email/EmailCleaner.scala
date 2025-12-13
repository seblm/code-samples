package name.lemerdy.sebastian.email

import scala.io.Source
import scala.util.Using

object EmailCleaner:

  @main def main(): Unit =
    Using(Source.fromFile("~/Downloads/fournisseur 121125.txt")): source =>
      println(source.getLines().flatMap(cleanEmail).distinct.mkString("\n"))

  private val nameWithEmail = """.+<(.+)>""".r

  private def cleanEmail: String => Seq[String] =
    case line if line.contains("\u00A0") => cleanEmail(line.filterNot(_ == '\u00A0'))
    case line if line.contains(";")      => line.split(";").flatMap(cleanEmail)
    case line if line.contains("/")      => line.split("/").flatMap(cleanEmail)
    case line if line.contains("\t")     => line.split("\t").flatMap(cleanEmail)
    case line if line.contains("\"")     => cleanEmail(line.filterNot(_ == '"'))
    case line if line.contains("'")      => cleanEmail(line.filterNot(_ == '\''))
    case nameWithEmail(email)            => cleanEmail(email)
    case email if email.contains("@")    => Seq(email.trim())
    case ""                              => Seq.empty
    case line                            => println(s"UNEXPECTED $line"); Seq.empty
