package pe.upeu.ecorecicla;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.upeu.ecorecicla.dominio.Pesaje;
import pe.upeu.ecorecicla.dominio.PuntoAcopio;
import pe.upeu.ecorecicla.repositorio.PesajeRepository;
import pe.upeu.ecorecicla.repositorio.PuntoAcopioRepository;
import pe.upeu.ecorecicla.repositorio.UsuarioRepository;
import pe.upeu.ecorecicla.seguridad.Rol;
import pe.upeu.ecorecicla.seguridad.Usuario;

@SpringBootApplication
public class Aplicacion {
    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UsuarioRepository usuarioRepo, PuntoAcopioRepository puntoRepo, PesajeRepository pesajeRepo, PasswordEncoder encoder) {
        return args -> {
            // 1. Crear Usuarios Semilla
            if (usuarioRepo.count() == 0) {
                Usuario op = new Usuario();
                op.setUsername("operario1");
                op.setPassword(encoder.encode("Eco2026*"));
                op.setRol(Rol.OPERARIO);

                Usuario sup = new Usuario();
                sup.setUsername("supervisor1");
                sup.setPassword(encoder.encode("Eco2026*"));
                sup.setRol(Rol.SUPERVISOR);

                Usuario adm = new Usuario();
                adm.setUsername("admin");
                adm.setPassword(encoder.encode("Eco2026*"));
                adm.setRol(Rol.ADMINISTRADOR);

                // Usuario de otro operario para probar RN-02
                Usuario op2 = new Usuario();
                op2.setUsername("operario2");
                op2.setPassword(encoder.encode("Eco2026*"));
                op2.setRol(Rol.OPERARIO);

                usuarioRepo.save(op);
                usuarioRepo.save(sup);
                usuarioRepo.save(adm);
                usuarioRepo.save(op2);
                System.out.println("====== USUARIOS SEMILLA CREADOS ======");
            }

            // 2. Crear 12 Puntos de Acopio en 3 Zonas (Requisito Anexo 10)
            if (puntoRepo.count() == 0) {
                String[] zonas = {"Norte", "Sur", "Centro"};
                for (int i = 1; i <= 12; i++) {
                    PuntoAcopio p = new PuntoAcopio();
                    p.setRazonSocial("Empresa Recicla " + i);
                    p.setDireccion("Av. Principal " + (100 * i));
                    p.setZona(zonas[i % 3]); // Repartimos equitativamente entre las 3 zonas
                    p.setMaterialHabitual(i % 2 == 0 ? "Cartón" : "Plástico");
                    p.setSecuencia(i);
                    // Dejamos los puntos de la ruta 1 sin atender para probar RN-04
                    p.setAtendido(false);
                    puntoRepo.save(p);
                }
                System.out.println("====== 12 PUNTOS DE ACOPIO CREADOS ======");

                // 3. Crear algunos pesajes de prueba
                PuntoAcopio puntoCerrado = puntoRepo.findById(12L).get();
                puntoCerrado.setAtendido(true);
                puntoRepo.save(puntoCerrado);

                Pesaje pesajeAjeno = new Pesaje();
                pesajeAjeno.setPunto(puntoCerrado);
                pesajeAjeno.setFechaHora("2026-10-27T08:00:00");
                pesajeAjeno.setMaterial("Vidrio");
                pesajeAjeno.setPesoBruto(50.0);
                pesajeAjeno.setTara(5.0);
                pesajeAjeno.setPesoNeto(45.0);
                pesajeAjeno.setOperario("operario2"); // Otro operario (RN-02)
                pesajeAjeno.setEstado("Registrado");
                pesajeRepo.save(pesajeAjeno);

                Pesaje pesajeAnulado = new Pesaje();
                pesajeAnulado.setPunto(puntoRepo.findById(11L).get());
                pesajeAnulado.setFechaHora("2026-10-27T09:00:00");
                pesajeAnulado.setMaterial("Cartón");
                pesajeAnulado.setPesoBruto(20.0);
                pesajeAnulado.setTara(2.0);
                pesajeAnulado.setPesoNeto(18.0);
                pesajeAnulado.setOperario("operario1");
                pesajeAnulado.setEstado("Anulado");
                pesajeAnulado.setMotivoAnulacion("Balanza descalibrada");
                pesajeRepo.save(pesajeAnulado);

                System.out.println("====== PESAJES DE PRUEBA CREADOS ======");
            }
        };
    }
}