package com.sinugaud.exception;

public class ServerException extends RuntimeException {
  /** */
  private static final long serialVersionUID = 1L;

  public ServerException(String message, Throwable cause) {
    super(message, cause);
  }
}
