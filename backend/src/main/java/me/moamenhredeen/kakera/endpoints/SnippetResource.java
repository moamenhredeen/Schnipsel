package me.moamenhredeen.kakera.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.moamenhredeen.kakera.dto.CommentDto;
import me.moamenhredeen.kakera.entities.Comment;
import me.moamenhredeen.kakera.entities.Snippet;
import me.moamenhredeen.kakera.entities.User;

@Path("/snippet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SnippetResource {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getSnippets() {
        try {
            var res = em.createNamedQuery("findSnippets", Snippet.class).getResultList();
            return Response.ok().entity(res).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getSnippet(@PathParam("id") Long id) {
        try {
            var res = em.createNamedQuery("findSnippetById", Snippet.class).getResultList();
            if (res.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return  Response.ok().entity(res.getFirst()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Transactional
    public Response createSnippet(Snippet snippet) {
        try{
            em.persist(snippet);
            return Response.ok().entity(snippet).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/{id}/comment/")
    @Transactional
    public Response comment(@PathParam("id") Long id, CommentDto  commentDto) {
        try {
            var comment = new Comment();
            comment.setAuthor(em.find(User.class, 1));
            comment.setSnippet(em.find(Snippet.class, id));
            comment.setContent(commentDto.content());
            em.persist(comment);
            return Response.ok().entity(comment).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}