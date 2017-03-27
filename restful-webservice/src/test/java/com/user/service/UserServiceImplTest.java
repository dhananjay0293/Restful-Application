package com.user.service;

import com.user.UserHelper;
import com.user.entity.User;
import com.user.exception.UserException;
import com.user.provider.UserProvider;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class UserServiceImplTest {
    @Mock
    UserProvider userProvider;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void getUserInfoTest() {

        doReturn(UserHelper.getMockUserList()).when(userProvider)
                .getUserInfo("10");
        List<User> userList = userService
                .getUserInfo("10");
        assertNotNull(userList);
        assertEquals(userList.get(0).getId(), "10");
    }

    @Test
    public void createUserTest() {
        doReturn(UserHelper.getMockUser()).when(userProvider)
                .createOrUpdateUser(UserHelper.getMockUser());
        User user = userService
                .createUser(UserHelper.getMockUser());
        assertNotNull(user);
        assertEquals(user.getId(), "10");
    }

    @Test(expected = UserException.class)
    public void createUserTestWithException() {
        when(this.userService.getUserInfo("10")).thenReturn(UserHelper.getMockUserList());
        doReturn(UserHelper.getMockUser()).when(userProvider)
                .createOrUpdateUser(UserHelper.getMockUser());
        User user = userService
                .createUser(UserHelper.getMockUser());
        assertNotNull(user);
        assertEquals(user.getId(), "10");
    }

    @Test(expected = UserException.class)
    public void modifyUserTestWithException() {
        doReturn(UserHelper.getMockUser()).when(userProvider)
                .createOrUpdateUser(UserHelper.getMockUser());
        User user = userService
                .modifyUser(UserHelper.getMockUser());
        assertNotNull(user);
        assertEquals(user.getId(), "10");
    }
    @Test
    public void modifyUserTest() {
        when(this.userService.getUserInfo("10")).thenReturn(UserHelper.getMockUserList());
        doReturn(UserHelper.getMockUser()).when(userProvider)
                .createOrUpdateUser(UserHelper.getMockUser());
        User user = userService
                .modifyUser(UserHelper.getMockUser());
        assertNotNull(user);
        assertEquals(user.getId(), "10");
    }

    @Test(expected = UserException.class)
    public void removeUserTestWithException() {
        doReturn(UserHelper.getMockUser()).when(userProvider)
                .createOrUpdateUser(UserHelper.getMockUser());
        userService
                .removeUser("10");

    }
    @Test
    public void removeUserTest() {
        when(this.userService.getUserInfo("10")).thenReturn(UserHelper.getMockUserList());
        doReturn(UserHelper.getMockUser()).when(userProvider)
                .createOrUpdateUser(UserHelper.getMockUser());
        userService
                .removeUser("10");
    }

}