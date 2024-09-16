package com.emazon.transaction.infraestructure.input.rest;

import com.emazon.transaction.application.dtos.SupplyRequest;
import com.emazon.transaction.application.handler.ISupplyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.emazon.transaction.infraestructure.util.InfraestructureRestControllerConstants.*;


@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SupplyRestController {
    private final ISupplyHandler supplyHandler;

    @Operation(summary = UPDATE_SUPPLY_OPERATION_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_CODE_SUCCESS, description = RESPONSE_DESCRIPTION_UPDATED_SUCCESSFUL, content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_UNAUTHORIZED, description = RESPONSE_DESCRIPTION_UNAUTHORIZED, content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = SUPPLY_NOT_FOUND_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = INVALID_REQUEST_DESCRIPTION, content = @Content)
    })
    @PutMapping
    ResponseEntity<Void> addSupply(@RequestBody SupplyRequest supplyRequest) {
        supplyHandler.addSupply(supplyRequest);
        return ResponseEntity.ok().build();
    }

}
