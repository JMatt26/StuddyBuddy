package Study.App.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Participation;
import Study.App.model.Session;
import Study.App.model.SessionInformation;

@Repository
public interface SessionRepository extends CrudRepository<Session, Integer> {

    public Session findSessionBySessionId(int id);

    // TODO: remove this repository method, this should be done at the service layer
    // public Session findSessionByParticipationId(int id);
    
    public List<Session> findAllSessionByIsPrivateTrue();

    public List<Session> findAllSessionByIsPrivateFalse();

    public List<Session> findAllSessionByTitle(String title);

    public Session findSessionBySessionInformation(SessionInformation sessionInformation);

}
