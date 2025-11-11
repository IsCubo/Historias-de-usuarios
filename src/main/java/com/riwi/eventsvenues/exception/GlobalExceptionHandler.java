package com.riwi.eventsvenues.exception;

import com.riwi.eventsvenues.exception.utils.Trace;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(NotFoundException ex, HttpServletRequest req)
    {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setType(URI.create("/errors/not-found"));
        pd.setTitle("Recurso no encontrado");
        pd.setDetail(ex.getMessage());
        pd.setProperty("code", ex.getErrorCode());
        pd.setProperty("traceId", Trace.currentId());
        pd.setProperty("instance", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ProblemDetail handleDuplicateName(DuplicateNameException ex, HttpServletRequest req)
    {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setType(URI.create("/errors/duplicate-name"));
        pd.setTitle("Nombre duplicado");
        pd.setDetail(ex.getMessage());
        pd.setProperty("code", ex.getErrorCode());
        pd.setProperty("traceId", Trace.currentId());
        pd.setProperty("instance", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusiness(BusinessException ex, HttpServletRequest req)
    {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setType(URI.create("/errors/business"));
        pd.setTitle("Regla de negocio incumplida");
        pd.setDetail(ex.getMessage());
        pd.setProperty("code", ex.getErrorCode());
        pd.setProperty("traceId", Trace.currentId());
        pd.setProperty("instance", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req)
    {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setType(URI.create("/errors/validation"));
        pd.setTitle("Error de validación");
        pd.setDetail("Uno o más campos son inválidos");
        pd.setProperty("traceId", Trace.currentId());
        pd.setProperty("instance", req.getRequestURI());

        List<Map<String, Object>> errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fe -> Map.of(
                        "field", fe.getField(),
                        "message", fe.getDefaultMessage(),
                        "rejectedValue", fe.getRejectedValue()
                ))
                .toList();
        pd.setProperty("errors", errors);
        return pd;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleUnreadable(HttpMessageNotReadableException ex, HttpServletRequest req)
    {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setType(URI.create("/errors/bad-request"));
        pd.setTitle("Solicitud mal formada");
        pd.setDetail("El cuerpo de la solicitud no pudo parsearse");
        pd.setProperty("traceId", Trace.currentId());
        pd.setProperty("instance", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, HttpServletRequest req)
    {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setType(URI.create("/errors/internal"));
        pd.setTitle("Error interno");
        pd.setDetail("Ha ocurrido un error inesperado");
        pd.setProperty("traceId", Trace.currentId());
        pd.setProperty("instance", req.getRequestURI());
        // log.error("Unhandled", ex) con traceId
        return pd;
    }
}
