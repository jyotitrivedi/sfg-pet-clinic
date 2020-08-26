package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepositoy extends CrudRepository<Owner,Long> {
    Owner findByLastName(String lastname);

}
