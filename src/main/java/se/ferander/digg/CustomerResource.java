package se.ferander.digg;

import io.vertx.core.cli.annotations.Hidden;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/digg/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private static final Logger LOG = Logger.getLogger(CustomerResource.class);

    @Inject
    CustomerRepository repo;

    @GET
    @Operation(summary = "Lista kunder", description = "Returnerar kunder med paginering (page/size).")
    @APIResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = Customer.class))
    )
    public List<Customer> list(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        List<Customer> all = repo.list();
        int from = Math.max(0, page * size);
        int to = Math.min(all.size(), from + size);

        if (from >= to) {
            return List.of();
        }
        LOG.info("Returnerar lista på kunder i repositoryt");
        return all.subList(from, to);
    }

    @POST
    @Path("/seed")
    public Response seedCustomers() {
        repo.seedCustomers();
        return Response.ok("Seed-data skapat!").build();
    }

    @POST
    @Operation(summary = "Lägg till en ny kund")
    @APIResponse(responseCode = "201", description = "Kund skapad")
    public Response add(@Valid Customer customer) {
        repo.add(customer);
        LOG.infov("Ny kund: {0} ({1})", customer.getName(), customer.getEmail());
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @DELETE
    @Path("/all")
    @Hidden // gömmer endpointen från OpenAPI
    public Response clearAll() {
        repo.clear();
        LOG.info("Repository tömt");
        return Response.noContent().build();
    }
}
