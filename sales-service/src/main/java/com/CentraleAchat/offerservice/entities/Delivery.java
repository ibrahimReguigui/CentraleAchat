package com.CentraleAchat.offerservice.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idDelivery; // Clé primaire

    @NotNull
    String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    StatusDelivery  statusDelivery;

    @NotNull
    @Temporal(TemporalType.DATE)
    Date dateLivraisonPrevue;


    @Temporal(TemporalType.DATE)
    Date dateLivraison;






}
