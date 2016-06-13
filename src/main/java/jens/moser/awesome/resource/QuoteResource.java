package jens.moser.awesome.resource;

import jens.moser.awesome.domain.dto.QuoteDto;
import jens.moser.awesome.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quotes")
public class QuoteResource {

    @Autowired
    @Qualifier("quoteService")
    private QuoteService quoteService;

    @Autowired
    @Qualifier("famousQuoteService")
    private QuoteService famousQuoteService;

    @RequestMapping("today")
    public QuoteDto helloToday() {
        throw new RuntimeException("Dette tryner");
//        return quoteService.getQuoteOfToday();
    }

    @RequestMapping("")
    public List<QuoteDto> getQuotes() {
        return quoteService.getQuotes();
    }

    @RequestMapping("/{category}")
    public QuoteDto getQuoteForCategory(@PathVariable("category") String category) {
        return quoteService.getQuoteForCategory(category);
    }

}
