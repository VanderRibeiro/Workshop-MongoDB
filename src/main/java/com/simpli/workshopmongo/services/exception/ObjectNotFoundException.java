package com.simpli.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	/*
	 * Precisa ser Runtime pois caso contrário ele exige que você trate
	 * E também vai ser usado uma classe para tratar a exceção
	 */
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
