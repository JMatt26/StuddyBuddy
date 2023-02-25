package Study.App.repository;

import org.springframework.data.repository.CrudRepository;

import Study.App.model.Location;

public interface LocationRepository extends CrudRepository<Location, Integer>{
    public Location findLocationByLocationid(Integer locationid);
}
