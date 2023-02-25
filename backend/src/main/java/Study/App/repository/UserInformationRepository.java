package Study.App.repository;

import org.springframework.data.repository.CrudRepository;

import Study.App.model.UserInformation;

public interface UserInformationRepository extends CrudRepository<UserInformation, Integer>{
    UserInformation findByUserInfoId(int userInfoId);    
    UserInformation findUserInformationByUserUsername(String userUsername);
}
