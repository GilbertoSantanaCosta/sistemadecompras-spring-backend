package com.implementacao.mc.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.implementacao.mc.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	Integer id;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=5,max=120,message="O tamanho deve ser entre 5 e 120 caracteres")
	String name;
	@NotEmpty(message="Preenchimento obrigatorio")
	@Email(message="Email inválido")
	String email;
	
	public ClienteDTO() {
		
	}
	
    public ClienteDTO(Cliente obj) {
		
    	id = obj.getId();
    	name = obj.getNome();
    	email = obj.getEmail();
    			
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
