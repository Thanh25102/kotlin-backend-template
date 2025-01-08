package com.khipster.template.khipstertemplate

import com.khipster.template.khipstertemplate.errors.AuthenticationException
import com.khipster.template.khipstertemplate.errors.BadRequestAlertException
import com.khipster.template.khipstertemplate.errors.CONSTRAINT_VIOLATION_TYPE
import com.khipster.template.khipstertemplate.errors.DEFAULT_TYPE
import com.khipster.template.khipstertemplate.errors.ERR_CONCURRENCY_FAILURE
import com.khipster.template.khipstertemplate.errors.ERR_VALIDATION
import com.khipster.template.khipstertemplate.errors.EmailAlreadyUsedException
import com.khipster.template.khipstertemplate.errors.FIELD_ERRORS_KEY
import com.khipster.template.khipstertemplate.errors.FieldErrorVM
import com.khipster.template.khipstertemplate.errors.InvalidPasswordException
import com.khipster.template.khipstertemplate.errors.LoginAlreadyUsedException
import com.khipster.template.khipstertemplate.errors.MESSAGE_KEY
import com.khipster.template.khipstertemplate.errors.PATH_KEY
import com.khipster.template.khipstertemplate.errors.UsernameAlreadyUsedException
import com.khipster.template.khipstertemplate.errors.VIOLATIONS_KEY
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.dao.ConcurrencyFailureException
import org.springframework.dao.DataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.ProblemBuilder
import org.zalando.problem.Status
import org.zalando.problem.StatusType
import org.zalando.problem.spring.web.advice.ProblemHandling
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait
import tech.jhipster.config.JHipsterConstants
import tech.jhipster.web.util.HeaderUtil
import java.net.URI

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807).
 */
@RestControllerAdvice
class ExceptionTranslator(private val env: Environment) : ProblemHandling, SecurityAdviceTrait {

    @Value("\${jhipster.clientApp.name}")
    private val applicationName: String? = null

    /**
     * Post-process the Problem payload to add the message key for the front-end if needed.
     */

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? {
        val result = ex.bindingResult
        val fieldErrors = result.fieldErrors.map {
            FieldErrorVM(
                it.objectName.replaceFirst(Regex("DTO$"), ""),
                it.field,
                if (StringUtils.isNotBlank(it.defaultMessage)) it.defaultMessage else it.code
            )
        }

        val problem = Problem.builder()
            .withType(CONSTRAINT_VIOLATION_TYPE)
            .withTitle("Method argument not valid")
            .withStatus(defaultConstraintViolationStatus())
            .with(MESSAGE_KEY, ERR_VALIDATION)
            .with(FIELD_ERRORS_KEY, fieldErrors)
            .build()
        return create(ex, problem, request)
    }

    @ExceptionHandler(Exception::class)
    fun handleAnyException(
        ex: Exception,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? {
        val problem = Problem.builder()
            .withStatus(Status.INTERNAL_SERVER_ERROR)
            .withTitle("Internal Server Error")
            .withDetail("An unexpected error occurred")
            .build()
        return create(ex, problem, request)
    }


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? {
        println("IllegalArgumentException Go to Here: $ex")
        val problem = Problem.builder()
            .withStatus(Status.INTERNAL_SERVER_ERROR)
            .withTitle("Internal Server Error")
            .withDetail("An unexpected error occurred")
            .build()
        return create(ex, problem, request)
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleGlobalException(
        ex: Exception,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? {
        println("Exception Go to Here: $ex")
        val problem = Problem.builder()
            .withStatus(Status.INTERNAL_SERVER_ERROR)
            .withTitle("Internal Server Error")
            .withDetail("An unexpected error occurred")
            .build()
        return create(ex, problem, request)
    }

    @ExceptionHandler
    fun handleEmailAlreadyUsedException(
        ex: EmailAlreadyUsedException,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? {
        val problem = EmailAlreadyUsedException()
        return create(
            problem,
            request,
            HeaderUtil.createFailureAlert(applicationName, true, problem.entityName, problem.errorKey, problem.message)
        )
    }

    @ExceptionHandler
    fun handleUsernameAlreadyUsedException(
        ex: UsernameAlreadyUsedException,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? {
        val problem = LoginAlreadyUsedException()
        return create(
            problem,
            request,
            HeaderUtil.createFailureAlert(applicationName, true, problem.entityName, problem.errorKey, problem.message)
        )
    }

    @ExceptionHandler
    fun handleInvalidPasswordException(
        ex: InvalidPasswordException,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? {
        return create(InvalidPasswordException(), request)
    }

    @ExceptionHandler
    fun handleBadRequestAlertException(
        ex: BadRequestAlertException,
        request: NativeWebRequest
    ): ResponseEntity<Problem>? =
        create(
            ex, request,
            HeaderUtil.createFailureAlert(applicationName, true, ex.entityName, ex.errorKey, ex.message)
        )

    @ExceptionHandler
    fun handleConcurrencyFailure(ex: ConcurrencyFailureException, request: NativeWebRequest): ResponseEntity<Problem>? {
        val problem = Problem.builder()
            .withStatus(Status.CONFLICT)
            .with(MESSAGE_KEY, ERR_CONCURRENCY_FAILURE)
            .build()
        return create(ex, problem, request)
    }

    override fun prepare(throwable: Throwable, status: StatusType, type: URI): ProblemBuilder {
        val activeProfiles = env.activeProfiles
        var detail = throwable.message
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            detail = when (throwable) {
                is HttpMessageConversionException -> "Unable to convert http message"
                is DataAccessException -> "Failure during data access"
                else -> {
                    if (containsPackageName(throwable.message)) {
                        "Unexpected runtime exception"
                    } else {
                        throwable.message
                    }
                }
            }
        }
        return Problem.builder()
            .withType(type)
            .withTitle(status.reasonPhrase)
            .withStatus(status)
            .withDetail(detail)
            .withCause(throwable.cause.takeIf { isCausalChainsEnabled }?.let { toProblem(it) })
    }

    private fun containsPackageName(message: String?) =
        listOf("org.", "java.", "net.", "javax.", "com.", "io.", "de.", "com.ktemplate.hsv").any { it == message }
}