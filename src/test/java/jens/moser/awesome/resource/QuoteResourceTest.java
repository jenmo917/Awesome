package jens.moser.awesome.resource;

import jens.moser.awesome.domain.model.Quote;
import jens.moser.awesome.service.QuoteService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class QuoteResourceTest extends BaseTest {

    @InjectMocks
    private QuoteResource quoteResource;

    @Mock(name="famousQuoteService")
    private QuoteService famousQuoteService;

    @Mock(name="quoteService")
    private QuoteService quoteService;

    @Test
    public void testHelloToday() throws Exception {
//        Quote quote = new Quote();
//        when(famousQuoteService.getQuoteOfToday()).thenReturn(quote);
//        assertEquals(quote, quoteResource.helloToday());
//        verify(famousQuoteService, times(1)).getQuoteOfToday();
    }
}