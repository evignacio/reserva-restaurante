package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.ReviewDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateReviewUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class ReviewController {

    private final CreateReviewUseCase createReviewUseCase;

    public ReviewController(CreateReviewUseCase createReviewUseCase) {
        this.createReviewUseCase = createReviewUseCase;
    }

    @PostMapping("review")
    public ResponseEntity<ServiceResponse<Void>> createReview(@RequestBody ReviewDTO reviewDTO) {
        createReviewUseCase.execute(reviewDTO);
        return ResponseEntity.ok(ServiceResponse.build());
    }
}
