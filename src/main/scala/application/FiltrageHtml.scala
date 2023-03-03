package application
import library._

object FiltrageHtml extends library.FiltrageHtml {

  /** A partir d'un document Html h et d'une requête e, dit si le document
    * satisfait l'expression e
    *
    * @param h
    *   le document Html
    * @param e
    *   l'expression
    * @return
    *   true si le document satisfait l'expression e
    */
  def filtreHtml(h: Html, e: Expression): Boolean = {
    e match {
      case Et(e1, e2) => filtreHtml(h, e1) && filtreHtml(h, e2)
      case Ou(e1, e2) => filtreHtml(h, e1) || filtreHtml(h, e2)
      case Mot(cle) =>
        h.toString().toLowerCase().contains(cle.toLowerCase()) && cle
          .length() > 0
    }
  }
}
