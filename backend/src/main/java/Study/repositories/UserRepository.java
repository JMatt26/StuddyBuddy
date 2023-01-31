package Study.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Study.App.model.Invitee;
import Study.App.model.Participant;
import Study.App.model.User;
import Study.App.model.enums.ParticipationRole;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
   
    public User findUserByUserId(int id);

    public List<User> findAllUserByRoles(ParticipationRole role);

    public User findUserByUsername(String username);

    public List<User> findAllUserByFriends(User user);  // Have to think about this. Will it give what we want?

    public List<User> findAllUserByInvitee(Invitee invitee);

    public List<User> findAllUserByParticipants(Participant participant);
}
