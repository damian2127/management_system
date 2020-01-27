package com.example.controller;

import com.example.model.Item;
import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/accountant")
public class AccountantController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value ="/list_items",method = RequestMethod.GET)
    public ModelAndView list_items(){
        ModelAndView view = new ModelAndView();
        List<Item> listOfItems = itemService.getAll();
        view.addObject("listOfItems", listOfItems);
        view.setViewName("/accountant/list_items");
        return view;
    }

    @RequestMapping(value="/add_item", method = RequestMethod.GET)
    public ModelAndView add_item(){
        ModelAndView modelAndView = new ModelAndView();
        Item item = new Item();
        modelAndView.addObject("item", item);
        modelAndView.setViewName("/accountant/add_item");
        return modelAndView;
    }

    @RequestMapping(value = "/add_item", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Item item, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("accountant/add_item");
        } else {
            itemService.saveItem(item);
            modelAndView.addObject("successMessage", "Item has been added successfully");
            modelAndView.addObject("item", new Item());
            modelAndView.setViewName("accountant/add_item");
        }
        return modelAndView;
    }



}