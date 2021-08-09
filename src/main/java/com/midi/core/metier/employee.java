package com.midi.core.metier;

import java.util.Date;

public class employee {

	private String matricule;
	private String nom;
	private String prenom;
	private Date date_debut;
	private String role;
	
	public employee() {
		super();
	}
	public employee(String matricule, String nom, String prenom, Date date_debut, String role) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.date_debut = date_debut;
		this.role = role;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "employee [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", date_debut=" + date_debut
				+ ", role=" + role + "]";
	}
	
	
}
