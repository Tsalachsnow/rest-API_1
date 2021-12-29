//package com.example.rest_api2.controllers;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @GetMapping
//    public String getUsers1(@RequestParam(value = "page") int pageno, @RequestParam(value = "limit") int limitno){
//        return "http Get request was sent for page: "+pageno+" and limit is: "+limitno;
//    }
//
//
////    @GetMapping
////    public String getUsers(){
////        return "http Get request was sent";
////    }
//
//
//    @GetMapping(path = "/{userID}")
//    public String getUser(@PathVariable String userID){
//        return "http Get request was sent for userID: " + userID;
//    }
//
//    @PostMapping
//    public String createUser(){
//        return "http Post request was sent";
//    }
//
//    @PutMapping
//    public String updateUser(){
//        return "http Put request was sent";
//    }
//
//
//    @DeleteMapping
//    public String deleteUser(){
//        return "http Delete request was sent";
//    }
//
//}
