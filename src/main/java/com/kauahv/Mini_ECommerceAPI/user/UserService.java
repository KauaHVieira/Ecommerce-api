package com.kauahv.Mini_ECommerceAPI.user;

import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(UUID id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found!");
        }
        userRepository.deleteById(id);
    }

    public User update(UUID id, User obj){
        User user = findById(id);
        updateData(user, obj);
        return userRepository.save(user);
    }

    public void updateData(User user, User obj){
        if(obj.getName() != null){
            user.setName(obj.getName());
        }
        if(obj.getEmail() != null){
            obj.setEmail(obj.getEmail());
        }
        if(obj.getPhone() != null){
            user.setPhone(obj.getPhone());
        }
    }
}
