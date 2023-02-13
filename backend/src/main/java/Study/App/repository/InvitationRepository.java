package Study.App.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Invitation;
import Study.App.model.InvitationDestination;
import Study.App.model.Session;
import Study.App.model.enums.InvitationType;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Integer> {

    public Invitation findInvitationByInviteId(int id);

    public List<Invitation> findAllInvitationByInvitationType(InvitationType type);

    public List<Invitation> findAllInvitationByInvitee(InvitationDestination invitationDestination);

    public List<Invitation> findAllInvitationBySession(Session session);
}
