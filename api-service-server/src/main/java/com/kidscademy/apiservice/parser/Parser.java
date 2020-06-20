package com.kidscademy.apiservice.parser;

import js.dom.Document;

public interface Parser<T> {
    T parse(Document document);
}
