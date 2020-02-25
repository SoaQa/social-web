package ru.starry_sky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.starry_sky.utils.LocaleFlag;
import ru.starry_sky.utils.enums.LocaleEnum;

import java.util.Locale;

@RestController
@RequestMapping(value = "/index")
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    private ResponseEntity hello(@RequestParam(required = false) String lang){
        if (lang != null)
        {
            LocaleFlag.setLocale(lang);
        return ResponseEntity.ok(messageSource.getMessage("helloWord", null, new Locale(LocaleFlag.getLocale())));
        }
        else{
            HttpHeaders h = new HttpHeaders();
            h.add("Content-type", "text/html;charset=UTF-8");
            return ResponseEntity.ok()
                    .headers(h)
                    .body(messageSource.getMessage("helloWord", null, new Locale(LocaleFlag.getLocale())));
        }
    }
}
