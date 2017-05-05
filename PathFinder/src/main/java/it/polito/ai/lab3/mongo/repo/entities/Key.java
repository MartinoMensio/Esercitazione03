package it.polito.ai.lab3.mongo.repo.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

//TODO Adding suppress warning for Key
@SuppressWarnings("serial")
@Embeddable
public class Key implements Serializable {
	private String src;
	private String dst;
	
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO review the comparison
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Key other = (Key) obj;
		
		try {
			if (this.src.equals(other.src) && this.dst.equals(other.dst))
				return true;
			else
				return false;
		}
		catch (NullPointerException e) {
			return false;
		}		
	}
	
	// TODO add hash() method??
}
