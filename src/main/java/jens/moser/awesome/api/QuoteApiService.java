package jens.moser.awesome.api;

import jens.moser.awesome.domain.model.QuoteOfTheDay;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteApiService {

    @GET("qod.json")
    Call<QuoteOfTheDay> getQuoteForCategory(@Query("category") String category);
}
