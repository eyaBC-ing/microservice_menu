package com.example.servicemenu;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "plats", collectionResourceRel = "plats")
public interface PlatRepository extends CrudRepository<Plat, Long> {
    //Recherche les plats par catégorie
    //endpoint: GET /plats/search/findByCategorie?categorie=ENTREE
    @RestResource(path = "categorie", rel = "categorie")
    List<Plat> findByCategorie(Plat.Categorie categorie);


    //Recherche les plats par nom (contient le texte)
    //Endpoint: GET /plats/search/findByName?nom=pizza
    @RestResource(path = "recherche", rel = "recherche")
    List<Plat> findByNom(String nom);


    //Recherche les plats disponibles
    //Endpoint généré: GET /plats/search/findByDisponible?disponible=true
    @RestResource(path = "disponibles", rel = "disponibles")
    List<Plat> findByDisponible(Boolean disponible);
}
