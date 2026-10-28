package pe.upeu.ecorecicla.seguridad;

import java.util.List;

public enum Rol {
    OPERARIO(List.of(
            Privilegio.PUNTO_LEER, Privilegio.PESAJE_LEER, Privilegio.PESAJE_CREAR, Privilegio.PESAJE_EDITAR
    )),
    SUPERVISOR(List.of(
            Privilegio.PUNTO_LEER, Privilegio.PESAJE_LEER, Privilegio.PESAJE_CREAR, Privilegio.PESAJE_EDITAR,
            Privilegio.PESAJE_ANULAR, Privilegio.RUTA_CERRAR
    )),
    ADMINISTRADOR(List.of(
            Privilegio.PUNTO_LEER, Privilegio.PESAJE_LEER, Privilegio.PUNTO_GESTIONAR
    ));

    private final List<Privilegio> privilegios;

    Rol(List<Privilegio> privilegios) {
        this.privilegios = privilegios;
    }

    public List<Privilegio> getPrivilegios() {
        return privilegios;
    }
}