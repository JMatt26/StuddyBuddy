package Study.App.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Participation;
import Study.App.model.enums.ParticipationRole;

@Repository
public interface ParticipantRepository extends CrudRepository<Participation, Integer>{
    public Participation findParticipantByParticipantId(int id);
    public List<Participation> findAllParticipantByRole(ParticipationRole role);
    public List<Participation> findAllParticipantByIsGoingTrue();
    public List<Participation> findAllParticipantByIsGoingFalse();
}
