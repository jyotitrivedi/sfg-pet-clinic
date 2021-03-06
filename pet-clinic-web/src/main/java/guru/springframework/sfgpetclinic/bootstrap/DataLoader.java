package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

      @Override
    public void run(String... args) throws Exception {

        int count =petTypeService.findAll().size();
        if(count==0)
          loadData();

      }

    private void loadData() {
        PetType dog= new PetType();
        dog.setName("Dog");
        PetType savedDogPetType= petTypeService.save(dog);

        PetType cat= new PetType();
        cat.setName("Cat");
        PetType savedCatPetType= petTypeService.save(cat);

        Specialty radiology=new Specialty();
        radiology.setDescription("radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1=new Owner();
        owner1.setFirstName("Jyoti");
        owner1.setLastName("Trivedi");
        owner1.setAddress("802 Sethi Max");
        owner1.setCity("Noida");
        owner1.setTelephone("8947066123");

        Pet jyotisPet =new Pet();
        jyotisPet.setPetType(savedDogPetType);
        jyotisPet.setOwner(owner1);
        jyotisPet.setName("Tiger");
        jyotisPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(jyotisPet);

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstName("Mohit");
        owner2.setLastName("Joshi");
        owner1.setAddress("802 Sethi Max");
        owner1.setCity("Noida");
        owner1.setTelephone("9660264489");

        Pet mohitsPet =new Pet();
        mohitsPet.setPetType(savedCatPetType);
        mohitsPet.setOwner(owner2);
        mohitsPet.setName("Edward");
        mohitsPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(mohitsPet);

        ownerService.save(owner2);
        Visit catVisit = new Visit();
        catVisit.setPet(mohitsPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);

        System.out.println("Owners loaded........");

        Vet vet1=new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Cena");
        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("Harry");
        vet2.setLastName("Potter");
        vetService.save(vet2);

        System.out.println("Vets loaded........");
    }
}
