package com.users.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.users.dto.RequisicaoNovoUsuario;
import com.users.model.Usuario;
import com.users.model.ResponseError;
import com.users.model.ResponseSucess;
import com.users.model.StatusUsuario;
import com.users.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping()
		public List<Usuario> getPedidos() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		usuarioRepository.close();
		return usuarios;
				
		
	}

	@GetMapping("/{status}")
	public List<Usuario> getPedidosByStatus(@PathVariable("status") String status, Model model) {
		List<Usuario> usuarios = usuarioRepository.findByStatus(StatusUsuario.valueOf(status.toUpperCase()));
		usuarioRepository.close();
		return usuarios;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "status não existente";
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseSucess cadastrarUsuario(@RequestBody @Valid RequisicaoNovoUsuario requisicao) {

		ResponseSucess usuarioCadastrado = new ResponseSucess();
		Usuario usuario = requisicao.toPedido();
		usuarioCadastrado.setUsuario(usuarioRepository.save(usuario));
		usuarioCadastrado.setMensagemSucesso("Usuário cadastrado com Sucesso");
		return usuarioCadastrado;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ResponseError> handlevalidationException(MethodArgumentNotValidException ex) {
		List<ResponseError> erros = new ArrayList();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMensage = error.getDefaultMessage();

			erros.add(new ResponseError(fieldName, errorMensage));
		});

		return erros;
	}
}
