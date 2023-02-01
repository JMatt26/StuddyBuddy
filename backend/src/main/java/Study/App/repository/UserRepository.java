package Study.App.repository;

import org.springframework.data.repository.CrudRepository;

import Study.App.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
}
