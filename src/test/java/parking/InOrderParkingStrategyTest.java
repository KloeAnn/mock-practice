package parking;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

        ParkingLot parkingLot=mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("parkinglot");

        Car car=mock(Car.class);
        when(car.getName()).thenReturn("car");

        InOrderParkingStrategy inOrderParkingStrategy=new InOrderParkingStrategy();
        Receipt receipt=inOrderParkingStrategy.createReceipt(parkingLot,car);

        Assert.assertEquals("parkinglot",receipt.getParkingLotName());
        Assert.assertEquals("car",receipt.getCarName());

    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */

        Car car=mock(Car.class);
        when(car.getName()).thenReturn("car");

        InOrderParkingStrategy inOrderParkingStrategy=new InOrderParkingStrategy();

        Receipt receipt=inOrderParkingStrategy.createNoSpaceReceipt(car);

        verify(car,times(1)).getName();
        Assert.assertEquals("car",receipt.getCarName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

        Car car=new Car("car");

        ParkingLot parkingLot=new ParkingLot("parkinglot",1);
        List<ParkingLot>parkingLots= Arrays.asList(parkingLot);

        InOrderParkingStrategy inOrderParkingStrategy=spy(new InOrderParkingStrategy());

        Receipt receipt=inOrderParkingStrategy.park(null,car);

        verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(car);
        Assert.assertEquals("car",receipt.getCarName());

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

        Car car=new Car("car");

        ParkingLot parkingLot=new ParkingLot("parkinglot",1);
        List<ParkingLot>parkingLots= Arrays.asList(parkingLot);

        InOrderParkingStrategy inOrderParkingStrategy=spy(new InOrderParkingStrategy());

        Receipt receipt=inOrderParkingStrategy.park(parkingLots,car);

        verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot,car);
        Assert.assertEquals("car",receipt.getCarName());
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateNoReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

        Car car=new Car("car");
        Car otherCar=new Car("other");

        ParkingLot parkingLot=new ParkingLot("parkinglot",1);
        List<ParkingLot>parkingLots= Arrays.asList(parkingLot);

        parkingLot.getParkedCars().add(otherCar);

        InOrderParkingStrategy inOrderParkingStrategy=spy(new InOrderParkingStrategy());

        Receipt receipt=inOrderParkingStrategy.park(parkingLots,car);

        verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(car);
        Assert.assertEquals("car",receipt.getCarName());
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

        Car car=new Car("car");
        Car otherCar=new Car("other");

        ParkingLot parkingLot1=new ParkingLot("parkinglot1",1);
        ParkingLot parkingLot2=new ParkingLot("parkinglot2",1);
        List<ParkingLot>parkingLots= Arrays.asList(parkingLot1,parkingLot2);

        parkingLot1.getParkedCars().add(otherCar);

        InOrderParkingStrategy inOrderParkingStrategy=spy(new InOrderParkingStrategy());

        Receipt receipt=inOrderParkingStrategy.park(parkingLots,car);

        verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot2,car);
        Assert.assertEquals("car",receipt.getCarName());

    }


}
