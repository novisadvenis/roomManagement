package ch.bzz.roomManagement.util;

/**
 * generates random 7-digit numbers for the ReservationId
 *
 * <p>
 *     M426: RoomManagement
 * </p>
 *
 * @author Skyelar Maurer
 * @since 2021-12-02
 */
public class ReservationIdGenerator {

    public static int generateRandomId() {
        int aNumber = 0;
        aNumber = (int) ((Math.random() * 9000000) + 1000000);
        return aNumber;
    }
}
