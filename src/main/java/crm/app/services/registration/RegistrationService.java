package crm.app.services.registration;
import crm.app.services.registration.utils.EmailValidator;
import crm.app.services.registration.utils.PasswordEncoder;
import org.springframework.util.StringUtils;
import crm.app.data.dao.AppUserDAO;
import crm.app.data.model.AppUser;
import crm.app.services.registration.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements IRegistrationService{

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registration(UserDTO userDTO) throws InvalidCredentialsException{
    AppUser appUser = new AppUser();
    isEmailCorrect(userDTO);
    isPasswordValid(userDTO);
    isValue(userDTO.getName());
    isValue(userDTO.getSurname());
    appUser.setName(userDTO.getName());
    appUser.setSurname(userDTO.getSurname());
    appUser.setEmail(userDTO.getEmail());
    appUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    appUserDAO.create(appUser);
    }

    private void isEmailCorrect(UserDTO userDTO) throws InvalidCredentialsException{
        if(!EmailValidator.validate(userDTO.getEmail())){
            throw new InvalidCredentialsException("Email is incorrect");
        }
    }
    private void isPasswordConfirmed(UserDTO userDTO) throws InvalidCredentialsException {
        if(!(userDTO.getPassword().equals(userDTO.getConfirmPassword()))){
            throw new InvalidCredentialsException("Passwords are not similar");
        }
    }

    private void isPasswordValid(UserDTO userDTO) throws InvalidCredentialsException{
        isPasswordConfirmed(userDTO);
        if(!(userDTO.getPassword().length()>=6 && userDTO.getPassword().matches(" [a-z]+")&&
                userDTO.getPassword().matches(" [A-Z]+")&& userDTO.getPassword().matches(" [0-9]+"))){
            throw new InvalidCredentialsException("Password should include at least one upper, lower letter and a number");
        }

    }

    private void isValue(String value) throws InvalidCredentialsException{
        if(StringUtils.isEmpty(value)){
            throw new InvalidCredentialsException("Value cannot be empty or null");
        }
    }

}
