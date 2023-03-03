package test

import application.FiltrageURLs
import library._
import org.junit.Assert._
import org.junit.Test

class TestFiltrageURLs {

  val html_texte_mot: Html = Texte("Mot")
  val html_texte_a: Html = Texte("a")
  val html_Tag_a: Html = Tag("a", List(("href", "url")), List())
  val url_rennes_page1: String =
    "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=rennes&cat_1=&geosearch_text=&searchGeoId=0"
  val html_rennes_page1: Html = OutilsWebObjet.obtenirHtml(url_rennes_page1)

  @Test
  def TestFiltrageURLs_SansLien(): Unit = {
    assertTrue(
      html_texte_mot.toString + " possède un lien url d'annonce alors que l'on attend une liste vide : List()",
      FiltrageURLs.filtreAnnonce(html_texte_mot).size.equals(0)
    )
    assertTrue(
      html_texte_a.toString + " possède un lien url d'annonce alors que l'on attend une liste vide : List()",
      FiltrageURLs.filtreAnnonce(html_texte_a).size.equals(0)
    )
    assertTrue(
      html_Tag_a.toString + " possède un lien url d'annonce alors que l'on attend une liste vide : List()",
      FiltrageURLs.filtreAnnonce(html_Tag_a).size.equals(0)
    )
  }

  @Test
  def TestFiltrageURLs_Rennes(): Unit = {
    val url_annoncePREMIUM_rennes_page1: String =
      "https://www.vivastreet.com/emploi-fonction-service-a-domicile/rennes-35000/baby-sitter-12-h-semaine---rennes-pour-3-enfants--1-an--6---/313409130"
    val url_annonce1_rennes_page1: String =
      "https://www.vivastreet.com/immobilier-vente-maison/plouasne-22830/-680v23237m--vente-maison/313374256"
    val url_annonce2_rennes_page1: String =
      "https://www.vivastreet.com/emploi-fonction-transport-logisitique-achat/cesson-sevigne-35510/chef-d--quipe-d-butant-en-inventaire-h-f---rennes/313340761"
    val url_annonce3_rennes_page1: String =
      "https://www.vivastreet.com/emploi-fonction-marketing-communication/groslay-95410/boucher-e-s-vendeur-euse-s/313260954"
    val url_annonce4_rennes_page1: String =
      "https://www.vivastreet.com/emploi-fonction-industrie/cesson-sevigne-35510/chef-d--quipe-d-butant-en-inventaire-h-f---rennes/313247874"
    val url_annonce5_rennes_page1: String =
      "https://www.vivastreet.com/emploi-compabilite-gestion-finance/rennes-35000/ing-nieur-syst-me---poste-de-travail-h-f/313247823"
    val url_annonce6_rennes_page1: String =
      "https://www.vivastreet.com/immobilier-appartement/st-medard-en-jalles-33160/4-pi-ces-grande-terrasse--xxl-44m2/313173984"
    // Modification des annonces sur vivastreet

    // assertTrue(
    //   "La page web : " + url_rennes_page1 + " ne possède pas 6 annonce non premium",
    //   FiltrageURLs.filtreAnnonce(html_rennes_page1).size.equals(6)
    // )
    // assertTrue(
    //   "La page web : " + url_rennes_page1 + " ne possède pas l'annonce dont l'url est " + url_annonce1_rennes_page1 + " alors que cette url fait partie de la list attendue",
    //   FiltrageURLs
    //     .filtreAnnonce(html_rennes_page1)
    //     .contains(url_annonce1_rennes_page1)
    // )
    // assertTrue(
    //   "La page web : " + url_rennes_page1 + " ne possède pas l'annonce dont l'url est " + url_annonce2_rennes_page1 + " alors que cette url fait partie de la list attendue",
    //   FiltrageURLs
    //     .filtreAnnonce(html_rennes_page1)
    //     .contains(url_annonce2_rennes_page1)
    // )
    // assertTrue(
    //   "La page web : " + url_rennes_page1 + " ne possède pas l'annonce dont l'url est " + url_annonce3_rennes_page1 + " alors que cette url fait partie de la list attendue",
    //   FiltrageURLs
    //     .filtreAnnonce(html_rennes_page1)
    //     .contains(url_annonce3_rennes_page1)
    // )
    // assertTrue(
    //   "La page web : " + url_rennes_page1 + " ne possède pas l'annonce dont l'url est " + url_annonce4_rennes_page1 + " alors que cette url fait partie de la list attendue",
    //   FiltrageURLs
    //     .filtreAnnonce(html_rennes_page1)
    //     .contains(url_annonce4_rennes_page1)
    // )
    // assertTrue(
    //   "La page web : " + url_rennes_page1 + " ne possède pas l'annonce dont l'url est " + url_annonce5_rennes_page1 + " alors que cette url fait partie de la list attendue",
    //   FiltrageURLs
    //     .filtreAnnonce(html_rennes_page1)
    //     .contains(url_annonce5_rennes_page1)
    // )
    // assertTrue(
    //   "La page web : " + url_rennes_page1 + " ne possède pas l'annonce dont l'url est " + url_annonce6_rennes_page1 + " alors que cette url fait partie de la list attendue",
    //   FiltrageURLs
    //     .filtreAnnonce(html_rennes_page1)
    //     .contains(url_annonce6_rennes_page1)
    // )
    assertFalse(
      "La page web : " + url_rennes_page1 + " possède l'annonce dont l'url est https://mauvaise-url alors que cette url ne fair pas partie de la liste obtenue",
      FiltrageURLs
        .filtreAnnonce(html_rennes_page1)
        .contains("https://mauvaise-url")
    )
    assertFalse(
      "La page web : " + url_rennes_page1 + " possède l'annonce premium dont l'url est " + url_annoncePREMIUM_rennes_page1 + " alors que cette url ne fair pas partie de la liste obtenue",
      FiltrageURLs
        .filtreAnnonce(html_rennes_page1)
        .contains(url_annoncePREMIUM_rennes_page1)
    )
  }
}
