package test

import application.HtmlVersString
import library._
import org.junit.Assert._
import org.junit.Test

class TestHtmlVersString {
  val exemple0 = new Texte("TEST")
  val exemple1 = Tag("", List(), List())
  val exemple2 = Tag(
    "html",
    List(("href", "http://www.irisa.fr")),
    List(Texte("yes"), Tag("meta", List(("charset", "utf-8")), List()))
  )
  val exemple3 = Tag(
    "html",
    List(),
    List(
      Tag(
        "head",
        List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page")))
        )
      ),
      Tag(
        "body",
        List(),
        List(
          Tag(
            "center",
            List(),
            List(
              Tag(
                "a",
                List(("href", "http://www.irisa.fr")),
                List(Texte("Lien"))
              )
            )
          )
        )
      )
    )
  )

  @Test
  def test1(): Unit = {
    assertTrue("test1", HtmlVersString.traduire(exemple0).equals("TEST"))
  }
  @Test
  def test2(): Unit = {
    assertTrue("test2", HtmlVersString.traduire(exemple1).equals("<></>"))
  }

  @Test
  def test3(): Unit = {
    val res: String =
      """<html><head><meta charset="utf-8"/><title>My Page</title></head><body><center><a href="http://www.irisa.fr">Lien</a></center></body></html>"""
    assertTrue("test3", HtmlVersString.traduire(exemple3).equals(res))
  }
  @Test
  def test4(): Unit = {
    val res: String =
      """<html href="http://www.irisa.fr">yes<meta charset="utf-8"/></html>"""
    assertTrue("test4", HtmlVersString.traduire(exemple2).equals(res))
  }
}
