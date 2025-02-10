import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubAPI { // Interface GitHubAPI
    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username); // Método searchUsers

    @GET("/users/{username}/repos")
    Call<List<Repository>> getUserRepositories(@Path("username") String username); // Método getUserRepositories
}