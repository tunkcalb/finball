package com.example.backend.dto.inventory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkinInfo {

    private Long id;
    private String image;
    private String name;
    private boolean isSelected;

}
