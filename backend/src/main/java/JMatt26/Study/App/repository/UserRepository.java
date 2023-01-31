package JMatt26.Study.App.repository;

import org.springframework.data.repository.CrudRepository;

import JMatt26.Study.App.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
}
