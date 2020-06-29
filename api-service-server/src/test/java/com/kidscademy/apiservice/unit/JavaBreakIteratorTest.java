package com.kidscademy.apiservice.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class JavaBreakIteratorTest {
    @Test
    public void sentences() {
	String source = "This is a test. This is a T.L.A. test. Now with a Dr. in it. Done on Jun. 27.";
	List<String> sentences = new ArrayList<>();

	BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
	iterator.setText(source);
	int start = iterator.first();
	for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
	    sentences.add(source.substring(start, end).trim());
	}

	assertThat(sentences, hasSize(4));
	assertThat(sentences.get(0), equalTo("This is a test."));
	assertThat(sentences.get(1), equalTo("This is a T.L.A. test."));
	assertThat(sentences.get(2), equalTo("Now with a Dr. in it."));
	assertThat(sentences.get(3), equalTo("Done on Jun. 27."));
    }
    
    @Test
    public void firstSentence() {
	String source = "This is a T.L.A. test. This is a test. Now with a Dr. in it. ";
	BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
	iterator.setText(source);
	
	int end = iterator.next();
	assertThat(end, not(equalTo(BreakIterator.DONE)));
	
	String sentence = source.substring(iterator.first(), end).trim();
	assertThat(sentence, equalTo("This is a T.L.A. test."));
    }
}
