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
import java.io.Serializable;

/**
 *
 * @author Rotsy
 */
@Named(value = "modifierCompte")
@ViewScoped
public class ModifierCompte implements Serializable {
    
    private Long idCompte;
    private CompteBancaire compteBancaire;
    private String nomInitial;
    
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
    public ModifierCompte() {
    }
    
    public void loadCompte() {
        compteBancaire = gestionnaireCompte.getCompte(idCompte);
        this.nomInitial = compteBancaire.getNom();
    }

    public String updateCompte() {
        gestionnaireCompte.update(compteBancaire);
        Util.addFlashInfoMessage("Nom " +this.nomInitial+" changé en "+ compteBancaire.getNom());
        return "modifierCompte?idCompte="+compteBancaire.getId()+"&faces-redirect=true";
        
    }
    
}
