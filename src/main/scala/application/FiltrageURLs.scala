package application
import library._

object FiltrageURLs extends library.FiltrageURLs {

  /** A partir d'un document Html h, rend la liste des URLs accessibles à partir
    * de h (ces URLs sont des hyperliens h) tels que ces URLs sont tous des URLs
    * d'annonces du site de référence
    *
    * @param h
    *   le document Html
    * @return
    *   la liste des URLs d'annonces contenues dans h
    */
  def filtreAnnonce(h: Html): List[String] = {
    getUrls(getTagDivClad__title(h))
  }

  /** @param html
    * @return
    *   une liste html
    */
  private def getTagDivClad__title(html: Html): List[Html] = {
    html match {
      case Tag(
            "div",
            List(("class", "clad__title")),
            children
          ) =>
        children
      case Tag(name, attributes, children) => {
        var suite: List[Html] = Nil

        if (!isPremium(children)) {
          for (c <- children) {
            suite = suite ++ getTagDivClad__title(c)
          }
        }
        suite
      }
      case _ => Nil
    }
  }

  /** Permet de determiner si une liste de type Html correspond à une annonce
    * premium sur vivastreat
    *
    * @param l
    *   List[Html] : les enfants d'un tag de type Html (= tous les elements
    *   préesents dans une balise Html)
    * @return
    *   Boolean : True si cela corespond à une annonce premium
    */
  private def isPremium(l: List[Html]): Boolean = {
    l match {
      case head :: next =>
        head match {
          case Tag(
                "div",
                List(("class", "clad__labels")),
                children
              ) =>
            true
          case _ => isPremium(next)

        }
      case _ => false
    }
  }

  /** @param l
    *   List[Html] : les enfants d'un tag de type Html (= tous les elements
    *   préesents dans une balise Html)
    * @return
    *   la liste des urls
    */
  private def getUrls(l: List[Html]): List[String] = {
    l match {
      case head :: next =>
        head match {
          case Tag("a", ("href", url) :: reste, _) => {
            url :: getUrls(next)
          }
          case _ => getUrls(next)
        }
      case _ => Nil

    }
  }
}
