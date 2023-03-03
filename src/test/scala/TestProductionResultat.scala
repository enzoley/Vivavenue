package test

import application.ProductionResultat
import library._
import org.junit.Assert._
import org.junit.Test

class TestProductionResultat {

  val l0: List[(String, String)] = List()
  val l1: List[(String, String)] = List(("Titre", "https://url"))
  val l2: List[(String, String)] =
    List(("Titre1", "https://url1"), ("Titre2", "https://url2"))

  @Test
  def TestResultatVersHtml_l0(): Unit = {
    assertEquals(
      Tag(
        "html", // Nom tag
        List(), // Attributs
        List( // Enfants
          Tag(
            "head", // Nom tag
            List(), // Attributs
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
            List(), // Attributs
            List(
              Tag(
                "header",
                List(),
                List(
                  Tag(
                    "nav",
                    List(
                      (
                        "class",
                        "navbar navbar-expand-lg navbar-dark bg-primary"
                      )
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
              Tag("main", List(("class", "container")), List())
            )
          )
        )
      ),
      ProductionResultat
        .resultatVersHtml(l0)
    )
  }

  @Test
  def TestResultatVersHtml_l1(): Unit = {
    assertEquals(
      Tag(
        "html", // Nom tag
        List(), // Attributs
        List( // Enfants
          Tag(
            "head", // Nom tag
            List(), // Attributs
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
            List(), // Attributs
            List(
              Tag(
                "header",
                List(),
                List(
                  Tag(
                    "nav",
                    List(
                      (
                        "class",
                        "navbar navbar-expand-lg navbar-dark bg-primary"
                      )
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
              Tag(
                "main",
                List(("class", "container")),
                List(
                  Tag("br", List(), List()),
                  Tag(
                    "ul",
                    List(),
                    List(
                      Tag(
                        "li",
                        List(),
                        List(
                          Tag(
                            "a",
                            List(
                              ("href", "https://url"),
                              ("target", "_blank")
                            ),
                            List(Texte("Titre"))
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
      ProductionResultat
        .resultatVersHtml(l1)
    )
  }

  @Test
  def TestResultatVersHtml_l2(): Unit = {
    assertEquals(
      Tag(
        "html", // Nom tag
        List(), // Attributs
        List( // Enfants
          Tag(
            "head", // Nom tag
            List(), // Attributs
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
            List(), // Attributs
            List(
              Tag(
                "header",
                List(),
                List(
                  Tag(
                    "nav",
                    List(
                      (
                        "class",
                        "navbar navbar-expand-lg navbar-dark bg-primary"
                      )
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
              Tag(
                "main",
                List(("class", "container")),
                List(
                  Tag("br", List(), List()),
                  Tag(
                    "ul",
                    List(),
                    List(
                      Tag(
                        "li",
                        List(),
                        List(
                          Tag(
                            "a",
                            List(
                              ("href", "https://url1"),
                              ("target", "_blank")
                            ),
                            List(Texte("Titre1"))
                          )
                        )
                      ),
                      Tag(
                        "li",
                        List(),
                        List(
                          Tag(
                            "a",
                            List(
                              ("href", "https://url2"),
                              ("target", "_blank")
                            ),
                            List(Texte("Titre2"))
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
      ProductionResultat
        .resultatVersHtml(l2)
    )
  }
}
