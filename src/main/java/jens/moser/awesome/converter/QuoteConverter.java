package jens.moser.awesome.converter;

import jens.moser.awesome.domain.dto.QuoteDto;
import jens.moser.awesome.domain.model.Quote;
import org.springframework.stereotype.Component;

@Component
public class QuoteConverter extends AbstractConverter<QuoteDto, Quote> {

    @Override
    protected QuoteDto convertItem(Quote quote) {
        return new QuoteDto(quote.getQuote(), quote.getAuthor());
    }
}