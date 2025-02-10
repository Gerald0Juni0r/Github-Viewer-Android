import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubAPI { // Interface GitHubAPI
    @GET("/search/users")
    Call<UserResponse> searchUsers(@Query("q") String query); // Método searchUsers
}