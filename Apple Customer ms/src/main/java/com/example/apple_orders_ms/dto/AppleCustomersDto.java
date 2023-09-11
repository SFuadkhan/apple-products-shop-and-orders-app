package com.example.apple_orders_ms.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class AppleCustomersDto {
    private Long id;
    @Size(min = 2,max = 20,message = "Input name")
    @NotBlank
    private String name;
    @Size(min = 2,max = 20,message = "Input surname")
    @NotBlank
    private String surname;
    @NotNull
    private Long orders;
    @NotNull
    private Long purchases;
}
