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
            CompteBancaire compteJohn = new CompteBancaire("John Lennon", 150000);
            CompteBancaire comptePaul = new CompteBancaire("Paul McCartney", 950000);
            CompteBancaire compteRingo = new CompteBancaire("Ringo Starr", 20000);
            CompteBancaire compteGeorges = new CompteBancaire("Georges Harrisson", 100000);

            this.gestionnaireCompte.creerCompte(compteJohn);
            this.gestionnaireCompte.creerCompte(comptePaul);
            this.gestionnaireCompte.creerCompte(compteRingo);
            this.gestionnaireCompte.creerCompte(compteGeorges);
        }
    }
}
