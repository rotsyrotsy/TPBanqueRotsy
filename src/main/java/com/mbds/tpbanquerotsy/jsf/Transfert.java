/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tpbanquerotsy.jsf;

import com.mbds.tpbanquerotsy.entity.CompteBancaire;
import com.mbds.tpbanquerotsy.jsf.util.Util;
import com.mbds.tpbanquerotsy.service.GestionnaireCompte;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Rotsy
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert implements Serializable {

    private Long idSource;
    private Long idDestinataire;
    private int montant;
    @Inject
    GestionnaireCompte gestionnaireCompte;

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of TransfertArgent
     */
    public Transfert() {
    }

    public String transfererArgent() {
        CompteBancaire source = gestionnaireCompte.getCompte(idSource);
        CompteBancaire destinataire = gestionnaireCompte.getCompte(idDestinataire);
        boolean erreur = false;
        if (source == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Le solde du compte source de " + source.getNom() + " est insuffisant !", "Le solde du compte source de " + source.getNom() + " est insuffisant !", "form:montant");
                erreur = true;
            }
        }
        if (destinataire == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destinataire");
            erreur = true;
        }
        if (erreur) {
            return null;
        } else {
            gestionnaireCompte.transfert(source, destinataire, montant);
            Util.addFlashInfoMessage("Transfert de " + montant + " EUR depuis le compte de " + source.getNom() + " vers le compte de " + destinataire.getNom() + " correctement effectué.");
            return "listeComptes?amp;faces-redirect=true";
        }
    }

}
