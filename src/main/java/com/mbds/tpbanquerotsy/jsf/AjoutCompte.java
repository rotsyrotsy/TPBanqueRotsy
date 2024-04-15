/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tpbanquerotsy.jsf;

import com.mbds.tpbanquerotsy.entity.CompteBancaire;
import com.mbds.tpbanquerotsy.jsf.util.Util;
import com.mbds.tpbanquerotsy.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.PositiveOrZero;

/**
 *
 * @author Rotsy
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    @Inject
    GestionnaireCompte gestionnaireCompte;

    private String nom;
    @PositiveOrZero
    private int solde;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    @Transactional
    public String ajoutNouveauCompte() {
        gestionnaireCompte.creerCompte(new CompteBancaire(nom, solde));
        Util.addFlashInfoMessage("Création d'un nouveau compte pour "+nom);
        return "listeComptes?amp;faces-redirect=true";

    }

}
