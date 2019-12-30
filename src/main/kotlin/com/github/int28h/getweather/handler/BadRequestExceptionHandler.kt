package com.github.int28h.getweather.handler

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Component
@RestControllerAdvice
class BadRequestExceptionHandler{
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onBadRequest(ex: MissingServletRequestParameterException): Any {
        return "В запросе должны содержаться оба параметра - и city, и country."
    }
}