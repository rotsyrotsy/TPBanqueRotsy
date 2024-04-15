/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mbds.tpbanquerotsy.config;

import com.mbds.tpbanquerotsy.entity.CompteBancaire;
import com.mbds.tpbanquerotsy.service.GestionnaireCompte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;

/**
 * Initialise une base de données vide.
 * @author Rotsy
 */
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of Init
     */
    public Init() {

    }

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        if (this.gestionnaireCompte.nbComptes() == 0) {
            this.gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }
}
