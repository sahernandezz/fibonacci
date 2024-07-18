package com.proteccion.fibonacci.web.controller;

import com.proteccion.fibonacci.persistence.entity.Execution;
import com.proteccion.fibonacci.service.IEmailService;
import com.proteccion.fibonacci.service.IExecutionService;
import com.proteccion.fibonacci.service.IFibonacciService;
import com.proteccion.fibonacci.web.controller.request.TimeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/fibonacci/v1")
public class FibonacciController {

    private final IFibonacciService fibonacciService;
    private final IExecutionService executionService;
    private final IEmailService emailService;

    public FibonacciController(IFibonacciService fibonacciService, IExecutionService executionService, IEmailService emailService) {
        this.fibonacciService = fibonacciService;
        this.executionService = executionService;
        this.emailService = emailService;
    }

    @Operation(
            summary = "Generar serie Fibonacci",
            description = "Genera una serie de Fibonacci basada en la hora proporcionada y devuelve la serie. La serie se inicia con dos semillas derivadas de los minutos de la hora proporcionada y se extiende hasta el número especificado por los segundos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Proporciona la hora en formato HH:mm:ss para calcular las semillas iniciales y la longitud de la serie Fibonacci.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TimeRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de solicitud",
                                    summary = "Ejemplo de cuerpo de solicitud",
                                    value = "{\"time\": \"20:42:08\"}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Serie Fibonacci generada exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de respuesta",
                                            summary = "Ejemplo de serie Fibonacci generada",
                                            value = "[1, 1, 2, 3, 5, 8]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Parámetros inválidos",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/generate")
    public ResponseEntity<List<BigInteger>> generateFibonacci(@RequestBody @Valid TimeRequest timeRequest) {
        try {
            int seed1 = timeRequest.getMinutes() % 10;
            int seed2 = timeRequest.getMinutes() / 10;
            int count = timeRequest.getSeconds();
            List<BigInteger> fibonacciSeries = fibonacciService.generateFibonacci(seed1, seed2, count);

            executionService.saveExecution(timeRequest.getTime(), fibonacciSeries);

            String subject = "Prueba Técnica — Sergio Alejandro Hernandez Zambrano";
            String text = "Hora de generación: " + timeRequest.getTime() + "\nSerie Fibonacci: " + fibonacciSeries.toString() + "\nMensaje personalizado";

            emailService.sendEmail("didier.correa@proteccion.com.co", subject, text);
            emailService.sendEmail("correalondon@gmail.com", subject, text);

            return ResponseEntity.ok(fibonacciSeries);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @Operation(
            summary = "Obtener lista de ejecuciones",
            description = "Recupera y devuelve una lista de todas las ejecuciones de generación de series Fibonacci, incluyendo la hora de generación y la serie generada.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de ejecuciones recuperada exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Execution.class)),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de respuesta",
                                            summary = "Ejemplo de lista de ejecuciones",
                                            value = "[{\"id\":1,\"time\":\"20:42:08\",\"series\":[1, 1, 2, 3, 5, 8]}]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/executions")
    public List<Execution> getAllExecutions() {
        return executionService.getAllExecutions();
    }
}
