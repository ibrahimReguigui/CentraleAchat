package com.CentraleAchat.offerservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
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
    private Long idClient;
    private Long idSupplier;
  //  @NotEmpty(message = "TVA is mandatory")
    private float TVA;
  //  @NotEmpty(message = "HTVA is mandatory")
    private float HTVA;
 //   @NotEmpty(message = "totalTTC is mandatory")
    private float totalTTC;

    @Enumerated(EnumType.STRING)
    private BillType billType = BillType.NORMAL;

    private Date DateBill;


    @OneToMany(mappedBy = "bill")
    private List<Livraison> livraisons;


    @OneToOne(mappedBy = "bill")
    private Order orderr;

    @OneToMany(mappedBy = "bil")
    private List<AdditionalCharge> additionalCharge;



}