package Study.App.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import Study.App.model.Education;
import Study.App.model.Location;
import Study.App.model.SessionInformation;

@Repository
public interface SessionInformationRepository extends CrudRepository<SessionInformation, Integer>{
    
    public SessionInformation findSessionInformationBySessionInformationId(int id);

    public List<SessionInformation> findAllSessionInformationByCoursesIn(List<String> courses);

    public List<SessionInformation> findAllSessionInformationByIsOnlineTrue();
    public List<SessionInformation> findAllSessionInformationByIsOnlineFalse();

    public List<SessionInformation> findAllSessionInformationByEducation(Education education);

    public List<SessionInformation> findAllSessionInformationByBuildingName(String buildingName);

    public List<SessionInformation> findAllSessionInformationByStartTime(Date startTime);


}
