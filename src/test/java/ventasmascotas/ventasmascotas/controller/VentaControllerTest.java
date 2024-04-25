package ventasmascotas.ventasmascotas.controller;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ventasmascotas.ventasmascotas.model.Venta;
import ventasmascotas.ventasmascotas.service.VentaServiceImpl;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaServiceImpl ventaServicioMock;

    private List<Venta> ventas;

    @BeforeEach
    public void setUp() {
        Venta venta1 = new Venta();
        venta1.setDescripcion("Cancelada");
        venta1.setId(1L);
        Venta venta2 = new Venta();
        venta2.setDescripcion("Pendiente");
        venta2.setId(2L);
        ventas = Arrays.asList(venta1, venta2);
    }

    @AfterEach
    public void tearDown() {
        ventas = null;
    }

    @Test
    public void obtenerTodosTest() throws Exception {
        when(ventaServicioMock.getAllVentas()).thenReturn(ventas);

        mockMvc.perform(MockMvcRequestBuilders.get("/ventas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descripcion", Matchers.is("Cancelada")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].descripcion", Matchers.is("Pendiente")));
    }
}


