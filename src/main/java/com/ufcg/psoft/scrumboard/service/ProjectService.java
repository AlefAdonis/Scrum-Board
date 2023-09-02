package com.ufcg.psoft.scrumboard.service;

import com.ufcg.psoft.scrumboard.dto.AssociationProjectDTO;
import com.ufcg.psoft.scrumboard.dto.ProjectDTO;
import com.ufcg.psoft.scrumboard.dto.ProjectUpdateDTO;
import com.ufcg.psoft.scrumboard.exception.ProjectNotFoundException;
import com.ufcg.psoft.scrumboard.exception.UserAlreayInProjectException;
import com.ufcg.psoft.scrumboard.exception.user.RoleNotValidException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotInProjectException;
import com.ufcg.psoft.scrumboard.models.entities.Project;
import com.ufcg.psoft.scrumboard.models.entities.userStories.State;
import com.ufcg.psoft.scrumboard.models.entities.userStories.UserStory;
import com.ufcg.psoft.scrumboard.models.entities.users.ScrumMaster;
import com.ufcg.psoft.scrumboard.models.entities.users.User;
import com.ufcg.psoft.scrumboard.models.entities.users.UserRole;
import com.ufcg.psoft.scrumboard.models.responses.ProjectResponse;
import com.ufcg.psoft.scrumboard.models.responses.UserStoryResponse;
import com.ufcg.psoft.scrumboard.repository.ProjectRepository;
import com.ufcg.psoft.scrumboard.repository.RoleRepository;
import com.ufcg.psoft.scrumboard.repository.UserStoryRepository;
import com.ufcg.psoft.scrumboard.resource.enums.Role;
import com.ufcg.psoft.scrumboard.resource.mapper.UserStoryMapper;
import com.ufcg.psoft.scrumboard.resource.util.ProjectUtil;
import com.ufcg.psoft.scrumboard.resource.util.UserStoryUtil;
import com.ufcg.psoft.scrumboard.resource.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private  ProjectRepository projectRepository;

    @Autowired
    private  UserUtil userUtil;

    @Autowired
    private UserStoryUtil userStoryUtil;

    @Autowired
    private ProjectUtil projectUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserStoryMapper userStoryMapper;

    @Autowired
    private UserStoryRepository userStoryRepository;


    public ProjectResponse getProjectById(String projectId, String userName) throws ProjectNotFoundException, UserNotFoundException {
        Project project = projectUtil.verifyProjectScrum(projectId, userName);
        return getProjectResponse(project);
    }

    public void deleteProject(String projectId, String userName) throws ProjectNotFoundException, UserNotFoundException {
        projectUtil.verifyProjectScrum(projectId, userName);
        this.projectRepository.deleteProject(projectId);

    }

    public String addProject(ProjectDTO projectDTO, String userName) throws UserNotFoundException {
        User user = this.userUtil.verifyUser(userName);
        ScrumMaster scrumMaster = new ScrumMaster();

        user.setRole(scrumMaster);

        Project project = new Project(projectDTO.getProjectName(), projectDTO.getDescription(), projectDTO.getInstitution(),
                user);

        return this.projectRepository.addProject(project);
    }

    public String updateProject(String projectId, String userName, ProjectUpdateDTO projectDTO) throws ProjectNotFoundException, UserNotFoundException {

        Project project = projectUtil.verifyProjectScrum(projectId, userName);
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDescription(projectDTO.getDescription());

        return this.projectRepository.updateProject(project);
    }

    public List<UserStoryResponse> getAllUserStoriesInProject(String projectId, String userName) throws ProjectNotFoundException, UserNotFoundException {
        Project project = projectUtil.verifyProjectScrum(projectId, userName);
        HashMap<UserStory, State> userStories = project.getUserStories();

        return userStoryMapper.userStoryResponseList(userStories);
    }

    private static ProjectResponse getProjectResponse(Project project) {
        return new ProjectResponse(
                project.getProjectName(),
                project.getProjectDescription(),
                project.getPartnerInstitution(),
                project.getAssociates());
    }

    public List<String> getAllRolesAvailables() {
        Role[] listOfAllRoles = Role.values();
        List<String> listOfAvailableRoles = new ArrayList<>();

        for(Role role : listOfAllRoles){
            if(!(role.getRole().equals(Role.SCRUM_MASTER.getRole()))){
                listOfAvailableRoles.add(role.getRole());
            }
        }
        return listOfAvailableRoles;
    }

    public void associationUserProject(String projectId, String userName, AssociationProjectDTO assocProjDTO) throws UserNotFoundException,
            ProjectNotFoundException, RoleNotValidException, UserAlreayInProjectException {

        Project project = projectUtil.verifyProjectScrum(projectId, userName);

        String roleUserAdd = verifyRole(assocProjDTO.getRole());
        UserRole role = roleRepository.getUserByRole(roleUserAdd);

        User association = userUtil.verifyUser(assocProjDTO.getAssociationName());
        projectUtil.verifyUserNotAssociated(project, association);

        association.setRole(role);

        project.getAssociates().put(association.getUserName(), association.getRole());
    }

    private String verifyRole(String roleUserAdd) throws RoleNotValidException {
        if (!getAllRolesAvailables().contains(roleUserAdd)) {
            throw new RoleNotValidException("Role não encontrada!");
        }
        return roleUserAdd;
    }

    public Project getProjectByUserStory(String userStoryId){
        for(Project project: this.projectRepository.getAllProjects()){
            if(project.getUserStories().containsKey(this.userStoryRepository.getUserStoryByID(userStoryId))){
                return project;
            }
        }
        return null;
    }

    public String getUserReport(String userName, String projectId) throws UserNotInProjectException, RoleNotValidException, ProjectNotFoundException {
        Project project = this.projectRepository.getProjectByID(projectId);
        if(project == null){throw new ProjectNotFoundException("O projeto não foi encontrado");}
        this.userUtil.canEnterInUserStory(userName, project);

        double[] userStorysOfUser = this.userStoryUtil.countUserStorysOfUserInProject(userName, project);
        double[] totalUserStorysInStates = this.userStoryUtil.getTotalUserStorysInEstates(project);
        double totalUserStorysOfProject = project.getUserStories().size();
        double userStoryPercentage = (userStorysOfUser[0] / totalUserStorysOfProject) * 100;
        if(Double.isNaN(userStoryPercentage)){userStoryPercentage = 0.0;}
        double[] percentualOfUser = getPercentualOfUser(userStorysOfUser, totalUserStorysInStates);
        String report = generateReportUser(userStoryPercentage, percentualOfUser, userStorysOfUser, userName);

        return report;
    }
    private double[] getPercentualOfUser(double[] userStorysOfUser, double[] totalUserStorysInStates){
        double[] percentualOfUser = new double[4];
        double TODOPercentage = (userStorysOfUser[1] / totalUserStorysInStates[0]) * 100;
        if(Double.isNaN(TODOPercentage)){TODOPercentage = 0.0;}
        percentualOfUser[0] = TODOPercentage;
        double WIPPercentage = (userStorysOfUser[2] / totalUserStorysInStates[1]) * 100;
        if(Double.isNaN(WIPPercentage)){WIPPercentage = 0.0;}
        percentualOfUser[1] = WIPPercentage;
        double TO_VERIFYPercentage = (userStorysOfUser[3] / totalUserStorysInStates[2]) * 100;
        if(Double.isNaN(TO_VERIFYPercentage)){TO_VERIFYPercentage = 0.0;}
        percentualOfUser[2] = TO_VERIFYPercentage;
        double DONEPercentage = (userStorysOfUser[4] / totalUserStorysInStates[3]) * 100;
        if(Double.isNaN(DONEPercentage)){DONEPercentage = 0.0;}
        percentualOfUser[3] = DONEPercentage;

        return percentualOfUser;
    }

    private String generateReportUser(double userStoryPercentage, double[] percentualOfUser, double[] userStorysOfUser, String userN){

        String report1 =
                "Descritivo do usuário com User Name: " + userN + ":\n"
                        + userStoryPercentage + "% do total de Users Storys atribuídas ao usuario. Quantidade: " + userStorysOfUser[0] + ".\n";
        String report2 =
                "Percentual em cada State:\n"
                        +  percentualOfUser[0] + "% do total de Users Storys de TODO atribuídas ao usuário. Quantidade: " + userStorysOfUser[1] + ";\n"
                        +  percentualOfUser[1] + "% do total de Users Storys de Work in Progress atribuídas ao usuário. Quantidade: " + userStorysOfUser[2] + ";\n"
                        +  percentualOfUser[2] + "% do total de Users Storys de To Verify atribuídas ao usuário. Quantidade: " + userStorysOfUser[3] + ";\n"
                        +  percentualOfUser[3] + "% do total de Users Storys de DONE atribuídas ao usuário. Quantidade: " + userStorysOfUser[4] + ".\n";
        String report =
                "\n-----------------------------------------------------------------------------------------------------------------------------\n"
                        +report1 + report2;
        return report;
    }

    public String getProductOwnerReport(String userName, String projectId) throws ProjectNotFoundException, UserNotFoundException, RoleNotValidException {
        Project project = this.projectRepository.getProjectByID(projectId);
        if(project == null){throw new ProjectNotFoundException("O projeto não foi encontrado");}
        if(project.getAssociates().get(userName) == null){throw  new UserNotFoundException("O Usuário não foi encontrado.");}
        else if(!project.getAssociates().get(userName).equals(Role.PRODUCT_OWNER.getRole())){
            throw new RoleNotValidException("Este tipo de descrição é só para o Product Owner!");
        }else{
            double totalUserStorysOfProject = project.getUserStories().size();
            double[] totalUserStorysInStates = this.userStoryUtil.getTotalUserStorysInEstates(project);
            double[] percentualStates = getPercentualStates(totalUserStorysInStates, totalUserStorysOfProject);
            String report = generateReportStates(percentualStates, totalUserStorysInStates);

            return report;
        }
    }
    private double[] getPercentualStates(double[] totalUserStorysInStates, double totalUserStorysOfProject){
        double[] percetualStates = new double[4];
        double TODOPercentage = (totalUserStorysInStates[0] / totalUserStorysOfProject) * 100;
        if(Double.isNaN(TODOPercentage)){TODOPercentage = 0.0;}
        percetualStates[0] = TODOPercentage;
        double WIPPercentage = (totalUserStorysInStates[1] / totalUserStorysOfProject) * 100;
        if(Double.isNaN(WIPPercentage)){WIPPercentage = 0.0;}
        percetualStates[1] = WIPPercentage;
        double TO_VERIFYPercentage = (totalUserStorysInStates[2] / totalUserStorysOfProject) * 100;
        if(Double.isNaN(TO_VERIFYPercentage)){TO_VERIFYPercentage = 0.0;}
        percetualStates[2] = TO_VERIFYPercentage;
        double DONEPercentage = (totalUserStorysInStates[3]) / totalUserStorysOfProject * 100;
        if(Double.isNaN(DONEPercentage)){DONEPercentage = 0.0;}
        percetualStates[3] = DONEPercentage;

        return percetualStates;
    }

    private String generateReportStates(double[] percentualStates, double[] totalUserStorysInStates){
        String report =
                "\n-----------------------------------------------------------------------------------------------------------------------------\n"
                        + "Percentual em cada State:\n"
                        +  percentualStates[0] + "% do total de Users Storys no TODO. Quantidade: " + totalUserStorysInStates[0] + ";\n"
                        +  percentualStates[1] + "% do total de Users Storys no Work in Progress. Quantidade: " + totalUserStorysInStates[1] + ";\n"
                        +  percentualStates[2] + "% do total de Users Storys no To Verify. Quantidade: " + totalUserStorysInStates[2] + ";\n"
                        +  percentualStates[3] + "% do total de Users Storys no DONE. Quantidade: " + totalUserStorysInStates[3] + ".";
        return report;
    }
    public String getScrumMasterReport(String userName, String projectId) throws ProjectNotFoundException, RoleNotValidException, UserNotFoundException {
        Project project = this.projectRepository.getProjectByID(projectId);
        if(project == null){throw new ProjectNotFoundException("O projeto não foi encontrado");}
        if(project.getAssociates().get(userName) == null){throw  new UserNotFoundException("O Usuário não foi encontrado.");}
        else if(!project.getAssociates().get(userName).equals(Role.SCRUM_MASTER.getRole())){
            throw new RoleNotValidException("Este tipo de descrição é só para o Product Owner!");
        }else{
            double totalUserStorysOfProject = project.getUserStories().size();
            double[] totalUserStorysInStates = this.userStoryUtil.getTotalUserStorysInEstates(project);
            double[] perncetualStates = getPercentualStates(totalUserStorysInStates, totalUserStorysOfProject);
            String report = generateReportStates(perncetualStates, totalUserStorysInStates);

            HashMap<String, double[]> mapUserStorysOfUser = new HashMap<>();
            for(String userN: project.getAssociates().keySet()){
                double[] userStorysOfUser = this.userStoryUtil.countUserStorysOfUserInProject(userN, project);
                mapUserStorysOfUser.put(userN, userStorysOfUser);
            }

            String report2 = "";
            for(String userN: mapUserStorysOfUser.keySet()) {
                double[] userStorysUser = mapUserStorysOfUser.get(userN);
                double userStoryPercentage = (userStorysUser[0] / totalUserStorysOfProject) * 100;
                if (Double.isNaN(userStoryPercentage)) {
                    userStoryPercentage = 0.0;
                }
                double[] percentualOfUser = getPercentualOfUser(userStorysUser, totalUserStorysInStates);
                String reportAtual = generateReportUser(userStoryPercentage, percentualOfUser, userStorysUser, userN);
                report2 += reportAtual;
            }
            return report2 + report;
        }
    }
}
