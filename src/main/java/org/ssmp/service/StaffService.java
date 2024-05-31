package org.ssmp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ssmp.Utils.HibernateUtil;
import org.ssmp.dtos.BossDTO;
import org.ssmp.dtos.DriverDTO;
import org.ssmp.dtos.RegisterDTO;
import org.ssmp.model.Staff;
import org.ssmp.repository.RoleRepository;
import org.ssmp.repository.StaffRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService implements UserDetailsService {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;
    private final PostService postService;

    public List<Staff> getStaffList(){
        return staffRepository.findAll();
    }

    public Optional<Staff> findByLogin(String login){
        return staffRepository.findByLogin(login);
    }

    public Staff findById(Long id){
        return staffRepository.findByWorkerID(id);
    }

    public String getFIOMech(Long id){
        Staff staff = findById(id);
        return staff.getSurname() +" "+staff.getFirstname().charAt(0)+"."+staff.getPatronymic().charAt(0)+".";
    }
    public List<BossDTO> getBosses(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            List<BossDTO> bossDTOList = new ArrayList<>();
             List<Staff> staffList =  session.createQuery(" from Staff " +
                            "where post=:post or post=:secondPost", Staff.class)
                    .setParameter("post", postService.getPostByName("Гл. Врач"))
                    .setParameter("secondPost", postService.getPostByName("Зам. Гл. Врач")).stream().toList();
             for(Staff employee: staffList){
                 BossDTO bossDTO = new BossDTO();
                 bossDTO.setId(employee.getWorkerID());
                 bossDTO.setPost(employee.getPost().getPostName());
                 bossDTO.setFio(getFIO(employee));
                 bossDTOList.add(bossDTO);
             }
            return bossDTOList;
        }catch (Exception e){
            System.out.println("Ошибка в поиске боссов "+e);
        }
        return null;
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

    public boolean registerNewUser(RegisterDTO registerDTO){
        if(staffRepository.findByLogin(registerDTO.getLogin()).isPresent()){
            return false;
        }else{
                System.out.println("Попали в регистрацию");
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                Staff newUser = new Staff();
                newUser.setPost(postService.getPostByName(registerDTO.getPost()));
                newUser.setFirstname(registerDTO.getName());
                newUser.setSurname(registerDTO.getSurname());
                newUser.setPatronymic(registerDTO.getPatronimyc());
                newUser.setLogin(registerDTO.getLogin());
                newUser.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPass()));
                newUser.setRoles(List.of(roleRepository.findByRoleName(registerDTO.getRole()).get()));
                staffRepository.save(newUser);
                return true;
        }

    }
    public String getFIO(Staff employee){

        String surname = employee.getSurname();
        String firstname = employee.getFirstname();
        String patronimyc = employee.getPatronymic();

        return surname +" "+firstname.charAt(0)+". "+ patronimyc.charAt(0)+".";

    }

    public List<DriverDTO> getDrivers() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Staff> drivers = session.createQuery("from Staff where post=:post", Staff.class)
                    .setParameter("post", postService.getPostByName("Водитель")).stream().toList();
            List<DriverDTO> driverDTOList = new ArrayList<>();
            for(Staff driver: drivers){
                driverDTOList.add(new DriverDTO(driver.getWorkerID(), getFIO(driver)));
            }
            return driverDTOList;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
