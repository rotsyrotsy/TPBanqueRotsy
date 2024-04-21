/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tpbanquerotsy.jsf;

import com.mbds.tpbanquerotsy.entity.CompteBancaire;
import com.mbds.tpbanquerotsy.entity.OperationBancaire;
import com.mbds.tpbanquerotsy.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Rotsy
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private Long id;
    private CompteBancaire compteBancaire;
    private List<OperationBancaire> operations;


    @Inject
    private GestionnaireCompte gestionnaireCompte;
/**
     * Creates a new instance of Operations
     */
    public Operations() {
    }


    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void loadCompte() {
        compteBancaire = gestionnaireCompte.getCompte(this.id);
    }
    
    public List<OperationBancaire> getOperations() {
        return this.compteBancaire.getOperations();
    }
}
