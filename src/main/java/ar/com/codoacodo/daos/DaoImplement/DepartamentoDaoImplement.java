package ar.com.codoacodo.daos.DaoImplement;

import ar.com.codoacodo.daos.DepartamentoDao;
import ar.com.codoacodo.db.ConectionHelper;
import ar.com.codoacodo.entities.Departamento;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoImplement implements DepartamentoDao {

    @Override
    public Departamento getById(Long id) throws Exception {
        Connection connection = ConectionHelper.getConnection();
        String sql = "select * from departamentos where id = " + id;
        Statement statement  = connection.createStatement();
        ResultSet resultset = statement.executeQuery(sql);

        if (resultset.next()){
            return getDepartmentByResultSet(resultset);
        }
        close(connection);
        return null;

    }

    public List<Departamento> findAll() throws Exception {
        Connection connection = ConectionHelper.getConnection();
        String sql = "select * from departamentos";

        Statement statement  = connection.createStatement();
        ResultSet resultset = statement.executeQuery(sql);

        List<Departamento> departamentos = new ArrayList<>();

        while (resultset.next()){
            departamentos.add(getDepartmentByResultSet(resultset));
        }
        close(connection);
        return departamentos;
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection connection = ConectionHelper.getConnection();
        String sql = "DELETE FROM desafiocodoacodo.departamentos WHERE id=" + id;

        Statement statement  = connection.createStatement();
        statement.executeQuery(sql);
        close(connection);
    }

    public void update(Departamento depto) throws Exception {
        Connection connection = ConectionHelper.getConnection();
        String sql = "update departamentos set nombre = ?, presupuesto = ? where id= ?"  ;
        PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1,depto.getNombreDepto());
        statement.setDouble(2,depto.getPresupuestoDepto());
        statement.setLong(3,depto.getId());
        statement.execute();

        close(connection);
    }

    public void create(Departamento newDepto) throws Exception {
        Connection connection = ConectionHelper.getConnection();
        String sql = "insert into departamentos (id, nombre, presupuesto) values (?,?,?)" ;
        PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1,newDepto.getId());
        statement.setString(2,newDepto.getNombreDepto());
        statement.setDouble(3,newDepto.getPresupuestoDepto());
        statement.execute();

        ResultSet res = statement.getGeneratedKeys();
        if (res.next()) {
            System.out.println("Se creo el departamento correctamente");
        }
        close(connection);
    }

    public List<Departamento> search(String clave) throws Exception {
        Connection connection = ConectionHelper.getConnection();
        String sql = "SELECT * FROM DEPARTAMENTOS WHERE NOMBRE LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + clave + "%");
        ResultSet resultSet = statement.executeQuery();
        List<Departamento> depto = new ArrayList<>();

        while (resultSet.next()) {
            depto.add(getDepartmentByResultSet(resultSet));
        }

        close(connection);
        return depto;
    }

    private void close(Connection con) throws Exception{
        con.close();
    }

    private Departamento getDepartmentByResultSet(ResultSet resultset) throws SQLException {
        Long idBd = resultset.getLong("id");
        String nombreBd = resultset.getString("nombre");
        double presupuestoBd = resultset.getDouble("presupuesto");
        return new Departamento(idBd,nombreBd,presupuestoBd);
    }
}
