package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {

    public void save(Task task) throws SQLException {

        String sql = "INSERT INTO tasks (idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTimeInMillis()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTimeInMillis()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTimeInMillis()));

            //Executando a query
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
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
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTimeInMillis()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTimeInMillis()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTimeInMillis()));
            statement.setInt(9, task.getId());

            //Executando a query
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa", ex);
        }
    }

    public void removeById(int taskId) throws SQLException {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setInt(1, taskId);

            //Executando a query
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(int idProject) {

        String sql = "SELECT * FROM tasks WHERE idProject = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //Lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<Task>();

        try {
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);

            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();

            //Enquanto houver valores a serem percorridos no meu resultSet
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));

                //Config Calendar
                Calendar data = Calendar.getInstance();
                java.sql.Date deadline = resultSet.getDate("deadline");
                data.setTime(new java.util.Date(deadline.getTime()));
                task.setDeadline(data);

                java.sql.Date createdAt = resultSet.getDate("createdAt");
                data.setTime(new java.util.Date(createdAt.getTime()));
                task.setCreatedAt(data);

                java.sql.Date updatedAt = resultSet.getDate("updatedAt");
                data.setTime(new java.util.Date(updatedAt.getTime()));
                task.setUpdatedAt(data);
                
                tasks.add(task);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        //lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
    }
}
