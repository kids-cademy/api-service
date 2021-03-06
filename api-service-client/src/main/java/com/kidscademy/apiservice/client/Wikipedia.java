package com.kidscademy.apiservice.client;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.kidscademy.apiservice.model.EdiblePlant;
import com.kidscademy.apiservice.model.LifeForm;
import com.kidscademy.apiservice.model.WordDefinition;

@Path("wikipedia")
public interface Wikipedia {
    @GET
    @Path("definitions/{word}")
    List<WordDefinition> getDefinitions(@PathParam("word") String word);

    /**
     * Get life form descriptor.
     * 
     * @param name
     *            unique common name for requested life form.
     * @return life form descriptor.
     */
    @GET
    @Path("life/{name}")
    LifeForm getLifeForm(@PathParam("name") String name);

    /**
     * Get life form scientific taxonomy.
     * 
     * @param name
     *            unique common name for requested life form.
     * @return life form descriptor.
     */
    @GET
    @Path("/life/taxonomy/{name}")
    LinkedHashMap<String, String> getLifeTaxonomy(@PathParam("name") String name);

    @GET
    @Path("edible/{name}")
    EdiblePlant getEdiblePlant(@PathParam("name") String name);

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
    @Path("edible/nutrients/{name}")
    LinkedHashMap<String, Double> getEdibleNutrients(@PathParam("name") String name);
}
