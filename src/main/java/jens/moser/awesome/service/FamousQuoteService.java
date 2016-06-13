package jens.moser.awesome.service;

import jens.moser.awesome.domain.dto.QuoteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("famousQuoteService")
public class FamousQuoteService implements QuoteService {

    @Override
    public QuoteDto getQuoteOfToday() {
        return new QuoteDto("Category A", "Quote A");
    }

    @Override
    public List<QuoteDto> getQuotes() {
        return null;
    }

    @Override
    public QuoteDto getQuoteForCategory(String category) {
        return null;
    }
}
