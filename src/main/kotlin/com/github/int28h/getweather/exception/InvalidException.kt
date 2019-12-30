package com.github.int28h.getweather.exception

import org.springframework.validation.Errors

data class InvalidException(val errors: Errors?) : RuntimeException()

object InvalidRequest {
    fun check(errors: Errors) {
        if (errors.hasFieldErrors())
            throw InvalidException(errors)
    }
}