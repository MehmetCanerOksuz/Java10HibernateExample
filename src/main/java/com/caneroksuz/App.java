package com.caneroksuz;

import com.caneroksuz.controller.PostController;
import com.caneroksuz.controller.UserController;
import com.caneroksuz.repository.entity.Address;
import com.caneroksuz.repository.entity.Name;
import com.caneroksuz.repository.entity.Post;
import com.caneroksuz.repository.entity.User;
import com.caneroksuz.repository.enums.EAddressType;
import com.caneroksuz.repository.enums.EGender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        UserController userController = new UserController();

        List<String> interest1= List.of("Müzik", "Dans");
        List<String> interest2= List.of("Sinema", "Tiyatro");
        // 1. Map
        Map<EAddressType, Address> map1= new HashMap<>();
        map1.put(EAddressType.HOME,Address.builder().city("Ankara").country("Türkiye").build());
        map1.put(EAddressType.WORK,Address.builder().city("İstanbul").country("Türkiye").build());

        // 2. Map
        Map<EAddressType, Address> map2= new HashMap<>();
        map2.put(EAddressType.HOME,Address.builder().city("İzmir").country("Türkiye").build());
        map2.put(EAddressType.WORK,Address.builder().city("Antalya").country("Türkiye").build());

        User user= User.builder()
                .name(Name.builder().firstName("Mustafa").lastName("Ozturk").build())
                .username("musty")
                .password("12345")
                .gender(EGender.MAN)
                .interests(interest1)
                .addresses(map1)
                .age(25)
                .build();
        User user2= User.builder()
                .name(Name.builder().firstName("Caner").middleName("Mehmet").lastName("Oksuz").build())
                .username("caner")
                .password("12345646646546")
                .gender(EGender.MAN)
                .interests(interest2)
                .addresses(map2)
                .age(28)
                .build();


        System.out.println(userController.save(user));
        System.out.println(userController.save(user2));

        PostController postController = new PostController();
        Post post1 = Post.builder()
                .content("Deneme123")
                .userId(user.getId())
                .build();

        System.out.println(postController.save(post1));

    }
}
