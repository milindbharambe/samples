package com.milind.sample.oauth.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sample")
public class SampleController {

    @GetMapping("/normal")
    public String sayNormalHi() {
        return "normal Hi !!";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('USER')")
    public String sayPrivateHi() {
        return "private user Hi !!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String sayAdminHi() {
        return "Admin Hi !!";
    }

}
