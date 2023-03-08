package com.CentraleAchat.offerservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bill extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeBill;
    private String idClient;
    private String idCompany;
    private String idSupplier;
    @NotEmpty(message = "TVA is mandatory")
    private float TVA;
    @NotEmpty(message = "HTVA is mandatory")
    private float HTVA;
    @NotEmpty(message = "totalTTC is mandatory")
    private float totalTTC;
    @Enumerated(EnumType.STRING)
    private BillType billType;
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;


    @JsonIgnore
    @OneToOne(mappedBy = "bill")
    private Order orderr;

    @OneToMany(mappedBy = "bil")
    private List<AdditionalCharge> additionalCharge;

}