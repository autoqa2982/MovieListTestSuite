package core;



import static core.Config.ENV;

public class Environments {

    private static String movieListEndPoint;

    public static void load() {
        if (ENV.equalsIgnoreCase("DEV")) {
            movieListEndPoint = "http://127.0.0.1:5001/api";
        }
        if (ENV.equalsIgnoreCase("PREPROD")) {
            movieListEndPoint = "http://127.0.0.1:5001/api";
        }
    }

    public static String getMovieListEndPoint() {
        return movieListEndPoint;
    }


}
