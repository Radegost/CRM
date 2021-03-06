package crm.app.services.registration.utils;

import crm.app.services.user.exception.InvalidValueException;
import crm.app.services.user.utils.ValueValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ValueValidatorTest {

    @Test(expected = InvalidValueException.class)
    public void when_value_is_null() throws InvalidValueException {
        ValueValidator.isValue("name", null);
    }

    @Test(expected = InvalidValueException.class)
    public void when_value_is_empty() throws InvalidValueException {
        ValueValidator.isValue("name", "");
    }

    @Test
    public void when_value_exists() throws InvalidValueException {
        ValueValidator.isValue("some", "wartość");
    }

}
