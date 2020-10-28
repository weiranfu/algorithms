package OOD.ElevatorSystem;

/*
    Design requirements
    1. elevator car can go up, down and idle
    2. elevator can transfer passengers from one floor to another
    3. elevator can only open its door when it's idle
    4. 200 floors and 40 elevator cars in a building
    5. Specs of elevator car:
       1) number of passengers
       2) maximum load
       3) maximum speed
    6. Goal of elevator system:
       1) Do we want to minimize the overall wait time of all passengers?
       2) Do we want to minimize the wait time of an individual passenger?
       3) Do we want to maximize the throughput of the system? (number of passengers use this system in a time)
       4) Do we want to minimize the power usage of the system?
    7. We can distribute elevator cars into 4 operation zones. (Divide and Conquer)
       For example, the first 10 elevators are responsible for 0-50 floors, the second 10 elevators are responsible for 51-100 floors.
       These 4 operation zones are totally independent, so we break the original problems into 4 smaller subproblems.
    8. Elevator cars can trigger emergence alarm.
    9. There are some VIP / Utility elevators.
    10. Monitoring system will monitor the status of elevator cars.

    Use Cases:
    1. Calling the elevator.
       Then dispatcher will figure out which elevator is most appropriate.
    2. Move / stop the elevator.
    3. Open / close the doors.
    4. Indicating the elevator direction.
    5. Indicating the elevator floor by showing the floor number.
    6. Triggering the emergency brakes.
    7. Making emergency calls.

    Core classes:
    1. Elevator Car
    2. Floor
    3. Door
    4. Button Panel (1. go up/down 2. the number of floor passenger want to go)
    5. Dispatcher
    6. Elevator System
    7. Monitoring System
 */
public class Question {
}
