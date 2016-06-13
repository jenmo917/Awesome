package jens.moser.awesome.service;

import jens.moser.awesome.api.QuoteApiService;
import jens.moser.awesome.converter.Converter;
import jens.moser.awesome.domain.dto.QuoteDto;
import jens.moser.awesome.domain.exception.NotFoundException;
import jens.moser.awesome.domain.model.Quote;
import jens.moser.awesome.domain.model.QuoteOfTheDay;
import jens.moser.awesome.repository.QuoteRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("quoteService")
public class QuoteServiceImpl implements QuoteService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(QuoteServiceImpl.class);

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private Converter<QuoteDto, Quote> quoteConverter;

    private QuoteApiService quoteApiService;

    @Value("${url.quote}")
    private String quoteUrl;

    @PostConstruct
    protected void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(quoteUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        quoteApiService = retrofit.create(QuoteApiService.class);
    }

    @Override
    public QuoteDto getQuoteOfToday() {
        return quoteConverter.convert(quoteRepository.getQuoteOfToday());
    }

    @Override
    public List<QuoteDto> getQuotes() {
        return quoteConverter.convert(new ArrayList<>(quoteRepository.getQuotes().values()));
    }

    @Override
    public QuoteDto getQuoteForCategory(String category) {

        LOG.info("get quote for category: " + category);

        Quote quote = quoteRepository.getQuote(category);

        if (quote != null) {
            LOG.info("Quote exists in DB");
            return quoteConverter.convert(quote);
        }

        LOG.info("Quote does not exists in DB, make a request");

        Call<QuoteOfTheDay> quoteForCategory = quoteApiService.getQuoteForCategory(category);
        try {
            Response<QuoteOfTheDay> response = quoteForCategory.execute();
            QuoteOfTheDay quoteOfTheDay = response.body();

            if (quoteOfTheDay == null) {
                throw new NotFoundException("Could not find quote for category " + category);
            }

            quote = quoteOfTheDay.getContents().getQuotes().get(0);
            quoteRepository.addQuote(category, quote);
            LOG.info("Request successful");
            return quoteConverter.convert(quote);

        } catch (IOException e) {
            LOG.info("Request failed");
            e.printStackTrace();
        }

        return null;
    }
}
