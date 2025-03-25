package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.ReviewDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateReviewUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final CreateReviewUseCase createReviewUseCase;

    public ReviewController(CreateReviewUseCase createReviewUseCase) {
        this.createReviewUseCase = createReviewUseCase;
    }

    @Operation(summary = "Avaliar restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<ServiceResponse<Void>> createReview(@RequestBody ReviewDTO reviewDTO) {
        createReviewUseCase.execute(reviewDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
}
