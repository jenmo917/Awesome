package jens.moser.awesome.repository;

import jens.moser.awesome.domain.model.Quote;

import java.util.Map;

public interface QuoteRepository {

    Quote getQuoteOfToday();

    Map<String, Quote> getQuotes();

    void addQuote(String category, Quote quote);

    Quote getQuote(String category);
}
