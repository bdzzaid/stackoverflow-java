package retrofit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;
import java.util.Objects;

public final class SimpleService
{
    public static final String API_URL = "https://api.github.com";

    public static class Contributor
    {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions)
        {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub
    {
        @GET("/repos/{owner}/{repo}/contributors")
        @retrofit2.http.Headers("Transfer-Encoding: chunked")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    public static void main(String... args)
    {
        Retrofit retrofit = buildRetrofit(true);
        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);
        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");
        Headers reqHeaders = call.request().headers();
        try
        {
            // Fetch and print a list of the contributors to the library.
            Response<List<Contributor>> contributors = call.execute();
            Headers respHeader = contributors.headers();
            printHeader("Transfer-Encoding", reqHeaders, respHeader);
        }
        catch (Throwable e)
        {
            printHeader("Transfer-Encoding", reqHeaders, null);
        }
    }

    private static Retrofit buildRetrofit(boolean addInterceptor)
    {
        if (addInterceptor)
        {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain ->
            {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Transfer-Encoding", "chunked")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });
            return new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static void printHeader(String headerName, Headers requestHeader, Headers responseHeader)
    {
        Objects.requireNonNull(headerName, "Missing header name");
        Objects.requireNonNull(requestHeader, "Missing request headers");
        System.out.println(String.format("Request header %s : %s ", headerName,
                requestHeader.names().contains(headerName) ? requestHeader.get(headerName) : "is missing"));
        if (responseHeader != null)
            System.out.println(String.format("Response header %s : %s ", headerName,
                    responseHeader.names().contains(headerName) ? responseHeader.get(headerName) : "is missing"));
    }
}
