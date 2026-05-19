package org.example.vodafoneproject.controllers;


import org.example.vodafoneproject.dtos.UsageItemDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//using this controller to mimic an external endpoint by providing static values.

@RestController
@RequestMapping("/usage")
public class ExternalUsageController {

    @GetMapping("/{id}")
    public List<UsageItemDto> getUsage(
            @PathVariable Long id) {
        //some static usage values just for the exercise

       if(id == 1){
           UsageItemDto min = new UsageItemDto();
           min.setType("min");
           min.setCurrentSpent(100);
           min.setTotal(500);

           UsageItemDto sms = new UsageItemDto();
           sms.setType("sms");
           sms.setCurrentSpent(50);
           sms.setTotal(100);

           UsageItemDto internet = new UsageItemDto();
           internet.setType("internet");
           internet.setCurrentSpent(2048);
           internet.setTotal(5120);

           return List.of(min, sms, internet);
       }else{
           {
               UsageItemDto min = new UsageItemDto();
               min.setType("min");
               min.setCurrentSpent(340);
               min.setTotal(1000);

               UsageItemDto sms = new UsageItemDto();
               sms.setType("sms");
               sms.setCurrentSpent(24);
               sms.setTotal(120);

               UsageItemDto internet = new UsageItemDto();
               internet.setType("internet");
               internet.setCurrentSpent(200);
               internet.setTotal(7000);

               return List.of(min, sms, internet);
           }
       }
    }
}