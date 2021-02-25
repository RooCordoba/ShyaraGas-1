package repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shyaragas.app.models.User;
import com.shyaragas.app.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersRepositoryTest {

    @InjectMocks
    UsersRepository usersRepository = new UsersRepository();
    User s;

    @Mock
    DynamoDBMapper mapper;

   /* @Test
    public void ifSaveUserGetsAnUserAndSaveIt {

    }*/

    String idUserExist = "a92fef33-44e8-4ad0-87a5-ed0cebc75001";
    @Test
    public void ifGetUserByIdReturnAnUserThenReturnTrue() {

        s = usersRepository.getUserById(idUserExist);
        boolean status = (idUserExist.equals(s.getId()));
        assertEquals(true, status);
    }

    @Test
    public void ifGetUserByIdDoesNotReturnAnUserThenReturnTrue() {
        s = usersRepository.getUserById("identificadorDeUsuarioDeTest");
        boolean status = (s == null);
        assertEquals(true, status);
    }

    //PREGUNTAR COMO HACER ACA
    @Test
    public void ifGetAllUsersReturnAListOfAllTheUsersThenReturnTrue() {
        List<User> a = usersRepository.getAllUsers();
        boolean status = (a.size()>0 && a != null);
        assertEquals(true, status);
    }

    @Test
    public void isGetAllUsersDoesNotReturnAListOfAllTheUsersThenReturnTrue() {   //TODO:modificar luego de hacer el try catch en UserRepository
        MockitoAnnotations.initMocks(this);
        DynamoDBMapper mapper = mock(DynamoDBMapper.class);
        when(mapper.scan(any(),any())).thenReturn(null);
        List<User> a = usersRepository.getAllUsers();
        boolean status = (a == null);
        assertEquals(true, status);
    }
}
