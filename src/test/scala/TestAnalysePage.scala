package test

import application.AnalysePage
import library._
import org.junit.Assert._
import org.junit.Test

class TestAnalysePage {

  val url_rennes_page1: String =
    "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=rennes&cat_1=&geosearch_text=&searchGeoId=0"
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

  val exp_vide = Mot(
    ""
  ) // objFiltrageHtml.filtreHtml(OutilsWebObjet.obtenirHtml(url_rennes_page1), exp_vide) = false
  val exp_rennes = Mot(
    "Rennes"
  ) // objFiltrageHtml.filtreHtml(OutilsWebObjet.obtenirHtml(url_rennes_page1), exp_rennes) = true

  @Test
  def TestAnalysePage_ListVide(): Unit = {
    assertTrue(
      "La list de resultats de l'url " + url_rennes_page1 + " avec l'expresion " + exp_vide + " possède un ou plusieurs couple(s) (url, titre) d'annonce(s) alors que l'on attend une liste vide : List()",
      AnalysePage.resultats(url_rennes_page1, exp_vide).equals(List())
    )
  }

  @Test
  def TestAnalysePage_ListNonVide(): Unit = {
    val couple1: (String, String) = (
      " (680V23237M) vente maison",
      "https://www.vivastreet.com/immobilier-vente-maison/plouasne-22830/-680v23237m--vente-maison/313374256"
    )
    val couple2: (String, String) = (
      " Chef d&#039;équipe débutant en inventaire H F - Rennes",
      "https://www.vivastreet.com/emploi-fonction-transport-logisitique-achat/cesson-sevigne-35510/chef-d--quipe-d-butant-en-inventaire-h-f---rennes/313340761"
    )
    val couple3: (String, String) = (
      " Chef d&#039;équipe débutant en inventaire H F - Rennes",
      "https://www.vivastreet.com/emploi-fonction-industrie/cesson-sevigne-35510/chef-d--quipe-d-butant-en-inventaire-h-f---rennes/313247874"
    )
    val couple4: (String, String) = (
      " Ingénieur Système - Poste de Travail H F",
      "https://www.vivastreet.com/emploi-compabilite-gestion-finance/rennes-35000/ing-nieur-syst-me---poste-de-travail-h-f/313247823"
    )

    val fauxcouple: (String, String) = (
      "Titre",
      "https://url"
    )

    assertTrue(
      "La list de resultats de l'url " + url_rennes_page1 + " avec l'expresion " + exp_rennes + " possède exactenement 4 couples (url, titre) d'annonces",
      AnalysePage
        .resultats(url_rennes_page1, exp_rennes)
        .size
        .equals(4)
    )

    assertFalse(
      "La list de resultats de l'url " + url_rennes_page1 + " avec l'expresion " + exp_rennes + " ne possède pas le couple (url, titre) d'annonce : " + fauxcouple + ", alors que il ne fait pas partie des couples resultats",
      AnalysePage
        .resultats(url_rennes_page1, exp_rennes)
        .contains(fauxcouple)
    )

    // Modification des annonces sur vivastreet

    // assertTrue(
    //   "La list de resultats de l'url " + url_rennes_page1 + " avec l'expresion " + exp_rennes + " ne possède pas le couple (url, titre) d'annonce : " + couple1,
    //   AnalysePage
    //     .resultats(url_rennes_page1, exp_rennes)
    //     .contains(couple1)
    // )

    // assertTrue(
    //   "La list de resultats de l'url " + url_rennes_page1 + " avec l'expresion " + exp_rennes + " ne possède pas le couple (url, titre) d'annonce : " + couple2,
    //   AnalysePage
    //     .resultats(url_rennes_page1, exp_rennes)
    //     .contains(couple2)
    // )

    // assertTrue(
    //   "La list de resultats de l'url " + url_rennes_page1 + " avec l'expresion " + exp_rennes + " ne possède pas le couple (url, titre) d'annonce : " + couple3,
    //   AnalysePage
    //     .resultats(url_rennes_page1, exp_rennes)
    //     .contains(couple3)
    // )

    // assertTrue(
    //   "La list de resultats de l'url " + url_rennes_page1 + " avec l'expresion " + exp_rennes + " possède le couple (url, titre) d'annonce : " + couple4,
    //   AnalysePage
    //     .resultats(url_rennes_page1, exp_rennes)
    //     .contains(couple4)
    // )
  }
}
