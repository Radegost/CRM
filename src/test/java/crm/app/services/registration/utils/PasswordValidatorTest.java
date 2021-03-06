package crm.app.services.registration.utils;

import crm.app.services.user.exception.InvalidPasswordException;
import crm.app.services.user.registration.RegistrationUserDTO;
import crm.app.services.user.utils.PasswordValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordValidatorTest {


    @Test(expected = InvalidPasswordException.class)
    public void when_password_is_incorrect() throws InvalidPasswordException {
        PasswordValidator.isPasswordValid(new RegistrationUserDTO.UserDTOBuilder().withPassword("Hasłobezliczb").build().getPassword());
    }

    @Test
    public void when_password_is_correct() throws InvalidPasswordException {
        PasswordValidator.isPasswordValid(new RegistrationUserDTO.UserDTOBuilder().withPassword("Hasło1234").build().getPassword());
    }

    @Test(expected = InvalidPasswordException.class)
    public void when_passwords_are_not_equel() throws InvalidPasswordException {
        RegistrationUserDTO registrationUserDTO = new RegistrationUserDTO.UserDTOBuilder().withPassword("Hasło123").withConfirmPassword("InneHasło123").build();
        PasswordValidator.isPasswordConfirmed(registrationUserDTO.getPassword(), registrationUserDTO.getConfirmPassword());
    }

    @Test
    public void when_passwords_are_equel() throws InvalidPasswordException {
        RegistrationUserDTO registrationUserDTO = new RegistrationUserDTO.UserDTOBuilder().withPassword("Hasło123").withConfirmPassword("Hasło123").build();
        PasswordValidator.isPasswordConfirmed(registrationUserDTO.getPassword(), registrationUserDTO.getConfirmPassword());
    }

}
