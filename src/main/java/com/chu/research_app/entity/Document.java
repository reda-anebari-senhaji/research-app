package com.chu.research_app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nom_fichier", nullable = false)
    private String nomFichier;
    
    @Column(name = "chemin_fichier", nullable = false)
    private String cheminFichier;
    
    @Column(name = "type_fichier")
    private String typeFichier;
    
    @Column(name = "taille_fichier")
    private Long tailleFichier;
    
    @Column(name = "date_upload")
    private LocalDateTime dateUpload = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_id", nullable = false)
    private ProductionScientifique production;
    
    // Constructeurs
    public Document() {}
    
    public Document(String nomFichier, String cheminFichier, String typeFichier, 
                   Long tailleFichier, ProductionScientifique production) {
        this.nomFichier = nomFichier;
        this.cheminFichier = cheminFichier;
        this.typeFichier = typeFichier;
        this.tailleFichier = tailleFichier;
        this.production = production;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNomFichier() {
        return nomFichier;
    }
    
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }
    
    public String getCheminFichier() {
        return cheminFichier;
    }
    
    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }
    
    public String getTypeFichier() {
        return typeFichier;
    }
    
    public void setTypeFichier(String typeFichier) {
        this.typeFichier = typeFichier;
    }
    
    public Long getTailleFichier() {
        return tailleFichier;
    }
    
    public void setTailleFichier(Long tailleFichier) {
        this.tailleFichier = tailleFichier;
    }
    
    public LocalDateTime getDateUpload() {
        return dateUpload;
    }
    
    public void setDateUpload(LocalDateTime dateUpload) {
        this.dateUpload = dateUpload;
    }
    
    public ProductionScientifique getProduction() {
        return production;
    }
    
    public void setProduction(ProductionScientifique production) {
        this.production = production;
    }
}