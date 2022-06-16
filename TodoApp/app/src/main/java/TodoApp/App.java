package TodoApp;

import controller.ProjectController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class App {

    public static void main(String[] args) throws SQLException {
        
//        ProjectController projectController = new ProjectController();
        
//        Project project = new Project();
//        project.setName("Projeto teste");
//        project.setDescription("description");
//        projectController.save(project);
        
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setId(1);
        project.setName("Novo nome do projeto");
        project.setDescription("description teste");
        projectController.update(project);
        
        List<Project> projects = projectController.getAll();
        System.out.println("Total de projetos = " + projects.size());
        
        projectController.removeById(1);
    }
}
