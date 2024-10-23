package API;

import java.util.List;

import Domain.Category;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {

    @GET("api/Categories")
    Call<List<Category>> getCategories();
}