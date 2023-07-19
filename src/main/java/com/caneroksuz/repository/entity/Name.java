package com.caneroksuz.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/*

firstname
middlename
lastname
bu özelliklerle name sınıfını olusturup
user ile olan ilişkisini kurup database e yeni bir veri ekleyelim

 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;
}
