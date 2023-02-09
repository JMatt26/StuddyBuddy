package Study.App.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Participant;
import Study.App.model.enums.ParticipationRole;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Integer>{
    public Participant findParticipantByParticipantId(int id);
    public List<Participant> findAllParticipantByRole(ParticipationRole role);
    public List<Participant> findAllParticipantByIsGoingTrue();
    public List<Participant> findAllParticipantByIsGoingFalse();
    public Participant findParticipantByUsername(String username);
}
