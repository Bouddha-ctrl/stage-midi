package com.midi.core.metier;

public class Scritere {
	private String nom;
	private float taux;
	private critere crit;
	
	public Scritere() {
	}

	public Scritere(String nom,String taux) {
		this.nom = nom;
		float tauxNumeric = 0;
		try {
			tauxNumeric = Float.parseFloat(taux);
		}catch(Exception e) {
			System.out.println("Exception in parsing rate");
		}
		this.taux= tauxNumeric;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getTaux() {
		return taux;
	}

	public void setTaux(int taux) {
		this.taux = taux;
	}

	public critere getCrit() {
		return crit;
	}

	public void setCrit(critere crit) {
		this.crit = crit;
	}

	@Override
	public String toString() {
		return "Scritere [nom=" + nom + ", taux=" + taux + "]";
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
		Scritere other = (Scritere) obj;
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
