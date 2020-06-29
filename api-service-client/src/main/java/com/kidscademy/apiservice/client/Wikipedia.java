package com.kidscademy.apiservice.client;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.kidscademy.apiservice.model.EdiblePlant;
import com.kidscademy.apiservice.model.LifeForm;

@Path("wikipedia")
public interface Wikipedia extends TaxonomyAPI {
    @GET
    @Path("life-form/{name}")
    LifeForm getLifeForm(@PathParam("name") String name);

    @GET
    @Path("edible-plant/{name}")
    EdiblePlant getEdiblePlant(@PathParam("name") String name);

    @GET
    @Path("taxonomy/{name}")
    LinkedHashMap<String, String> getTaxonomy(@PathParam("name") String name);

    /**
     * Get nutritional value for a particular item of food identified by its unique
     * name. Returned value is a measure of a well-balanced ratio of the essential
     * nutrients carbohydrates, fat, protein, minerals, and vitamins.
     * 
     * @param name
     *            unique name of requested item of food.
     * @return nutritional value.
     */
    @GET
    @Path("nutritional-value/{name}")
    Map<String, Double> getNutritionalValue(@PathParam("name") String name);
}
