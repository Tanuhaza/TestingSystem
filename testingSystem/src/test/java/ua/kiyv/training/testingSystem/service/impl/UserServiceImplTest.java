package ua.kiyv.training.testingSystem.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.connection.TransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.dao.UserDao;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.service.ServiceFactory;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Tests for User service
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcDaoFactory.class, JdbcTransactionHelper.class})
public class UserServiceImplTest {

    @Mock
    private DaoFactory factory;

    @Mock
    private JdbcTransactionHelper transactionHelper;

    @Mock
    private UserDao userDao;

    @Before
    public void setUpMocks() {
        mockStatic(DaoFactory.class);
        mockStatic(JdbcTransactionHelper.class);
        when(DaoFactory.getInstance()).thenReturn(factory);
        when(JdbcTransactionHelper.getInstance()).thenReturn(transactionHelper);
        when(factory.createUserDao()).thenReturn(userDao);
    }

    @Test
    public void testTransactionSuccessForCreate() {
        User user = new User();
        InOrder inOrder = inOrder(transactionHelper, userDao);
        ServiceFactory.getInstance().createUserService().create(user);
        inOrder.verify(transactionHelper).beginTransaction();
        inOrder.verify(userDao).create(eq(user));
        inOrder.verify(transactionHelper).commitTransaction();
    }

    @Test
    public void testTransactionFailForCreate() {
        User user = new User();
        doThrow(DaoException.class).when(userDao).create(eq(user));

        InOrder inOrder = inOrder(transactionHelper, userDao);
        try {
            ServiceFactory.getInstance().createUserService().create(user);
        } catch (ServiceException ex) {
            Assert.assertTrue(ex.getCause() instanceof DaoException);
        }

        inOrder.verify(transactionHelper).beginTransaction();
        inOrder.verify(userDao).create(eq(user));
        inOrder.verify(transactionHelper).rollbackTransaction();

        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testTransactionSuccessForUpdate() {
        User user = new User();
        InOrder inOrder = inOrder(transactionHelper);

        ServiceFactory.getInstance().createUserService().update(user);

        inOrder.verify(transactionHelper).beginTransaction();
        verify(userDao).update(eq(user));
        inOrder.verify(transactionHelper).commitTransaction();

        verifyNoMoreInteractions(transactionHelper, userDao);
    }

    @Test
    public void testTransactionFailForUpdate() {
        User user = new User();
        doThrow(DaoException.class).when(userDao).create(eq(user));
        InOrder inOrder = inOrder(transactionHelper);

        try {
            ServiceFactory.getInstance().createUserService().update(user);
        } catch (ServiceException e) {
            Assert.assertTrue(e.getCause() instanceof DaoException);
        }

        inOrder.verify(transactionHelper).beginTransaction();
        verify(userDao).update(eq(user));
        inOrder.verify(transactionHelper).commitTransaction();

        verifyNoMoreInteractions(transactionHelper, userDao);
    }

    @Test(expected = ServiceException.class)
    public void testGetUserByWrongPassword() {
        User user = new User();
        user.setPassword("WrongPassword");
        when(userDao.findByLogin(anyString())).thenReturn(user);
        ServiceFactoryImpl.getInstance().createUserService().getUserByLoginPassword("Baris", "qwert");
        verify(userDao).findByLogin(eq("Baris"));
    }

    @Test
    public void testGetUserByCorrectPassword() {
        User user = new User();
        user.setPassword("qwert");
        when(userDao.findByLogin(anyString())).thenReturn(user);
        ServiceFactoryImpl.getInstance().createUserService().getUserByLoginPassword("Baris", "qwert");
        verify(userDao).findByLogin(eq("Baris"));
    }

    @Test(expected = DaoException.class)
    public void testGetUserByWrongLogin() {
        User user = new User();
        doThrow(DaoException.class).when(userDao).findByLogin(anyString());
        ServiceFactoryImpl.getInstance().createUserService().getUserByLoginPassword("Baris", "qwert");
        verify(userDao).findByLogin(eq("Baris"));
    }

    @Test
    public void testFindById(){
        User user = new User();
        user.setId(1);
        when(userDao.findById(anyInt())).thenReturn(user);
        ServiceFactory.getInstance().createUserService().findById(1);
        verify(userDao).findById(eq(1));
        Assert.assertEquals(1,user.getId());
    }

    @Test(expected = DaoException.class)
    public void testNoUserById(){
        User user = new User();
        doThrow(DaoException.class).when(userDao).findById(anyInt());
        ServiceFactory.getInstance().createUserService().findById(1);
        verify(userDao).findById(eq(1));
    }
}
