package application
import library._

object AnalysePage extends library.AnalysePage {
  val objFiltrageUrls = FiltrageURLs
  val objFiltrageHtml = FiltrageHtml

  /** A partir d'une URL de requête sur le site de référence et d'une expression
    * exp, retourne de pages issues de la requête et satisfaisant l'expression.
    *
    * @param url
    *   l'URL de la requête sur le site de référence
    * @param exp
    *   l'expression à vérifier sur les pages trouvées
    * @return
    *   la liste des couples (titre,ref) où ref est l'URL d'une page
    *   satisfaisant l'expression et titre est son titre.
    */
  def resultats(url: String, exp: Expression): List[(String, String)] = {
    var resultats: List[(String, String)] = List()
    val html: Html = OutilsWebObjet.obtenirHtml(url)
    val lURLs: List[String] = objFiltrageUrls.filtreAnnonce(html)
    for (u <- lURLs) {
      val html: Html = OutilsWebObjet.obtenirHtml(u)
      if (objFiltrageHtml.filtreHtml(html, exp)) {
        resultats = resultats ++ List((recupererTitre(html), u))
      }
    }
    resultats
  }

  /** fonction auxiliaire Permet d'obtenir le titre d'un document de type Html
    *
    * @param html
    * @return
    *   le titre d'un document de type Html
    */
  private def recupererTitre(html: Html): String = {
    html match {
      case Tag(
            "h1",
            List(
              (
                "class",
                "kiwii-font-xlarge kiwii-margin-none kiwii-font-weight-semibold"
              )
            ),
            List(Texte(title))
          ) => {
        title
      }
      case Tag(name, attributes, children) => {
        var suite: String = ""
        for (c <- children) {
          suite = suite + recupererTitre(c)
        }
        suite
      }
      case _ => ""
    }
  }
}
