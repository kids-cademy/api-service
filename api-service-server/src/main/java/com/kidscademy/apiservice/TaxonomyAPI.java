package com.kidscademy.apiservice;

import java.util.LinkedHashMap;

public interface TaxonomyAPI {
    LinkedHashMap<String, String> getTaxonomy(String name);
}
