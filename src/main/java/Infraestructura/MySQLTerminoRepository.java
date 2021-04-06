package Infraestructura;

import Dominio.Termino;
import Dominio.TerminoRepository;

import java.sql.*;
import java.util.Map;

public class MySQLTerminoRepository implements TerminoRepository {

    private Connection connection;

    @Override
    public Termino getTermino(String termino) {
        return null;
    }

    @Override
    public Map<String, Termino> getTerminos() {
        return null;
    }

    @Override
    public void saveTermino(Termino termino) {
    }

    @Override
    public void saveTerminos(Map<String, Termino> terminos) {
        try {
            connection = MySQLConnection.conectar();
            Statement statement = connection.createStatement();
            StringBuilder query =
                    new StringBuilder("INSERT INTO Terminos " +
                            "(termino, cantidadDocumentos, maximaFrecuenciaTermino) VALUES ");

            for (Map.Entry<String, Termino> entry : terminos.entrySet()) {
                String palabra = entry.getValue().getTermino();
                int cantidadDocumentos = entry.getValue().getCantidadDocumentos();
                int maximaFrecuenciaTermino = entry.getValue().getMaximaFrecuenciaTermino();

                query.append("('").append(palabra).append("',")
                        .append(cantidadDocumentos).append(",")
                        .append(maximaFrecuenciaTermino).append("),");
            }
            query.setCharAt(query.length()-1, ';');

            statement.execute(query.toString());
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
