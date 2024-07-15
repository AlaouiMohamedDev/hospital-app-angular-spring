package net.mohamed.spring_app.dtos;
import lombok.*;
import net.mohamed.spring_app.entities.PaymentStatus;
import net.mohamed.spring_app.entities.PaymentType;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status = PaymentStatus.CREATED;
}
