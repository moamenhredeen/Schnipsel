package me.moamenhredeen.kakera.endpoints;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import me.moamenhredeen.kakera.domain.Snippet;

import java.util.List;
import java.util.UUID;

@Path("/snippet")
@Produces("application/json")
@Consumes("application/json")
public class SnippetResource {

    @PersistenceContext
    EntityManager em;

    @GET
    public List<Snippet> getSnippets() {
        return em.createQuery("select s from Snippet s", Snippet.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Snippet getSnippet(@PathParam("id") UUID id) {
        return  new Snippet(id);
    }

    @POST
    @Transactional
    public Response createSnippet(Snippet snippet) {
        try{
            em.persist(snippet);
            return Response.ok().entity(snippet).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }

}