package com.app.cinema.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "premium_users")
public class PremiumUser extends User {


    @OneToMany(mappedBy = "premiumUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;

    @OneToOne(mappedBy = "premiumUser")
    private LoyaltyCard loyaltyCard;

    @OneToMany(mappedBy = "premiumUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<FamilyCard> familyCards;





}
