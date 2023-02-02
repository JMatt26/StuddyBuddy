package Study.App.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Education;
import Study.App.model.User;

@Repository
public interface EducationRepository extends CrudRepository<Education, Integer>{

    public Education findEducationByEducationId(int id);

    public List<Education> findAllEducationByUser(User user);
}
