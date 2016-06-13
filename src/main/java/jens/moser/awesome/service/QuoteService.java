package jens.moser.awesome.service;

import jens.moser.awesome.domain.dto.QuoteDto;

import java.util.List;
import java.util.Map;

public interface QuoteService {

    QuoteDto getQuoteOfToday();

    List<QuoteDto> getQuotes();

    QuoteDto getQuoteForCategory(String category);
}
