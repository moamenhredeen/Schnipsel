package me.moamenhredeen.kakera.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.moamenhredeen.kakera.dto.LoginRequestDto;
import me.moamenhredeen.kakera.dto.RegisterRequestDto;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"USER"})
public class AuthResource {

    @PersistenceContext
    private EntityManager em;

    @POST
    @Path("/register")
    public Response register(RegisterRequestDto registerRequestDto) {
        var user = registerRequestDto.toUser();
        em.persist(user);
        return Response.ok().entity(RegisterRequestDto.fromUser(user)).build();
    }

    @POST
    @Path("/login")
    public Response login(LoginRequestDto loginRequestDto) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
