package tech.silva.inventory.modules.user.application.service;


import tech.silva.inventory.modules.shared.exceptions.EmailAlreadyUsedException;
import tech.silva.inventory.modules.shared.exceptions.ObjectNotFoundException;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.domain.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAlreadyUsedException(user.getEmail());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found, try again!"));
    }
}