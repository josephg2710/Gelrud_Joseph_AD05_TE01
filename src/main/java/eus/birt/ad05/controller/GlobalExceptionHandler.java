package eus.birt.ad05.controller;
import eus.birt.ad05.service.RecursoNoEncontradoException; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.ControllerAdvice; import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice public class GlobalExceptionHandler { @ExceptionHandler(RecursoNoEncontradoException.class) public String noEncontrado(RecursoNoEncontradoException e,Model m){m.addAttribute("mensaje",e.getMessage());return "error/404";} }
