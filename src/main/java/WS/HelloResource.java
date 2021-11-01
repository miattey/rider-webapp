package WS;

import javax.ws.rs.*;

@Path("/calculatefare")
public class HelloResource {
    @POST
    @Produces("text/plain")

    public String hello(@FormParam("distance") String distance) {



        double distancee = Double.parseDouble(distance);

        double gst = 0.06;
        double price_per_km= 6;
        double base_price = 25;

        double finalprice = (((distancee)*price_per_km)+base_price);

        double finalpricegst = finalprice * (1 + gst);





        return String.valueOf(String.format("%,.2f", finalpricegst));
    }
}