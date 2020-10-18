package OOD.ParkingLot;

public class CreditCardProcessor implements PaymentProcessor {

    @Override
    public boolean pay(int amount) {
        System.out.println("$"+amount + " Thanks for your payment!");
        return true;
    }
}
