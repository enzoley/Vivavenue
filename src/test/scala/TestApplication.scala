package test

import application._
import library._
import org.junit.Assert._
import org.junit.Test

class TestApplication {
  // Expression n°1 : developpeur and (rennes or nantes) and (python or java)
  val exp_1: Expression = Et(
    Mot("developpeur"),
    Et(Ou(Mot("rennes"), Mot("nantes")), Ou(Mot("python"), Mot("java")))
  )
  val html_1: String =
    "<html><head><meta charset=\"utf-8\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"styles/bootstrap.min.css\"</link><title>Les liens des annonces - Vivavenue</title></head><body><header><nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\"><div class=\"container-fluid\"><a class=\"navbar-brand\" href=\"Index.html\">Vivavenue</a><div class=\"collapse navbar-collapse\" id=\"navbarColor01\"><ul class=\"navbar-nav me-auto\"><li class=\"nav-item\"><a class=\"nav-link active\" href=\"Index.html\">Les liens des annonces<span class=\"visually-hidden\">(current)</span></a></li><li class=\"nav-item\"><a class=\"nav-link\" href=\"About.html\">A propos</a></li></ul></div></div></nav></header><main class=\"container\"><br></br><ul><li><a href=\"https://www.vivastreet.com/emploi-fonction-informatique-internet-telecom/nantes-44100/d-veloppeur-fullstack---salesforce--f-h/277974165\" target=\"_blank\"> Développeur FullStack Salesforce F H</a></li><li><a href=\"https://www.vivastreet.com/emploi-fonction-informatique-internet-telecom/lyon-69006/d-veloppeur-java-f-h/217310819\" target=\"_blank\"> Développeur Java FH</a></li><li><a href=\"https://www.vivastreet.com/emploi-fonction-informatique-internet-telecom/rennes-35000/d-veloppeur-full-stack/260164964\" target=\"_blank\"> Développeur Full Stack</a></li></ul></main></body></html>"

  // Expression n°2 : rennes and (nantes and (avion and (mer and papier)))
  val exp_2: Expression =
    Et(
      Mot("rennes"),
      Et(Mot("nantes"), Et(Mot("avion"), Et(Mot("mer"), Mot("papier"))))
    )
  val html_2: String =
    "<html><head><meta charset=\"utf-8\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"styles/bootstrap.min.css\"</link><title>Les liens des annonces - Vivavenue</title></head><body><header><nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\"><div class=\"container-fluid\"><a class=\"navbar-brand\" href=\"Index.html\">Vivavenue</a><div class=\"collapse navbar-collapse\" id=\"navbarColor01\"><ul class=\"navbar-nav me-auto\"><li class=\"nav-item\"><a class=\"nav-link active\" href=\"Index.html\">Les liens des annonces<span class=\"visually-hidden\">(current)</span></a></li><li class=\"nav-item\"><a class=\"nav-link\" href=\"About.html\">A propos</a></li></ul></div></div></nav></header><main class=\"container\"</main></body></html>"

  @Test
  def TestApplication_Expression_1(): Unit = {
    assertTrue(
      "Page Resulat.html mal générer pour l'expression : developpeur and (rennes or nantes) and (python or java)",
      (HtmlVersString
        .traduire(
          ProductionResultat
            .resultatVersHtml(Application.getResultats(exp_1))
        )
        .equals(html_1))
    )
  }
  @Test
  def TestApplication_Expression_2(): Unit = {
    assertTrue(
      "Page Resulat.html mal générer pour l'expression : rennes and (nantes and (avion and (mer and papier)))",
      (HtmlVersString
        .traduire(
          ProductionResultat
            .resultatVersHtml(Application.getResultats(exp_2))
        )
        .equals(html_2))
    )
  }
}
