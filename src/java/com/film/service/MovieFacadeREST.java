/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.film.service;

import com.films.entity.Movie;
import com.films.entity.Movie_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author ben.abdallah.med@icloud.com
 */
@Stateless
@Path("com.movie")
public class MovieFacadeREST extends AbstractFacade<Movie> {
    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    public MovieFacadeREST() {
        super(Movie.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Movie entity) {
        System.out.println(entity);
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Movie entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Movie find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Movie> findAll() {
        return super.findAll();
    }
     @GET
     @Path("{id}")
    @Produces({"application/xml", "application/json"})
     
    public List<Movie> findBYGENRE(@PathParam("id") Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Root<Movie> m = cq.from(Movie.class);
        Predicate pr = cb.equal(m.get(Movie_.genderId),true);
        cq.where(pr);
        
        return getEntityManager().createQuery(cq).getResultList();
       
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Movie> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
