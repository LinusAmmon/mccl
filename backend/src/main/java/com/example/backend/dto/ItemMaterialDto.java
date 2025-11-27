package com.example.backend.dto;

import java.util.UUID;

public record ItemMaterialDto(
        UUID resourceId,
        Long amount
) {
}
