package com.phonepe.restapi.interfaceI;

public interface IException {

  default String getMessage(Object e) {
    return ((Exception) e).getMessage();
  }
}
