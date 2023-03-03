package application
import library._
import java.io.File
import java.io.FileWriter
import java.awt.Desktop

object Application extends App {

  /** Transforme une expression en une liste de String qui correspond au
    * différentes recherche de l'utilisateur exprimer par exp.
    *
    * @param exp
    *   une Expression qui correspond à la recherche de l'utilisateur
    * @return
    *   List[String] : Une liste de String des différentes recherches à
    *   effectuer
    */
  private def getKeywords(exp: Expression): List[String] = {
    exp match {
      case Et(e1, e2) => {
        var res: List[String] = List()
        for (element_e1 <- getKeywords(e1)) {
          for (element_e2 <- getKeywords(e2)) {
            res = res ++ List(element_e1 + "+" + element_e2)
          }
        }
        res
      }
      case Ou(e1, e2) => getKeywords(e1) ++ getKeywords(e2)
      case Mot(w)     => List(w)
    }
  }

  /** A partir du nombre d'annonce on obtient le nombre de page
    *
    * @param nbAnnonce
    *   Int : le nombre d'annonce
    *
    * @return
    *   Int : le nombre de page pour n annonces
    */
  private def getNbPages(nbAnnonce: Int): Int = {
    math.ceil(nbAnnonce / 25.0).toInt
  }

  /** A partir de l'url de recherche, on obtient le nombre d'annonces
    * corespondantes
    *
    * @param url
    *   l'URL de la requête sur le site de référence
    *
    * @return
    *   Int : le nombre d'annonce
    */
  private def getNbAnnonce(url: String): Int = {
    auxiliaire_getNbAnnonce(
      OutilsWebObjet
        .obtenirHtml(url)
    )
  }

  /** A partir de l'html de recherche, on obtient le nombre d'annonces
    * corespondantes
    *
    * @param html
    *   le document Html
    *
    * @return
    *   Int : le nombre d'annonce
    */
  private def auxiliaire_getNbAnnonce(html: Html): Int = {
    html match {
      case Tag(
            "div",
            List(("class", "results-summary")),
            Texte(nbAnnonce) :: reste
          ) => {
        nbAnnonce.replaceAll("[^0-9]", "").toInt
      }
      case Tag(name, attributes, children) => {
        var suite: Int = 0
        for (c <- children) {
          suite = suite + auxiliaire_getNbAnnonce(c)
        }
        suite
      }
      case _ => 0
    }
  }

  /** Permet d'obtenir le nombre d'annonce par page
    *
    * @param numeroPageCourante
    * @param nbPages
    * @param nbAnnonces
    */
  private def getNbAnnonceParPage(
      numeroPageCourante: Int,
      nbPages: Int,
      nbAnnonces: Int
  ): Int = {
    (numeroPageCourante, nbPages, nbAnnonces) match {
      case (_, _, 0)          => 0
      case (_, 1, nbAnnonces) => nbAnnonces
      case (i, j, nbAnnonces) => {
        if (i == j) {
          nbAnnonces - (i - 1) * 25
        } else {
          25
        }
      }
      case _ => 25

    }
  }

  /** A partir de l'expression on obtient tous les urls corespondants a
    * l'expression de recherche, c'est-à-dire qur nous décomposont l'expression
    * en plusieurs similaires, nous obtenons plusieurs urls avec les bons mots
    * clef. Nous ajoutons autant d'url qu'il y a de page pour chaque keywords
    *
    * @param exp
    *   une Expression qui correspond à la recherche de l'utilisateur
    *
    * @return
    *   List[(String, Int)] : toutes les url de recherche corespondants a
    *   l'expression avec leur nombre d'annonces
    */
  private def getUrls(exp: Expression): List[(String, Int)] = {
    var res: List[(String, Int)] = List()
    for (keywords <- getKeywords(exp)) {
      var url: String =
        "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=" + keywords + "&cat_1=&geosearch_text=&searchGeoId=0"
      var nbAnnonce: Int = getNbAnnonce(url)
      var nbPages: Int = getNbPages(nbAnnonce)
      res = res ++ List((url, getNbAnnonceParPage(1, nbPages, nbAnnonce)))
      for (numeroPage <- 2 to nbPages) {
        var url: String =
          "https://search.vivastreet.com/annonces/fr/t+" + numeroPage + "?lb=new&search=1&start_field=1&keywords=" + keywords + "&cat_1=&geosearch_text=&searchGeoId=0"
        res = res ++ List(
          (
            url,
            getNbAnnonceParPage(numeroPage, nbPages, nbAnnonce)
          )
        )
      }
    }
    res.distinct
  }

  /** Calcul le nombre total d'annonce
    *
    * @param urls
    */
  private def sommeAnnonces(urls: List[(String, Int)]): Int = {
    urls match {
      case head :: reste => head._2 + sommeAnnonces(reste)
      case Nil           => 0
    }
  }

  /** Affiche n espace
    *
    * @param n
    */
  def espace(n: Int): String = {
    if (n <= 0)
      ""
    else
      " " + espace(
        n - 1
      )
  }

  /** Affichage de la progression
    *
    * @param nbAnnonce
    * @param nbAnnonceTotal
    */
  private def progression(
      nbAnnonce: Int,
      nbAnnonceTotal: Int
  ): Unit = {
    if (nbAnnonceTotal > 0) {
      val nbAnnonceRestant: Int = (nbAnnonceTotal - nbAnnonce)
      val pourcentage: Int = (
        nbAnnonce * 100 / nbAnnonceTotal
      ).toInt
      println(
        espace(
          1 + nbAnnonceTotal.toString.length - nbAnnonceRestant.toString.length
        ) + nbAnnonceRestant.toString + " annonces restantes | " + pourcentage.toString + espace(
          3 - pourcentage.toString.length
        ) + "%"
      )
    }
  }

  /** A partir d'une list de mots clef, construir les URLs de requête sur le
    * site de référence et retourner les couples (titre,url) satisfaisant
    * l'expression sans doublon.
    *
    * @param exp
    *   une Expression qui correspond à la recherche de l'utilisateur
    * @return
    *   List[(String, String)] : la liste des couples (titre,url) satisfaisant
    *   l'expression exp
    */
  def getResultats(exp: Expression): List[(String, String)] = {
    var res: List[(String, String)] = List()
    val urls: List[(String, Int)] = getUrls(exp)
    var nbAnnonces: Int = 0
    for (url <- urls) {
      progression(
        nbAnnonces,
        sommeAnnonces(urls)
      )
      res = res ++ AnalysePage.resultats(url._1, exp)
      nbAnnonces = nbAnnonces + url._2
    }
    res.distinct.reverse
  }

  val exp: Expression = ParserExpression.lireExpression
  var res: String = HtmlVersString.traduire(
    ProductionResultat.resultatVersHtml(getResultats(exp))
  )

  val file = new File("public/Index.html")
  val writer: FileWriter = new FileWriter(file)
  try {
    writer.write(res)
  } finally writer.close()

  if (
    Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(
      Desktop.Action.BROWSE
    )
  ) {
    Desktop.getDesktop.browse(file.toURI)
  }
}
