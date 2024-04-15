package ventasmascotas.ventasmascotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ventasmascotas.ventasmascotas.model.Venta;
import ventasmascotas.ventasmascotas.repository.VentaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService{
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta createVenta(Venta venta){
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Long id, Venta venta){
        if(ventaRepository.existsById(id)){
            venta.setId(id);
            return ventaRepository.save(venta);
        }else{
            return null;
        }

    }

    @Override
    public void deleteVenta(Long Id){
        ventaRepository.deleteById(Id);
    }

}