package org.example;

import io.cucumber.java.en.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;

public class ChatIntelligentStepDefinitions {
    private ChatIntelligent chat;
    private MaisonPourChat maison;
    private MaisonPourChat nouvelleMaison;

    @Before
    public void setUp() {
        chat = null;
    }

    // 1️⃣ Chat apprend un nouveau mot
    @Given("un chat appelé {string} âgé de {int} ans")
    public void un_chat_appelé_âgé_de(String nom, int age) {
        chat = new ChatIntelligent(nom, age);
    }

    @When("il apprend {int} mot\\(s\\)")
    public void il_apprend_mots(int nombreMots) {
        for (int i = 0; i < nombreMots; i++) {
            chat.apprendreMot("Mot" + i);
        }
    }

    @Then("il doit connaître {int} mot\\(s\\)")
    public void il_doit_connaître_mots(int nombreMots) {
        Assertions.assertEquals(nombreMots, chat.getNombreMots());
    }

    // 2️⃣ Chat rejoint une maison
    @Given("une maison située à {string}")
    public void une_maison_située_à(String adresse) {
        maison = new MaisonPourChat(adresse);
    }

    @When("{string} rejoint cette maison")
    public void rejoint_cette_maison(String nom) {
        chat.rejoindreMaison(maison);
    }

    @Then("la maison doit contenir {int} chat")
    public void la_maison_doit_contenir_chat(int nombreChats) {
        Assertions.assertEquals(nombreChats, maison.getNombreChats());
    }

    @Then("{string} doit habiter à {string}")
    public void doit_habiter_à(String nom, String adresse) {
        Assertions.assertEquals(adresse, chat.getMaison().getAdresse());
    }

    // 3️⃣ Chat change de maison
    @Given("une autre maison située à {string}")
    public void une_autre_maison_située_à(String adresse) {
        nouvelleMaison = new MaisonPourChat(adresse);
    }

    @When("{string} rejoint la maison {string}")
    public void rejoint_la_maison(String nom, String adresse) {
        if (adresse.equals(maison.getAdresse())) {
            chat.rejoindreMaison(maison);
        } else {
            chat.rejoindreMaison(nouvelleMaison);
        }
    }

    @Then("la maison {string} ne doit plus contenir de chat")
    public void la_maison_ne_doit_plus_contenir_de_chat(String adresse) {
        if (adresse.equals(maison.getAdresse())) {
            Assertions.assertEquals(0, maison.getNombreChats());
        } else {
            Assertions.assertEquals(0, nouvelleMaison.getNombreChats());
        }
    }

    @Then("la maison {string} doit contenir {int} chat")
    public void la_maison_doit_contenir_chat(String adresse, Integer nombreChats) {
        if (maison != null && maison.getAdresse().equals(adresse)) {
            Assertions.assertEquals(nombreChats, maison.getNombreChats());
        } else if (nouvelleMaison != null && nouvelleMaison.getAdresse().equals(adresse)) {
            Assertions.assertEquals(nombreChats, nouvelleMaison.getNombreChats());
        } else {
            Assertions.fail("Aucune maison avec l'adresse " + adresse);
        }
    }

}