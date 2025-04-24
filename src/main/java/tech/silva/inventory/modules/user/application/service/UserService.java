package tech.silva.inventory.modules.user.application.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.inventory.modules.shared.exceptions.EmailAlreadyUsedException;
import tech.silva.inventory.modules.shared.exceptions.ObjectNotFoundException;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;
import tech.silva.inventory.modules.user.application.dto.AuthUserView;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.domain.repository.UserRepository;
import tech.silva.inventory.modules.user.infrastructure.persistence.mapper.UserMapper;

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
        return UserMapper.toAuthViewFromDomain(
                userRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException("User not found, try again!"))
        );
    }

    @Transactional(readOnly = true)
    public AuthUserView getUserByEmailAuth(String email){
        AuthUserView auth =UserMapper.toAuthViewFromDomain(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new ObjectNotFoundException("User not found, try again!"))
        );

        return auth;
    }

    @Transactional
    @Override
    public void addStore(Long idUser, Long idStore) {
        User user = getUserById(idUser);
        user.setIdStore(idStore);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getStoreIdByUserId(Long idUser) {
        Long storeId = userRepository.findById(idUser).orElseThrow(
                () -> new ObjectNotFoundException("User not found, try again!")
        ).getIdStore();
        return storeId;
    }
}