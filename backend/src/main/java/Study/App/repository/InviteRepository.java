package Study.App.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Invitation;
import Study.App.model.InvitationDestination;
import Study.App.model.Session;
import Study.App.model.enums.InvitationType;

@Repository
public interface InviteRepository extends CrudRepository<Invitation, Integer>{
    
    public Invitation findInviteByInviteId(int id);

    public List<Invitation> findAllInviteByInvitationType(InvitationType type);

    public List<Invitation> findAllInviteByInvitee(InvitationDestination invitationDestination);

    public List<Invitation> findAllInviteBySession(Session session);
}
