package com.midi.core.metier;

import java.util.ArrayList;
import java.util.List;

public class critere {
	private String nom;
	private float taux;
	private role role;
	private List<Scritere> scriteres;
	
	public critere() {
	}
	
	public critere(String nom,String taux) {
		this.nom = nom;
		float tauxNumeric = 0;
		try {
			tauxNumeric = Float.parseFloat(taux);
		}catch(Exception e) {
			System.out.println("Exception in parsing rate");
		}
		this.taux= tauxNumeric;
		scriteres = new ArrayList<Scritere>();
	}

	public void addScritere(Scritere scrit) {
		if (scriteres == null) scriteres = new ArrayList<Scritere>();
		scriteres.add(scrit);
		scrit.setCrit(this);
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public role getRole() {
		return role;
	}
	public void setRole(role obj) {
		this.role = obj;
	}
	public List<Scritere> getScriteres() {
		return scriteres;
	}
	public void setScriteres(List<Scritere> scriteres) {
		scriteres = scriteres;
	}

	public float getTaux() {
		return taux;
	}

	public void setTaux(int taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "critere [nom=" + nom + ", taux=" + taux + ", Scriteres=" + scriteres + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + Float.floatToIntBits(taux);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		critere other = (critere) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Float.floatToIntBits(taux) != Float.floatToIntBits(other.taux))
			return false;
		return true;
	}
	
	
	
}
