package Study.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Education;
import Study.App.model.Location;
import Study.App.model.SessionInformation;

@Repository
public interface SessionInformationRepository extends CrudRepository<SessionInformation, Integer>{
    
    public SessionInformation findSessionInformationBySessionInformationId(int id);

    public List<SessionInformation> findAllSessionInformationByCourse(String course);

    public List<SessionInformation> findAllSessionInformationByIsOnline();

    public List<SessionInformation> findAllSessionInformationByEducation(Education education);

    public List<SessionInformation> findAllSessionInformationByLocation(Location location);
}
