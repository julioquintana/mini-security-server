package com.cencosud.orderreturnservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

  private String code;
  private String message;
  private Map<String, String> details;
}
