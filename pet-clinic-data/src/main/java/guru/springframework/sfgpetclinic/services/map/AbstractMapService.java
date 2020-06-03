package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {

    protected Map<Long,T> map=new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }
    T findById(ID id){
        return map.get(id);
    }
    T save(T object){
        if(object!=null){
            if(object.getId()==null)
                object.setId(getNextid());
        }else
            throw new RuntimeException("object cannot be null");
        map.put(object.getId(),object);
        return object;
    }
    void deleteById(ID id){
        map.remove(id);
    }
    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
    private Long getNextid(){
       Long nextId=null;
        try{
            nextId= Collections.max(map.keySet()) +1;
        }catch (NoSuchElementException el){
            nextId=1L;
        }
        return nextId;
    }

}
