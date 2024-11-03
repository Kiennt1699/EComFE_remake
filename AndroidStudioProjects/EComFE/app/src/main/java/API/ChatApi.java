package API;

import java.util.List;

import Domain.Category;
import Domain.ChatChannel;
import Domain.ChatMessage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChatApi {
    @GET("api/chat/list")
    Call<List<ChatChannel>> getChannles();
    @GET("api/chat")
    Call<List<ChatMessage>> getMessages(@Query("userid")String userid);
}
