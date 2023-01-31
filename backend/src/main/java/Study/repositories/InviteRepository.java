package Study.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Invite;
import Study.App.model.Invitee;
import Study.App.model.Session;
import Study.App.model.enums.InvitationType;

@Repository
public interface InviteRepository extends CrudRepository<Invite, Integer>{
    
    public Invite findInviteByInviteId(int id);

    public List<Invite> findAllInviteByInvitationType(InvitationType type);

    public List<Invite> findAllInviteByInvitee(Invitee invitee);

    public List<Invite> findAllInviteBySession(Session session);
}
