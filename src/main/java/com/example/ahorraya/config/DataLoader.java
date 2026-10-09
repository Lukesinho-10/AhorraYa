package com.example.ahorraya.config;


import com.example.ahorraya.entities.*;
import com.example.ahorraya.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoSedeRepository productoSedeRepository;

    @Override
    public void run(String... args) {

        RolEntity rolCliente = new RolEntity();
        rolCliente.setNombre("CLIENTE");
        rolCliente.setDescripcion("Usuario que compra en la plataforma");
        rolRepository.save(rolCliente);

        RolEntity rolAdmin = new RolEntity();
        rolAdmin.setNombre("ADMIN");
        rolAdmin.setDescripcion("Administrador del sistema");
        rolRepository.save(rolAdmin);

        String[] descripciones = {"Muy malo", "Malo", "Regular", "Bueno", "Muy bueno"};
        for (int i = 1; i <= 5; i++) {
            CalificacionEntity calificacion = new CalificacionEntity();
            calificacion.setValor(i);
            calificacion.setDescripcion(descripciones[i - 1]);
            calificacionRepository.save(calificacion);
        }

        CategoriaProductoEntity categoriaLacteos = new CategoriaProductoEntity();
        categoriaLacteos.setNombre("Lácteos");
        categoriaProductoRepository.save(categoriaLacteos);

        CategoriaProductoEntity categoriaAbarrotes = new CategoriaProductoEntity();
        categoriaAbarrotes.setNombre("Abarrotes");
        categoriaProductoRepository.save(categoriaAbarrotes);

        MarcaEntity marcaGloria = new MarcaEntity();
        marcaGloria.setNombre("Gloria");
        marcaRepository.save(marcaGloria);

        MarcaEntity marcaLaive = new MarcaEntity();
        marcaLaive.setNombre("Laive");
        marcaRepository.save(marcaLaive);

        EmpresaEntity empresaPlazaVea = new EmpresaEntity();
        empresaPlazaVea.setNombre("Plaza Vea");
        empresaPlazaVea.setDescripcion("Supermercado Plaza Vea");
        empresaRepository.save(empresaPlazaVea);

        EmpresaEntity empresaTottus = new EmpresaEntity();
        empresaTottus.setNombre("Tottus");
        empresaTottus.setDescripcion("Supermercado Tottus");
        empresaRepository.save(empresaTottus);

        SedeEntity sedePlazaVea = new SedeEntity();
        sedePlazaVea.setEmpresa(empresaPlazaVea);
        sedePlazaVea.setNombre("Plaza Vea San Miguel");
        sedePlazaVea.setDireccion("Av. La Marina 2000");
        sedePlazaVea.setDistrito("San Miguel");
        sedeRepository.save(sedePlazaVea);

        SedeEntity sedeTottus = new SedeEntity();
        sedeTottus.setEmpresa(empresaTottus);
        sedeTottus.setNombre("Tottus Miraflores");
        sedeTottus.setDireccion("Av. Benavides 1500");
        sedeTottus.setDistrito("Miraflores");
        sedeRepository.save(sedeTottus);

        ProductoEntity productoLeche = new ProductoEntity();
        productoLeche.setCategoria(categoriaLacteos);
        productoLeche.setMarca(marcaGloria);
        productoLeche.setNombre("Leche Evaporada Gloria");
        productoLeche.setDescripcion("Leche evaporada entera 400g");
        productoLeche.setUnidadMedida(UnidadMedidaEnum.UNIDAD); // ajusta al valor real de tu enum
        productoRepository.save(productoLeche);

        ProductoEntity productoYogurt = new ProductoEntity();
        productoYogurt.setCategoria(categoriaLacteos);
        productoYogurt.setMarca(marcaLaive);
        productoYogurt.setNombre("Yogurt Laive Fresa");
        productoYogurt.setDescripcion("Yogurt bebible 1L");
        productoYogurt.setUnidadMedida(UnidadMedidaEnum.LITRO); // ajusta al valor real de tu enum
        productoRepository.save(productoYogurt);

        ProductoSedeEntity psLechePlazaVea = new ProductoSedeEntity();
        psLechePlazaVea.setProducto(productoLeche);
        psLechePlazaVea.setSede(sedePlazaVea);
        psLechePlazaVea.setPrecioActual(new BigDecimal("4.50"));
        psLechePlazaVea.setDisponible(true);
        psLechePlazaVea.setFechaActualizacion(LocalDate.now());
        productoSedeRepository.save(psLechePlazaVea);

        ProductoSedeEntity psLecheTottus = new ProductoSedeEntity();
        psLecheTottus.setProducto(productoLeche);
        psLecheTottus.setSede(sedeTottus);
        psLecheTottus.setPrecioActual(new BigDecimal("4.20"));
        psLecheTottus.setDisponible(true);
        psLecheTottus.setFechaActualizacion(LocalDate.now());
        productoSedeRepository.save(psLecheTottus);

        ProductoSedeEntity psYogurtTottus = new ProductoSedeEntity();
        psYogurtTottus.setProducto(productoYogurt);
        psYogurtTottus.setSede(sedeTottus);
        psYogurtTottus.setPrecioActual(new BigDecimal("6.90"));
        psYogurtTottus.setDisponible(true);
        psYogurtTottus.setFechaActualizacion(LocalDate.now());
        productoSedeRepository.save(psYogurtTottus);

        System.out.println(">>> Datos de prueba cargados correctamente (AhorraYa!)");
    }

}
