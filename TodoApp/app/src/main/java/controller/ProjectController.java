package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {

    public void save(Project project) throws SQLException {

        String sql = "INSERT INTO projects (name, "
                + "description, "
                + "createdAt, "
                + "updatedAt) "
                + "VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTimeInMillis()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTimeInMillis()));

            //Executando a query
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project) {

        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTimeInMillis()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTimeInMillis()));
            statement.setInt(5, project.getId());

            //Executando a query
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void removeById(int idProject) throws SQLException {
        
        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setInt(1, idProject);

            //Executando a query
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar o projeto");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Project> getAll() {
        
        String sql = "SELECT * FROM projects";

        //Lista de projetos que será devolvida quando a chamada do método acontecer
        List<Project> projects = new ArrayList<Project>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();

            //Enquanto houver valores a serem percorridos no meu resultSet
            while (resultSet.next()) {

                Project project = new Project();

                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));

                //Config Calendar
                Calendar data = Calendar.getInstance();

                java.sql.Date createdAt = resultSet.getDate("createdAt");
                data.setTime(new java.util.Date(createdAt.getTime()));
                project.setCreatedAt(data);

                java.sql.Date updatedAt = resultSet.getDate("updatedAt");
                data.setTime(new java.util.Date(updatedAt.getTime()));
                project.setUpdatedAt(data);

                //Adiciono o contato recuperado, a lista de contatos
                projects.add(project);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        //lista de tarefas que foi criada e carregada do banco de dados
        return projects;
    }
}
