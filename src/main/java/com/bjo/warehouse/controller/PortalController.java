package com.bjo.warehouse.controller;

import com.bjo.warehouse.dto.ServiceResponsePortalBodegasDTO;
import com.bjo.warehouse.dto.ServiceResponsePortalBodegaDTO;
import com.bjo.warehouse.dto.common.PortalBodegaDTO;
import com.bjo.warehouse.model.Inventario;
import com.bjo.warehouse.model.Producto;
import com.bjo.warehouse.repository.InventarioRepository;
import com.bjo.warehouse.repository.NativeRepository;
import com.bjo.warehouse.repository.ProductoRepository;
import com.bjo.warehouse.repository.TrasladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/portal")
@Controller
public class PortalController {

  @Autowired private InventarioRepository inventarioRepository;

  @Autowired private ProductoRepository productoRepository;

  @Autowired private TrasladoRepository trasladoRepository;

  @Autowired private NativeRepository nativeRepository;

  private static final Long ESTADO_PREPARACION = 1l;

  private static final Long ESTADO_RECOGIDO = 2l;

  private static final Long ESTADO_TRANSITO = 3l;

  private static final Long ESTADO_RECEPCIONADO = 4l;

  // BJO
  @GetMapping("")
  public String portal(Model model) {
    model.addAttribute("detalles", nativeRepository.getPortalDetallesTraslados());
    model.addAttribute("inventarios", nativeRepository.getPortalInventarios());
    model.addAttribute(
        "trasladosPreparacion", trasladoRepository.countByEstadoId(ESTADO_PREPARACION));
    model.addAttribute("trasladosRecogido", trasladoRepository.countByEstadoId(ESTADO_RECOGIDO));
    model.addAttribute("trasladosTransito", trasladoRepository.countByEstadoId(ESTADO_TRANSITO));
    model.addAttribute(
        "trasladosRecepcionado", trasladoRepository.countByEstadoId(ESTADO_RECEPCIONADO));
    return "portal";
  }

  @GetMapping("/productos")
  public String productos(Model model) {
    List<Producto> productos = productoRepository.findAll();
    model.addAttribute("productos", productos);
    return "productos";
  }

  @GetMapping("/bodegas")
  public String bodegas(Model model) {
    List<PortalBodegaDTO> bodegas = nativeRepository.getPortalBodegas();
    model.addAttribute("bodegas", bodegas);
    return "bodegas";
  }

  @GetMapping("/bodega/{id}")
  public ResponseEntity<ServiceResponsePortalBodegaDTO> bodegas(@PathVariable Long id) {
    if (id > 0) {
      List<PortalBodegaDTO> bodegas = nativeRepository.getPortalBodegas(id);
      if (bodegas.isEmpty()) {
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(new ServiceResponsePortalBodegaDTO(bodegas.get(0)));
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/inventarios")
  public String inventarios(Model model) {
    List<Inventario> inventarios = inventarioRepository.findAll();
    model.addAttribute("inventarios", inventarios);
    return "inventarios";
  }
}
