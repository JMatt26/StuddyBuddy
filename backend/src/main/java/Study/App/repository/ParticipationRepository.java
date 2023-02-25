package Study.App.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Participation;
import Study.App.model.enums.ParticipationRole;

@Repository
public interface ParticipationRepository extends CrudRepository<Participation, Integer> {
    public Participation findParticipationByParticipationID(int id);

    public List<Participation> findAllParticipationByRole(ParticipationRole role);

    public List<Participation> findAllParticipationByIsGoingTrue();

    public List<Participation> findAllParticipationBySessionSessionId(Integer sessionId);

    public List<Participation> findAllParticipationByUserInformationUserUserId(Integer userId);

    public List<Participation> findAllParticipationByIsGoingFalse();
}
