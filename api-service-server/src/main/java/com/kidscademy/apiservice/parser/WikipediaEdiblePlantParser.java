package com.kidscademy.apiservice.parser;

import com.kidscademy.apiservice.model.EdiblePlant;

import js.dom.Document;

class WikipediaEdiblePlantParser implements Parser<EdiblePlant> {
    private final WikipediaLifeFormParser lifeFormParser = new WikipediaLifeFormParser();
    private final WikipediaNutritionalValueParser nutritionalValueParser = new WikipediaNutritionalValueParser();

    @Override
    public EdiblePlant parse(Document document) {
	EdiblePlant ediblePlant = new EdiblePlant();
	lifeFormParser.parse(document, ediblePlant);
	ediblePlant.setNutritionalValue(nutritionalValueParser.parse(document));
	return ediblePlant;
    }
}
