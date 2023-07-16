package ar.com.codoacodo;

import ar.com.codoacodo.daos.DaoImplement.DepartamentoDaoImplement;
import ar.com.codoacodo.entities.Departamento;

import java.util.List;

public class main {
    static DepartamentoDaoImplement departamentoDaoImplement;
    public static void main(String[] args) throws Exception {
        departamentoDaoImplement = new DepartamentoDaoImplement();
        Departamento departamento  = departamentoDaoImplement.getById(1l);
        System.out.println(departamento.toString());

        List<Departamento> departamentos  = departamentoDaoImplement.findAll();
        System.out.println(departamentos);

        //departamentoDaoImplement.delete(2L);
        //System.out.println(departamentos);
        //departamentoDaoImplement.update(new Departamento(1L,"deptoNahu", 60000));
        //System.out.println(departamentos);
        //departamentoDaoImplement.create(new Departamento(4L,"deptoNahu", 60000d));

        List<Departamento> departamentosSearch  = departamentoDaoImplement.search("epto");
        System.out.println(departamentosSearch);
    }
}
