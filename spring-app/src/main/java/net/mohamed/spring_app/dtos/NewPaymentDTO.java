package net.mohamed.spring_app.dtos;

import lombok.*;
import net.mohamed.spring_app.entities.PaymentType;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewPaymentDTO {
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private String studentCode;
}
