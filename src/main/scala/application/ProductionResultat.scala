package application
import library._

object ProductionResultat extends library.ProductionResultat {

  val noAttributes: List[(String, String)] = Nil

  /** A partir d'une liste de couples (titre,URL), produit une list de tag
    * représentant tous les liens cliquables
    *
    * @param l
    *   la liste des couples solution (titre,URL)
    * @return
    *   une liste de Tag correspondant aux liens cliquables
    */
  private def liens(l: List[(String, String)]): List[Tag] = {
    var res: List[Tag] = List()
    var liens: List[Tag] = List()
    if (!l.isEmpty) {
      for (page <- l) {
        liens = liens ++
          List(
            Tag(
              "li",
              noAttributes,
              List(
                Tag(
                  "a",
                  List(("href", page._2), ("target", "_blank")),
                  List(Texte(page._1))
                )
              )
            )
          )
      }
      res = List(
        Tag("br", noAttributes, List()),
        Tag(
          "ul",
          noAttributes,
          liens
        )
      )
    }
    res
  }

  /** A partir d'une liste de couples (titre,URL), produit un document Html, qui
    * liste les solutions sous la forme de liens cliquables
    *
    * @param l
    *   la liste des couples solution (titre,URL)
    * @return
    *   le document Html listant les solutions
    */
  def resultatVersHtml(l: List[(String, String)]): Html = {

    val html: Html = Tag(
      "html", // Nom tag
      noAttributes, // Attributs
      List( // Enfants
        Tag(
          "head", // Nom tag
          noAttributes, // Attributs
          List( // Enfants
            Tag("meta", List(("charset", "utf-8")), List()),
            Tag(
              "link",
              List(
                ("rel", "stylesheet"),
                ("type", "text/css"),
                ("href", "styles/bootstrap.min.css")
              ),
              List()
            ),
            Tag(
              "title",
              List(),
              List(Texte("Les liens des annonces - Vivavenue"))
            )
          )
        ),
        Tag(
          "body", // Nom tag
          noAttributes, // Attributs
          List(
            Tag(
              "header",
              noAttributes,
              List(
                Tag(
                  "nav",
                  List(
                    ("class", "navbar navbar-expand-lg navbar-dark bg-primary")
                  ),
                  List(
                    Tag(
                      "div",
                      List(("class", "container-fluid")),
                      List(
                        Tag(
                          "a",
                          List(
                            ("class", "navbar-brand"),
                            ("href", "Index.html")
                          ),
                          List(Texte("Vivavenue"))
                        ),
                        Tag(
                          "div",
                          List(
                            ("class", "collapse navbar-collapse"),
                            ("id", "navbarColor01")
                          ),
                          List(
                            Tag(
                              "ul",
                              List(("class", "navbar-nav me-auto")),
                              List(
                                Tag(
                                  "li",
                                  List(("class", "nav-item")),
                                  List(
                                    Tag(
                                      "a",
                                      List(
                                        ("class", "nav-link active"),
                                        ("href", "Index.html")
                                      ),
                                      List(
                                        Texte("Les liens des annonces"),
                                        Tag(
                                          "span",
                                          List(("class", "visually-hidden")),
                                          List(Texte("(current)"))
                                        )
                                      )
                                    )
                                  )
                                ),
                                Tag(
                                  "li",
                                  List(("class", "nav-item")),
                                  List(
                                    Tag(
                                      "a",
                                      List(
                                        ("class", "nav-link"),
                                        ("href", "About.html")
                                      ),
                                      List(
                                        Texte("A propos")
                                      )
                                    )
                                  )
                                )
                              )
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            ),
            Tag("main", List(("class", "container")), liens(l))
          )
        )
      )
    )
    html
  }
}
