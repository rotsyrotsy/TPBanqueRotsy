/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tpbanquerotsy.jsf;

import com.mbds.tpbanquerotsy.entity.CompteBancaire;
import com.mbds.tpbanquerotsy.jsf.util.Util;
import com.mbds.tpbanquerotsy.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 *
 * @author Rotsy
 */
@Named(value = "retraitVersement")
@ViewScoped
public class RetraitVersement implements Serializable {

    private Long idCompte;
    private CompteBancaire compteBancaire;
    private String typeMouvement;
    @PositiveOrZero
    private int montant;

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    /**
     * Creates a new instance of RetraitVersement
     */
    public RetraitVersement() {
    }

    public void loadCompte() {
        this.compteBancaire = gestionnaireCompte.getCompte(idCompte);
    }

    public String validerMouvement() {
        try {
            boolean erreur = false;
            if (typeMouvement == null || typeMouvement.equals("")) {
                Util.messageErreur("Choisissez un mouvement !", "Choisissez un mouvement !", "form:typeMouvement");
                erreur = true;
            } else {
                if (typeMouvement.equals("retrait")) {
                    if (compteBancaire.getSolde() < montant) {
                        Util.messageErreur("Le retrait doit être inférieur au solde du compte", "Le retrait doit être inférieur au solde du compte", "form:montant");
                        erreur = true;
                    }
                }
            }
            if (erreur) {
                return null;
            }
            if (typeMouvement.equals("ajout")) {
                gestionnaireCompte.deposer(compteBancaire, montant);
            } else if (typeMouvement.equals("retrait")) {
                gestionnaireCompte.retirer(compteBancaire, montant);
            }
            Util.addFlashInfoMessage("Mouvement enregistré sur compte de " + compteBancaire.getNom());
            return "listeComptes?faces-redirect=true";
        } catch (OptimisticLockException ex) {
            Util.messageErreur("Le compte de " + compteBancaire.getNom()
                    + " a été modifié ou supprimé par un autre utilisateur !"
                            + "Revenez à la liste des comptes et faites une nouvelle transaction");
            return null; // pour rester sur la page s'il y a une exception
        }
    }

}
