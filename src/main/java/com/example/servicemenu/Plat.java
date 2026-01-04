package com.example.servicemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "plats")
@Data  // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Constructeur sans arguments (requis par JPA)
@AllArgsConstructor  // Constructeur avec tous les arguments
@Builder  // Pattern Builder pour créer des instances facilement
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlat;

    @JsonProperty("nom")
    @NotBlank(message = "Le nom du plat est obligatoire")
    @Size(min = 2, max = 250, message = "Le nom doit contenir entre 2 et 250 caractères")
    @Column(nullable = false, length = 250)
    private String nom;

    @JsonProperty("description")
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    @Column(length = 500)
    private String description;

    @JsonProperty("prix")
    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @JsonProperty("categorie")
    @NotNull(message = "La catégorie est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Categorie categorie;

    @JsonProperty("image")
    @Column(name = "image", length = 255 )
    private String image;

    @JsonProperty("disponible")
    @Column(nullable = false)
    private Boolean disponible = true;

    @CreationTimestamp
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    public enum Categorie {
        ENTREE("Entrée"),
        PLAT("Plat principal"),
        DESSERT("Dessert"),
        BOISSON("Boisson");

        private final String libelle;

        Categorie(String libelle) {
            this.libelle = libelle;
        }

        public String getLibelle() {
            return libelle;
        }
    }
}
/**
 * NotBlank:Le champ ne peut pas être vide ou contenir uniquement des espaces
 * DecimalMin: Le nombre doit être supérieur à value
 *
 */