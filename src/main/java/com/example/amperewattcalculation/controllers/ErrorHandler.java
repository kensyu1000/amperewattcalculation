package com.example.amperewattcalculation.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

  @ExceptionHandler(Exception.class)
  public String handleException(Exception e) {
    log.error("[ログ]エラーが発生しました", e);
    return "redirect::/device";
  }
}
