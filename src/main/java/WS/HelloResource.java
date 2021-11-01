package WS;
import javax.ws.rs.*;

@Path("/hello-world")
public class HelloResource {
    @POST
    @Produces("text/plain")
    public String hello(@FormParam("distance") String distance) {

        double distancee = Double.parseDouble(distance)*10;




        return "Hello, World!"+distancee;
    }
}