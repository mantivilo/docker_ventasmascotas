package ventasmascotas.ventasmascotas.service;

import ventasmascotas.ventasmascotas.model.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> getAllVentas();
    Optional<Venta> getVentaById(Long id);
    Venta createVenta (Venta venta);
    Venta updateVenta(Long id, Venta venta);
    void deleteVenta(Long id);
}