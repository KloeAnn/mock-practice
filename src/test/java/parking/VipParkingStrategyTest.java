package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {



	@Test
    public void testPark_givenAVipCarAndAFullParkingLot_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */

	    ParkingLot parkingLot=new ParkingLot("paekinglot",1);
	    parkingLot.getParkedCars().add(new Car("other"));
        List<ParkingLot>parkingLots= Arrays.asList(parkingLot);

        Car car=new Car("Acar");

        VipParkingStrategy vipParkingStrategy=spy(new VipParkingStrategy());
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);

        Receipt receipt=vipParkingStrategy.park(parkingLots,car);

        assertEquals("Acar",receipt.getCarName());

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */

        ParkingLot parkingLot=new ParkingLot("parking-lot",1);
        parkingLot.getParkedCars().add(new Car("other"));
        List<ParkingLot>parkingLots=Arrays.asList(parkingLot);

        Car car=new Car("car");

        VipParkingStrategy vipParkingStrategy=spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(car);

        Receipt receipt=vipParkingStrategy.park(parkingLots,car);

        assertEquals("No Parking Lot",receipt.getParkingLotName());
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */



        Car car=new Car("Acar");

        CarDaoImpl carDao=mock(CarDaoImpl.class);
        when(carDao.isVip("Acar")).thenReturn(true);

        VipParkingStrategy vipParkingStrategy=spy(new VipParkingStrategy());
        doReturn(carDao).when(vipParkingStrategy).getCarDao();

        boolean allowOverPark=vipParkingStrategy.isAllowOverPark(car);

        assertTrue(allowOverPark);

    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */

        Car car=new Car("car");

        CarDaoImpl carDao=mock(CarDaoImpl.class);
        when(carDao.isVip("car")).thenReturn(true);

        VipParkingStrategy vipParkingStrategy=spy(new VipParkingStrategy());
        doReturn(carDao).when(vipParkingStrategy).getCarDao();

        boolean allowOverPark=vipParkingStrategy.isAllowOverPark(car);

        assertFalse(allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */

        Car car=new Car("A-car");

        CarDaoImpl carDao=mock(CarDaoImpl.class);
        when(carDao.isVip("A-car")).thenReturn(false);

        VipParkingStrategy vipParkingStrategy=spy(new VipParkingStrategy());
        doReturn(carDao).when(vipParkingStrategy).getCarDao();

        boolean allowOverPark=vipParkingStrategy.isAllowOverPark(car);

        assertFalse(allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
        Car car=new Car("car");

        CarDaoImpl carDao=mock(CarDaoImpl.class);
        when(carDao.isVip("car")).thenReturn(false);

        VipParkingStrategy vipParkingStrategy=spy(new VipParkingStrategy());
        doReturn(carDao).when(vipParkingStrategy).getCarDao();

        boolean allowOverPark=vipParkingStrategy.isAllowOverPark(car);

        assertFalse(allowOverPark);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
