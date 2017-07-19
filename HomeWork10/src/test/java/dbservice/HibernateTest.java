package dbservice;

import org.junit.Test;
import ru.aryukov.dao.UserAddressEntityDao;
import ru.aryukov.dao.UserEntityDao;
import ru.aryukov.dao.UserPhoneEntityDao;
import ru.aryukov.dbservices.DbServices;
import ru.aryukov.dbservices.DbServicesImpl;
import ru.aryukov.domain.UserAddressEntity;
import ru.aryukov.domain.UserEntity;
import ru.aryukov.domain.UserPhoneEntity;
import ru.aryukov.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dev on 17.07.17.
 */
public class HibernateTest {

    @Test
    public void save() throws Exception {

       /* UserEntityDao userEntityDao = new UserEntityDao();
        UserPhoneEntityDao userPhoneEntityDao = new UserPhoneEntityDao();
        UserAddressEntityDao userAddressEntityDao= new UserAddressEntityDao();

        final UserEntity userEntity = new UserEntity();
        userEntity.setName("Oleg");
        userEntity.setAge(32);

        final UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.setIndex(11);
        userAddressEntity.setStreet("Kultury street");
        userEntity.setUserAddressEntity(userAddressEntity);
        userAddressEntityDao.save(userAddressEntity);

        userEntityDao.save(userEntity);

        final List<UserPhoneEntity> phoneDataSetList = new ArrayList<>();
        final UserPhoneEntity userPhoneEntity = new UserPhoneEntity();
        userPhoneEntity.setNumber("987-654-32");
        userPhoneEntity.setCode(812);
        userPhoneEntity.setUser(new UserEntity(userEntity));
        phoneDataSetList.add(userPhoneEntity);
        userPhoneEntityDao.save(userPhoneEntity);

        final UserPhoneEntity userPhoneEntity1 = new UserPhoneEntity();
        userPhoneEntity1.setNumber("123-456-78");
        userPhoneEntity1.setCode(490);
        userPhoneEntity1.setUser(new UserEntity(userEntity));
        phoneDataSetList.add(userPhoneEntity1);

        //userEntity.setUserPhoneEntity(phoneDataSetList);
        userPhoneEntityDao.save(userPhoneEntity1);



       final UserEntity factUser = userEntityDao.findByName("Oleg");

        assertEquals("userEntity Name", userEntity.getName(), factUser.getName());
        assertEquals("userEntity Age", userEntity.getAge(), factUser.getAge());

        assertEquals("userAddressEntity Index", userEntity.getUserAddressEntity().getIndex(), factUser.getUserAddressEntity().getIndex());
        assertEquals("userAddressEntity Street", userEntity.getUserAddressEntity().getStreet(), factUser.getUserAddressEntity().getStreet());

        assertEquals("userPhoneEntity Phone size", userEntity.getUserPhoneEntity().size(), factUser.getUserPhoneEntity().size());
        assertEquals("userPhoneEntity Phone", userEntity.getUserPhoneEntity().get(0), factUser.getUserPhoneEntity().get(0));*/
    }
}
