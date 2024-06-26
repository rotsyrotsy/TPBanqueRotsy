/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mbds.tpbanquerotsy.service;

import com.mbds.tpbanquerotsy.entity.CompteBancaire;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * Façade pour gérer les comptes.
 *
 * @author Rotsy
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "rotsy",
        password = "PasswordMySQL2024",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire compte) {
        em.persist(compte);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("select count(c) from CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    public void transfert(CompteBancaire source, CompteBancaire destination, int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    public CompteBancaire getCompte(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public void deposer(CompteBancaire compte, int montant) {
        compte.deposer(montant);
        update(compte);
    }

    @Transactional
    public void retirer(CompteBancaire compte, int montant) {
        compte.retirer(montant);
        update(compte);
    }
    
    @Transactional
    public void supprimer(CompteBancaire compte){
        em.remove(em.merge(compte));
    }
    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }

}
