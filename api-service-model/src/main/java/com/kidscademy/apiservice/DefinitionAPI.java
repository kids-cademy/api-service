package com.kidscademy.apiservice;

import java.util.List;

import com.kidscademy.apiservice.model.WordDefinition;

public interface DefinitionAPI {
    List<WordDefinition> getDefinitions(String word);
}
