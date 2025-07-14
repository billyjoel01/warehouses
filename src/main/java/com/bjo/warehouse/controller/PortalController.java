package com.bjo.warehouse.controller;

import com.bjo.warehouse.model.Inventario;
import com.bjo.warehouse.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/portal")
@Controller
public class PortalController {

    @Autowired
    private InventarioRepository inventarioRepository;

    // BJO
    @GetMapping("")
    public String portal(Model model) {
        List<Inventario> inventarios = inventarioRepository.findAll();
        model.addAttribute("inventarios", inventarios);
        return "portal";
    }

    @GetMapping("/inventarios")
    public String inventarios(Model model) {
        List<Inventario> inventarios = inventarioRepository.findAll();
        model.addAttribute("inventarios", inventarios);
        return "inventarios";
    }
}
