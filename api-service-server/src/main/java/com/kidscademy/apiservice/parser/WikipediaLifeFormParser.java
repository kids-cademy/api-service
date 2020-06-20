package com.kidscademy.apiservice.parser;

import com.kidscademy.apiservice.model.LifeForm;
import com.kidscademy.apiservice.util.Strings;

import js.dom.Document;
import js.dom.EList;

class WikipediaLifeFormParser implements Parser<LifeForm> {
    private final WikipediaBiotaParser biotaParser = new WikipediaBiotaParser();

    @Override
    public LifeForm parse(Document document) {
	LifeForm lifeForm = new LifeForm();
	return parse(document, lifeForm);
    }

    public LifeForm parse(Document document, LifeForm lifeForm) {
	lifeForm.setCommonName(Strings.text(document.getByXPath("//H1[@id='firstHeading']")));

	EList content = document.findByXPath("//DIV[@id='mw-content-text']/DIV[contains(@class,'mw-parser-output')]/*");
	WikipediaArticleText articleText = new WikipediaArticleText(content);
	lifeForm.setDefinition(articleText.getDefinition());
	lifeForm.setDescription(articleText.getDescription());

	WikipediaBiota biota = biotaParser.parse(document);
	lifeForm.setConservationStatus(biota.getConservationStatus());
	lifeForm.setStartDate(biota.getStartDate());
	lifeForm.setEndDate(biota.getEndDate());

	lifeForm.setScientificName(biota.getScientificName());
	lifeForm.setTaxonomy(biota.getTaxonomy());

	return lifeForm;
    }
}
