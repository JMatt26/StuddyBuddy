package Study.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Participant;
import Study.App.model.Session;
import Study.App.model.SessionInformation;

@Repository
public interface SessionRepository extends CrudRepository<Session, Integer>{
    
    public Session findSessionBySessionId(int id);

    public List<Session> findAllSessionByIsPrivate();

    public List<Session> findAllSessionByTitle(String title);

    public List<Session> findAllSessionByParticipant(Participant participant);

    public List<Session> findAllSessionBySessionInformation(SessionInformation sessionInformation);

}
