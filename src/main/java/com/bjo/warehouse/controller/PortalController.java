package com.bjo.warehouse.controller;

import com.bjo.warehouse.model.Inventario;
import com.bjo.warehouse.repository.InventarioRepository;
import com.bjo.warehouse.repository.NativeRepository;
import com.bjo.warehouse.repository.TrasladoRepository;
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

    @Autowired
    private TrasladoRepository trasladoRepository;

    @Autowired
    private NativeRepository nativeRepository;

    private static final Long ESTADO_PREPARACION = 1l;

    private static final Long ESTADO_RECOGIDO = 2l;

    private static final Long ESTADO_TRANSITO = 3l;

    private static final Long ESTADO_RECEPCIONADO = 4l;

    // BJO
    @GetMapping("")
    public String portal(Model model) {
        model.addAttribute("detalles", nativeRepository.getPortalDetallesTraslados());
        model.addAttribute("inventarios", nativeRepository.getPortalInventarios());
        model.addAttribute("trasladosPreparacion", trasladoRepository.countByEstadoId(ESTADO_PREPARACION));
        model.addAttribute("trasladosRecogido", trasladoRepository.countByEstadoId(ESTADO_RECOGIDO));
        model.addAttribute("trasladosTransito", trasladoRepository.countByEstadoId(ESTADO_TRANSITO));
        model.addAttribute("trasladosRecepcionado", trasladoRepository.countByEstadoId(ESTADO_RECEPCIONADO));
        return "portal";
    }

    @GetMapping("/inventarios")
    public String inventarios(Model model) {
        List<Inventario> inventarios = inventarioRepository.findAll();
        model.addAttribute("inventarios", inventarios);
        return "inventarios";
    }
}
