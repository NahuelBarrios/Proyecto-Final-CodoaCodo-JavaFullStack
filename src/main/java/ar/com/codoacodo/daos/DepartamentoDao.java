package ar.com.codoacodo.daos;

import ar.com.codoacodo.entities.Departamento;

import java.util.List;

public interface DepartamentoDao {

    Departamento getById(Long id) throws Exception;

    List<Departamento> findAll() throws Exception;    // devuelve todos los registros de la tabla departamentos

    void delete(Long id) throws Exception;// esto borra un registro por el id del departamento

    void update(Departamento depto) throws Exception; // se le pasa un objeto

    void create(Departamento newDepto) throws Exception;

    List<Departamento> search(String clave) throws Exception;

}
