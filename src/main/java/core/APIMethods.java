package core;

public class APIMethods {

    public static APIMethod CREATE_MOVIE = new APIMethod(Environments.getMovieListEndPoint(), "movie", HttpMethod.POST);
    public static APIMethod UPDATE_MOVIE = new APIMethod(Environments.getMovieListEndPoint(), "movie/@@param", HttpMethod.PUT);
    public static APIMethod GET_MOVIE = new APIMethod(Environments.getMovieListEndPoint(), "movies", HttpMethod.GET);
    public static APIMethod DELETE_MOVIE = new APIMethod(Environments.getMovieListEndPoint(), "movie/@@param", HttpMethod.DELETE);

}
