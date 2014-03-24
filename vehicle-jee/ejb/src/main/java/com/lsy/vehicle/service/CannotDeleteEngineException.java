package com.lsy.vehicle.service;

import com.lsy.vehicle.domain.Engine;

public class CannotDeleteEngineException extends ApplicationException {

	private static final long serialVersionUID = 1L;
	private Engine engine;
	
	public CannotDeleteEngineException(Engine engine, Throwable cause) {
		super("Engine cannot be deleted.", cause);
		this.engine = engine;
	}
	
	public Engine getEngine() {
		return engine;
	}

}
