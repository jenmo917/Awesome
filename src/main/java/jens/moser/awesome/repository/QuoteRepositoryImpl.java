package jens.moser.awesome.repository;

import jens.moser.awesome.domain.model.Quote;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class QuoteRepositoryImpl implements QuoteRepository {

    private Map<String, Quote> quoteMap;

    @PostConstruct
    protected void init() {
        quoteMap = new ConcurrentHashMap<>();
        Quote quoteA = new Quote();
        quoteA.setAuthor("John");
        quoteA.setQuote("hello");

        Quote quoteB = new Quote();
        quoteB.setAuthor("Jens");
        quoteB.setQuote("world");

        quoteMap.put("CategoryA", quoteA);
        quoteMap.put("CategoryB", quoteB);

    }

    @Override
    public Quote getQuoteOfToday() {
        Quote quote = new Quote();
        quote.setQuote("Another day, another krone");
        quote.setAuthor("John");
        return quote;
    }

    @Override
    public Map<String, Quote> getQuotes() {
        return quoteMap;
    }

    @Override
    public void addQuote(String category, Quote quote) {
        quoteMap.put(category, quote);
    }

    @Override
    public Quote getQuote(String category) {
        return quoteMap.get(category);
    }
}
