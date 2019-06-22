package com.nucleoti.alpes.ti.model;

import javax.xml.bind.annotation.XmlRootElement;


public class Usuario {
	private String idUsuario;
	private String Name;
	private String Apaterno;
	private String Amaterno;
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getApaterno() {
		return Apaterno;
	}
	public void setApaterno(String apaterno) {
		Apaterno = apaterno;
	}
	public String getAmaterno() {
		return Amaterno;
	}
	public void setAmaterno(String amaterno) {
		Amaterno = amaterno;
	}
	
}
