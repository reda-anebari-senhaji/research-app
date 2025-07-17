package com.chu.research_app.entity;

import com.chu.research_app.util.TypeProduction;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productions_scientifiques")
public class ProductionScientifique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(length = 2000)
    private String resume;
    
    @Column(name = "date_publication")
    private LocalDate datePublication;
    
    @Column(name = "mots_cles")
    private String motsCles;
    
    private String revue;
    
    private String doi;
    
    @Column(name = "nombre_pages")
    private Integer nombrePages;
    
    @Column(name = "date_creation")
    private LocalDateTime dateCreation = LocalDateTime.now();
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeProduction type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chercheur_id", nullable = false)
    private Chercheur chercheur;
    
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents;
    
    // Constructeurs
    public ProductionScientifique() {}
    
    public ProductionScientifique(String titre, String resume, LocalDate datePublication, 
                                  TypeProduction type, Chercheur chercheur) {
        this.titre = titre;
        this.resume = resume;
        this.datePublication = datePublication;
        this.type = type;
        this.chercheur = chercheur;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getResume() {
        return resume;
    }
    
    public void setResume(String resume) {
        this.resume = resume;
    }
    
    public LocalDate getDatePublication() {
        return datePublication;
    }
    
    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }
    
    public String getMotsCles() {
        return motsCles;
    }
    
    public void setMotsCles(String motsCles) {
        this.motsCles = motsCles;
    }
    
    public String getRevue() {
        return revue;
    }
    
    public void setRevue(String revue) {
        this.revue = revue;
    }
    
    public String getDoi() {
        return doi;
    }
    
    public void setDoi(String doi) {
        this.doi = doi;
    }
    
    public Integer getNombrePages() {
        return nombrePages;
    }
    
    public void setNombrePages(Integer nombrePages) {
        this.nombrePages = nombrePages;
    }
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public TypeProduction getType() {
        return type;
    }
    
    public void setType(TypeProduction type) {
        this.type = type;
    }
    
    public Chercheur getChercheur() {
        return chercheur;
    }
    
    public void setChercheur(Chercheur chercheur) {
        this.chercheur = chercheur;
    }
    
    public List<Document> getDocuments() {
        return documents;
    }
    
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
