package investigate.scala

import java.util.Date

/**
 * Created by LiuPin on 2015/8/17.
 */
object XmlProcessing extends App{

  val page =
    <html>
      <head>
        <title>Hello XHTML world</title>
      </head>
      <body>
        <h1>Hello world</h1>
        <p><a href="scala-lang.org">Scala</a> talks XHTML</p>
      </body>
    </html>

  println(page.toString)

  val df = java.text.DateFormat.getDateInstance
  val dateString = df.format(new Date())
  def theDate(name: String) =
    <dateMsg addressedTo={name}>
      Hello, {name}! Today is {dateString}
    </dateMsg>

  println(theDate("John Doe").toString)
}
