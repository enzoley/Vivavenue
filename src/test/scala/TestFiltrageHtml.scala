package test

import application.FiltrageHtml
import library._
import org.junit.Assert._
import org.junit.Test

class TestFiltrageHtml {

  val h0: Html = Tag(
    "html",
    List(),
    List(
      Tag(
        "head",
        List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page - TP345")))
        )
      ),
      Tag(
        "body",
        List(),
        List(
          Tag(
            "center",
            List(),
            List()
          )
        )
      )
    )
  )
  val h1: Html = Tag(
    "html",
    List(),
    List(
      Tag(
        "head",
        List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page - TP345")))
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
                List(("href", "https://url")),
                List(Texte("Titre"))
              )
            )
          )
        )
      )
    )
  )

  @Test
  def TestFiltreHtml_h0(): Unit = {
    assertFalse(
      h0.toString + " contient \"url\", alors que c'est faux",
      FiltrageHtml.filtreHtml(h0, Mot("url"))
    )

    assertFalse(
      h0.toString + " contient \"\", alors que c'est faux",
      FiltrageHtml.filtreHtml(h0, Mot(""))
    )

    assertFalse(
      h0.toString + " contient \"url et arbre\", alors que c'est faux",
      FiltrageHtml.filtreHtml(h0, Et(Mot("url"), Mot("arbre")))
    )
  }

  @Test
  def TestFiltreHtml_h1(): Unit = {
    assertTrue(
      h1.toString + " ne contient pas \"url\", alors que c'est vrai",
      FiltrageHtml.filtreHtml(h1, Mot("url"))
    )

    assertFalse(
      h1.toString + " contient \"\", alors que c'est faux",
      FiltrageHtml.filtreHtml(h1, Mot(""))
    )

    assertFalse(
      h1.toString + " contient \"url et arbre\", alors que c'est faux",
      FiltrageHtml.filtreHtml(h1, Et(Mot("url"), Mot("arbre")))
    )

    assertFalse(
      h1.toString + " contient \"url et (arbre et url)\", alors que c'est faux",
      FiltrageHtml.filtreHtml(
        h1,
        Et(Mot("url"), Et(Mot("arbre"), Mot("url")))
      )
    )

    assertTrue(
      h1.toString + " ne contient pas \"url ou arbre\", alors que c'est vrai",
      FiltrageHtml.filtreHtml(h1, Ou(Mot("url"), Mot("arbre")))
    )
  }
}
