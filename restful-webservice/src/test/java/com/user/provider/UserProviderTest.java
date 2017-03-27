package com.user.provider;

import com.datastax.driver.core.querybuilder.Batch;
import com.user.UserHelper;
import com.user.entity.User;
import com.user.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.cassandra.core.CassandraOperations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class UserProviderTest {

    @Mock
    CassandraOperations cassandraOperations;

    @InjectMocks
    UserProvider userProvider;

    @Test
    public void getUserInfoTest() {

        doReturn(UserHelper.getMockUserList()).when(cassandraOperations)
                .select(any(String.class), any(Class.class));
        List<User> userList = userProvider
                .getUserInfo("10");
        assertNotNull(userList);
        assertEquals(userList.get(0).getId(), "10");
    }

    @Test(expected = UserException.class)
    public void getUserInfoTestWithException() {

        doThrow(Exception.class).when(cassandraOperations)
                .select(any(String.class), any(Class.class));
        List<User> userList = userProvider
                .getUserInfo("10");
        assertNotNull(userList);
        assertEquals(userList.get(0).getId(), "10");
    }
    @Test
    public void createOrUpdateUserTest() {

        doReturn(UserHelper.getMockUser()).when(cassandraOperations)
                .insert(any(User.class));
        User user = userProvider
                .createOrUpdateUser(UserHelper.getMockUser());
        assertNotNull(user);
        assertEquals(user.getId(), "10");
    }

    @Test(expected = UserException.class)
    public void createOrUpdateUserTestWithException() {

        doThrow(Exception.class).when(cassandraOperations)
                .insert(any(User.class));
        User user = userProvider
                .createOrUpdateUser(UserHelper.getMockUser());
        assertNotNull(user);
        assertEquals(user.getId(), "10");
    }

    @Test(expected = UserException.class)
    public void removeUserTest() {

        doThrow(Exception.class).when(cassandraOperations)
                .execute(any(Batch.class));
        userProvider
                .removeUser("10");
    }
}