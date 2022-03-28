package com.users.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import org.hibernate.validator.constraints.br.CPF;

import com.users.model.Usuario;
import com.users.model.StatusUsuario;

import static com.users.utils.Constantes.*;

public class RequisicaoNovoUsuario {

	@NotBlank(message = CAMPO_OBRIGATORIO)
	private String nome;
	
	@NotBlank(message = CAMPO_OBRIGATORIO)
	@Email(message = EMAIL_INVALIDO)
	private String email;
	
	@NotBlank(message = CAMPO_OBRIGATORIO)
	private String senha;
	
	@NotBlank(message = CAMPO_OBRIGATORIO)
	@CPF(message = CPF_INVALIDO)
	private String cpf;
	
	@NotBlank(message = CAMPO_OBRIGATORIO)
	private String urlFoto;
	
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getUrlFoto() {
		return urlFoto;
	}


	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}


	public Usuario toPedido() {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setUrlFoto(urlFoto);
		
		usuario.setStatus(StatusUsuario.CADASTRADO);
		
		return usuario;
	}	
}
