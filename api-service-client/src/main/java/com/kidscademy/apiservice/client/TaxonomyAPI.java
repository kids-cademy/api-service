package com.kidscademy.apiservice.client;

import java.util.LinkedHashMap;

public interface TaxonomyAPI {
    LinkedHashMap<String, String> getTaxonomy(String name);
}
