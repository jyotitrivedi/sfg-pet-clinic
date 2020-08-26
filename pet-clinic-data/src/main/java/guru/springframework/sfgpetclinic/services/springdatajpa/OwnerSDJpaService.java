package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepositoy;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepositoy ownerRepositoy;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepositoy ownerRepositoy, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepositoy = ownerRepositoy;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepositoy.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners =new HashSet<>();
        ownerRepositoy.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
                if(ownerRepositoy.findById(aLong).isPresent()){
            return ownerRepositoy.findById(aLong).get();
        }else
            return null;
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepositoy.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepositoy.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepositoy.deleteById(aLong);
    }
}
