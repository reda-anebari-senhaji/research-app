package com.chu.research_app.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(length = 1000)
    private String description;
    
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chercheur> chercheurs;
    
    // Constructeurs
    public Service() {}
    
    public Service(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Chercheur> getChercheurs() {
        return chercheurs;
    }
    
    public void setChercheurs(List<Chercheur> chercheurs) {
        this.chercheurs = chercheurs;
    }
}