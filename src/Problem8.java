public class Problem8 {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(10);
        int spot1 = lot.parkVehicle("ABC-1234");
        int spot2 = lot.parkVehicle("ABC-1235");
        System.out.println("ABC-1234 parked at spot " + spot1);
        System.out.println("ABC-1235 parked at spot " + spot2);
        lot.exitVehicle("ABC-1234");
        System.out.println("ABC-1234 exited.");
    }
}

class ParkingLot {
    private String[] spots;
    private int size;

    ParkingLot(int size) {
        this.size = size;
        this.spots = new String[size];
    }

    public int parkVehicle(String plate) {
        int hash = Math.abs(plate.hashCode()) % size;
        while (spots[hash] != null) {
            hash = (hash + 1) % size;
        }
        spots[hash] = plate;
        return hash;
    }

    public void exitVehicle(String plate) {
        for (int i = 0; i < size; i++) {
            if (plate.equals(spots[i])) {
                spots[i] = null;
                break;
            }
        }
    }
}
