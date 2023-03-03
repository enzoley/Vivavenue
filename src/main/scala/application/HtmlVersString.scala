package application
import library._

object HtmlVersString extends library.HtmlVersString {

  /** Produit la chaîne de caractère correspondant à un document Html
    *
    * @param h
    *   le document Html
    * @return
    *   la chaîne de caractère représentant h
    */
  def traduire(h: Html): String = {
    var res: String = ""
    h match {
      case Texte(content)      => res = res + content;
      case Tag(name, Nil, Nil) => res = res + "<" + name + "/>";
      case Tag(name, Nil, children) =>
        res = res + "<" + name + ">";
        for (ident <- children) { res = res + traduire(ident); }
        res = res + "</" + name + ">";
      case Tag(name, attribut, Nil) =>
        attribut match {
          case ("charset", _) :: tl =>
            res = res + "<" + name + traduireAttribut(attribut) + "/>";
          case ("rel", _) :: tl =>
            res = res + "<" + name + traduireAttribut(attribut) + "/>";
          case _ =>
            res = res + "<" + name + traduireAttribut(
              attribut
            ) + "</" + name + ">";
        }
      case Tag(name, attributs, children) =>
        res = res + "<" + name + traduireAttribut(attributs) + ">"
        for (ident <- children) {
          res = res + traduire(ident);
        }
        res = res + "</" + name + ">";
    }
    res;
  }

  /** Permet de traduire les attributs en chaine de caractère
    *
    * @param attribut
    * @return
    *   la chaîne de caractère représentant les attributs en html
    */
  def traduireAttribut(attribut: List[(String, String)]): String = {
    var res: String = ""
    attribut match {
      case (a, b) :: tl =>
        res + " " + a + "=\"" + b + "\"" + traduireAttribut(tl)
      case _ => ""
    }
  }
}
