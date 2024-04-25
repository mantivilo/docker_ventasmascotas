package ventasmascotas.ventasmascotas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ventasmascotas.ventasmascotas.model.Venta;
import ventasmascotas.ventasmascotas.repository.VentaRepository;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {
    @InjectMocks
    private VentaServiceImpl ventaServicio;

    @Mock
    private VentaRepository ventaRepositoryMock;

    @Test
    public void guardarVentaTest() {

        Venta venta = new Venta();
        venta.setDescripcion("Productos de perro");

        when(ventaRepositoryMock.save(any())).thenReturn(venta);

        Venta resultado = ventaServicio.createVenta(venta);

        assertEquals("Productos de perro", resultado.getDescripcion());
    }
}
