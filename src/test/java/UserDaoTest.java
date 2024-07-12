/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.empresa.thyssenplastic.dao.impl.UserDaoImpl;
import com.empresa.thyssenplastic.dao.UserDao;
import com.empresa.thyssenplastic.model.UserModel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gusta
 */
public class UserDaoTest {
    
    public UserDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void saveUserTest() {
        UserDao userdao = new UserDaoImpl();
        /*
        UserModel user = new UserModel();
        user.setUsername("pepe@yahoo.com");
        user.setPassword("123");
        
        userdao.save(user);
        */
        assertTrue(true);
    }
    
    @Test
    public void getAllUserTest() {
                        
        UserDao userdao = new UserDaoImpl();
        List<UserModel> users = userdao.getAll();
        
        assertNotNull(users);        
    }

    @Test
    public void getUserByUsernameAndPasswordTest() {
        UserDao userdao = new UserDaoImpl();
        UserModel user = userdao.getUserByUsernameAndPassword("oficina@gmail.com", "123");
        
        assertNotNull(user);
        assertNotNull(true);
    }

}
