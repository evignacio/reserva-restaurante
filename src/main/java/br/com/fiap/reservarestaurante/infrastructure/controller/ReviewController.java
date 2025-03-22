package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.ReviewDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateReviewUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
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

    @PostMapping()
    public ResponseEntity<ServiceResponse<Void>> createReview(@RequestBody ReviewDTO reviewDTO) {
        createReviewUseCase.execute(reviewDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
}
