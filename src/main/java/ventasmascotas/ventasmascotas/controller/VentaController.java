package ventasmascotas.ventasmascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ventasmascotas.ventasmascotas.model.Venta;
import ventasmascotas.ventasmascotas.service.VentaService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/ventas")
public class VentaController {

    private static final Logger log = LoggerFactory.getLogger(VentaController.class);

    @Autowired
    private VentaService ventaService;

    //http://localhost:8080/ventas
    /*@GetMapping
    public List<Venta> getAllVentas(){
        return ventaService.getAllVentas();
    }*/

    @GetMapping
    public CollectionModel<EntityModel<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.getAllVentas();
        log.info("GET /ventas");
        log.info("Retornando todas las ventas");
        List<EntityModel<Venta>> ventaResources = ventas.stream()
            .map( venta -> EntityModel.of(venta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(venta.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVentas());
        CollectionModel<EntityModel<Venta>> resources = CollectionModel.of(ventaResources, linkTo.withRel("ventas"));

        return resources;
    }
    
    //http://localhost:8080/ventas/1
    /*@GetMapping("/{id}")
    public Optional<Venta> getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id);
    }*/

    @GetMapping("/{id}")
    public EntityModel<Venta> getVentaById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.getVentaById(id);

        if (venta.isPresent()) {
            return EntityModel.of(venta.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVentas()).withRel("all-ventas"));
        } else {
            throw new VentaNotFoundException("Venta not found with id: " + id);
        }
    }

    /*@PostMapping
    public Venta createVenta(@RequestBody Venta venta) {
        return ventaService.createVenta(venta);
    }*/

    @PostMapping
    public EntityModel<Venta> createVenta(@Validated @RequestBody Venta venta) {
        Venta createdVenta = ventaService.createVenta(venta);
            return EntityModel.of(createdVenta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(createdVenta.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVentas()).withRel("all-ventas"));

    }
   
    /*@PutMapping("/{id}")
    public Venta updateVenta(@PathVariable Long id, @RequestBody Venta venta) {
        return ventaService.updateVenta(id, venta);
    }*/
    @PutMapping("/{id}")
    public EntityModel<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta venta) {
        Venta updatedVenta = ventaService.updateVenta(id, venta);
        return EntityModel.of(updatedVenta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVentas()).withRel("all-ventas"));

    }

    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable Long id){
        ventaService.deleteVenta(id);
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}

