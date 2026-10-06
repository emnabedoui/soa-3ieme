package ressourcesrest;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("options")
public class restOption {

   public static OptionBusiness optB = new OptionBusiness();

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addOption(Option option) {
      if (optB.addOption(option)) {
         return Response.status(200).build();
      }
      return Response.status(404).build();
   }

   @DELETE
   @Path("{code}")
   public Response deleteOption(@PathParam("code") int id) { /*code kima id */
      if (optB.deleteOption(id)) {
         return Response.status(Response.Status.NO_CONTENT).build();
      }
      return Response.status(Response.Status.NOT_FOUND).build();
   }

   @PUT
   @Path("{code}")
   @Consumes(MediaType.APPLICATION_JSON) /* fel atelier 9alek json*/
   public Response updateOption(@PathParam("code") int id, Option option) {
      if (optB.updateOption(id, option)) {
         return Response.status(Response.Status.OK).build();
      }
      return Response.status(Response.Status.NOT_FOUND).build();
   }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAllOptions(@QueryParam("domaine") String domaine) {
      List<Option> l;

      if (domaine == null || domaine.isEmpty()) {
         l = optB.getListeOptions();
      } else {
         l = optB.getOptionsByDomaine(domaine);
      }

      if (l.isEmpty()) {
         return Response.status(Response.Status.NO_CONTENT).build();
      }
      return Response.status(200).entity(l).build();
   }


   @GET
   @Path("{code}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOptionByCode(@PathParam("code") int id) {
      Option option = optB.getOptionByCode(id);
      if (option != null) {
         return Response.status(Response.Status.OK).entity(option).build();
      }
      return Response.status(Response.Status.NOT_FOUND).build();
   }
}