package ventasmascotas.ventasmascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ventasmascotas.ventasmascotas.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{
    
}

