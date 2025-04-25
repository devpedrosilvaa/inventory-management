package tech.silva.inventory.modules.user.application.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.inventory.modules.shared.exceptions.EmailAlreadyUsedException;
import tech.silva.inventory.modules.shared.exceptions.ObjectNotFoundException;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;
import tech.silva.inventory.modules.user.application.dto.AuthUserView;
import tech.silva.inventory.modules.user.application.dto.mapper.UserDtoMapper;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.domain.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserApplicationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAlreadyUsedException(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found, try again!"));
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User not found, try again!"));
    }

    @Transactional(readOnly = true)
    public AuthUserView getUserByIdAuth(Long id) {
        return UserDtoMapper.toAuthViewFromDomain(
                userRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException("User not found, try again!"))
        );
    }

    @Transactional(readOnly = true)
    public AuthUserView getUserByEmailAuth(String email){
        return UserDtoMapper.toAuthViewFromDomain(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new ObjectNotFoundException("User not found, try again!"))
        );
    }

    @Transactional
    @Override
    public void assignStoreToUser(Long idUser, Long idStore) {
        User user = getUserById(idUser);
        user.setIdStore(idStore);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getStoreIdByUserId(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(
                () -> new ObjectNotFoundException("User not found, try again!")
        ).getIdStore();
    }

    @Override
    public User addSellerUser(String name, String email, String password, Long idStore) {
        return createUser(new User(
                name,
                email,
                password,
                idStore
        ));
    }

    @Override
    public List<User> listAllSellers(Long idUser) {
        Long idStore = getStoreIdByUserId(idUser);
        return userRepository.findAllByIdStore(idStore);
    }
}