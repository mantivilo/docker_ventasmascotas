package ventasmascotas.ventasmascotas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ventasmascotas.ventasmascotas.model.Venta;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VentaRepositoryTest {
    @Autowired
    private VentaRepository ventaRepository;

    @Test
    public void guardarVentaTest() {
        Venta venta = new Venta();
        venta.setDescripcion("acuario de 30lts");

        Venta resultado = ventaRepository.save(venta);

        assertNotNull(resultado.getId());
        assertEquals("acuario de 30lts", resultado.getDescripcion());
    }

}
