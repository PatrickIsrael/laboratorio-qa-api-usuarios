package com.users.model;

public class ResponseError {
	private String artibuto;
	private String erro;
	
	
	public ResponseError(String artibuto, String erro) {
		this.artibuto = artibuto;
		this.erro = erro;
	}


	public String getArtibuto() {
		return artibuto;
	}


	public void setArtibuto(String artibuto) {
		this.artibuto = artibuto;
	}


	public String getErro() {
		return erro;
	}


	public void setErro(String erro) {
		this.erro = erro;
	}

	
	
}
