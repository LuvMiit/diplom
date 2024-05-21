package org.ssmp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.ssmp.dtos.CreateUserDTO;
import org.ssmp.model.Staff;
import org.ssmp.repository.RoleRepository;
import org.ssmp.repository.StaffRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService implements UserDetailsService {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;

    public List<Staff> getStaffList(){
        return staffRepository.findAll();
    }

    public Optional<Staff> findByLogin(String login){
        return staffRepository.findByLogin(login);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Staff user = findByLogin(login).orElseThrow(()->new UsernameNotFoundException(
                "Пользователь с данным login не найден"));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList())
        );
    }

    public void createNewUser(CreateUserDTO user){
        Staff newUser = new Staff();
        newUser.setPost(user.getPost());
        newUser.setFirstname(user.getFirstname());
        newUser.setSurname(user.getSurname());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(List.of(roleRepository.findByRoleName(user.getRole()).get()));
        staffRepository.save(newUser);

    }

}
