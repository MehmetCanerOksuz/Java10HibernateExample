package com.caneroksuz.repository.entity;

import com.caneroksuz.repository.enums.EAddressType;
import com.caneroksuz.repository.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Name name;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    @Size(min =4, max=32, message ="Şifre 4 ile 32 karakter arası olmalıdır.." )
    private String password;
    @Transient //bu özellik artık databasede gorunmeyecek..
    private int age;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EGender gender=EGender.MAN;


    /*

    Her kullanıcının birden fazla ilgi alanı olsun ve
    her bir ilgi alanı turu String olarak tutalım

     */

    @ElementCollection// list olduğu için bu şekilde tutabiliyoruz.. YENİ BİR TABLO OLUSTURUYOR..
    @Builder.Default
    List<String> interests=new ArrayList<>();

    /*
        Bir kullanıcının birden fazla adresi olabilir
        ve adresleri burada adres turlerine gore tutalım
        iş: address1
        ev: address2
     */

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    Map<EAddressType,Address> addresses;

    int postCount;
}
