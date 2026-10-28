package pe.upeu.ecorecicla;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.upeu.ecorecicla.repositorio.UsuarioRepository;
import pe.upeu.ecorecicla.seguridad.Rol;
import pe.upeu.ecorecicla.seguridad.Usuario;

@SpringBootApplication
public class Aplicacion {
    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
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

                repo.save(op);
                repo.save(sup);
                repo.save(adm);
                System.out.println("====== USUARIOS SEMILLA CREADOS ======");
            }
        };
    }
}