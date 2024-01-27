import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

/**
 * Class to test AirplaneRBT.java
 * 
 * @author Shrey Ramesh
 */
public class AlgorithmEngineerTests {

        /**
         * Testing Case 1 and Case 2 for remove() in AirplaneRBT: Node has one child and
         * node has 2 children, respectively
         * 
         * @return true for an expected level-order output, false for an unexpected
         *         level-order output
         */
        @Test
        public void test1() {
                // createAirplanes();
                // String airport = "ORD"
                String airport = "ORD";
                AirplaneRBT departures = new AirplaneRBT();

                AirplanePlaceholder a1 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:00"), "Southwest");
                AirplanePlaceholder a2 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:30"), "Southwest");
                AirplanePlaceholder a3 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:40"), "Southwest");
                AirplanePlaceholder a4 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:50"), "Southwest");
                AirplanePlaceholder a5 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:55"), "Southwest");
                AirplanePlaceholder a6 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("5:21"), "Southwest");
                AirplanePlaceholder a7 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("11:46"), "Southwest");
                AirplanePlaceholder a8 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("12:59"), "Southwest");
                AirplanePlaceholder a9 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:42"), "Southwest");
                AirplanePlaceholder a10 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:45"), "Southwest");
                AirplanePlaceholder a11 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:21"), "Southwest");
                AirplanePlaceholder a12 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:31"), "Southwest");
                AirplanePlaceholder a13 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("20:49"), "Southwest");
                AirplanePlaceholder a14 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("22:54"), "Southwest");
                AirplanePlaceholder a15 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("23:02"), "Southwest");

                departures.insert(a1);
                departures.insert(a2);
                departures.insert(a3);
                departures.insert(a4);
                departures.insert(a5);
                departures.insert(a6);
                departures.insert(a7);
                departures.insert(a8);
                departures.insert(a9);
                departures.insert(a10);
                ;
                // Case 1
                departures.remove(a9);

                // Expected Traversal: a4, a2, a6, a1, a3, a5, a8, a7, a10
                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	2:50, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	1:00, Southwest Flight: 111111 Departing: ORD	2:40, Southwest Flight: 111111 Departing: ORD	2:55, Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	17:45 ]",
                                departures.toLevelOrderString());

                // Case 2
                // Expected Traversal: a4, a2, a6, a1, a3, a5, a7, a10
                departures.remove(a8);
                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	2:50, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	1:00, Southwest Flight: 111111 Departing: ORD	2:40, Southwest Flight: 111111 Departing: ORD	2:55, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	17:45 ]",
                                departures.toLevelOrderString());
        }

        /**
         * Testing Case 3 for remove() in AirplaneRBT: Node has zero children
         * 
         * @return true for an expected level-order output, false for an unexpected
         *         level-order output
         */
        @Test
        public void test2() {
                // createAirplanes();
                // String airport = "ORD"
                String airport = "ORD";
                AirplaneRBT departures = new AirplaneRBT();

                AirplanePlaceholder a1 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:00"), "Southwest");
                AirplanePlaceholder a2 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:30"), "Southwest");
                AirplanePlaceholder a3 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:40"), "Southwest");
                AirplanePlaceholder a4 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:50"), "Southwest");
                AirplanePlaceholder a5 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:55"), "Southwest");
                AirplanePlaceholder a6 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("5:21"), "Southwest");
                AirplanePlaceholder a7 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("11:46"), "Southwest");
                AirplanePlaceholder a8 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("12:59"), "Southwest");
                AirplanePlaceholder a9 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:42"), "Southwest");
                AirplanePlaceholder a10 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:45"), "Southwest");
                AirplanePlaceholder a11 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:21"), "Southwest");
                AirplanePlaceholder a12 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:31"), "Southwest");
                AirplanePlaceholder a13 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("20:49"), "Southwest");
                AirplanePlaceholder a14 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("22:54"), "Southwest");
                AirplanePlaceholder a15 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("23:02"), "Southwest");

                departures.insert(a1);
                departures.insert(a2);
                departures.insert(a3);
                departures.insert(a4);
                departures.insert(a5);
                departures.insert(a6);
                departures.insert(a7);
                departures.insert(a8);
                departures.insert(a9);
                departures.insert(a10);
                // Case 3 that involves some rotating
                departures.remove(a3);

                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	2:50, Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	2:55, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	17:42, Southwest Flight: 111111 Departing: ORD	1:00, Southwest Flight: 111111 Departing: ORD	17:45 ]",
                                departures.toLevelOrderString());
        }

        /**
         * Testing Case 2 for remove() in AirplaneRBT: Node has two children and is root
         * node so it is forced to do recursive calls of other cases
         * 
         * @return true for an expected level-order output, false for an unexpected
         *         level-order output
         */
        @Test
        public void test3() {
                // createAirplanes();
                // String airport = "ORD"
                String airport = "ORD";
                AirplaneRBT departures = new AirplaneRBT();

                AirplanePlaceholder a1 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:00"), "Southwest");
                AirplanePlaceholder a2 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:30"), "Southwest");
                AirplanePlaceholder a3 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:40"), "Southwest");
                AirplanePlaceholder a4 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:50"), "Southwest");
                AirplanePlaceholder a5 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:55"), "Southwest");
                AirplanePlaceholder a6 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("5:21"), "Southwest");
                AirplanePlaceholder a7 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("11:46"), "Southwest");
                AirplanePlaceholder a8 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("12:59"), "Southwest");
                AirplanePlaceholder a9 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:42"), "Southwest");
                AirplanePlaceholder a10 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:45"), "Southwest");
                AirplanePlaceholder a11 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:21"), "Southwest");
                AirplanePlaceholder a12 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:31"), "Southwest");
                AirplanePlaceholder a13 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("20:49"), "Southwest");
                AirplanePlaceholder a14 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("22:54"), "Southwest");
                AirplanePlaceholder a15 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("23:02"), "Southwest");

                departures.insert(a1);
                departures.insert(a2);
                departures.insert(a3);
                departures.insert(a4);
                departures.insert(a5);
                departures.insert(a6);
                departures.insert(a7);
                departures.insert(a8);
                departures.insert(a9);
                departures.insert(a10);
                ;
                // Case 3 that involves some rotating
                departures.remove(a4);

                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	2:40, Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	2:55, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	17:42, Southwest Flight: 111111 Departing: ORD	1:00, Southwest Flight: 111111 Departing: ORD	17:45 ]",
                                departures.toLevelOrderString());
                departures.remove(a6); // Another root node

                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	2:55, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	1:00, Southwest Flight: 111111 Departing: ORD	2:40, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	17:42, Southwest Flight: 111111 Departing: ORD	17:45 ]",
                                departures.toLevelOrderString());

                departures.remove(a5); // Another root node

                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	2:40, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	1:00, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	17:42, Southwest Flight: 111111 Departing: ORD	17:45 ]",
                                departures.toLevelOrderString());

        }

        /**
         * Testing all cases together for remove() in AirplaneRBT: Adding and removing
         * airplanes in a completely random order
         * 
         * @return true for an expected level-order output, false for an unexpected
         *         level-order output
         */
        @Test
        public void test4() {
                // createAirplanes();
                // String airport = "ORD"
                String airport = "ORD";
                AirplaneRBT departures = new AirplaneRBT();

                AirplanePlaceholder a1 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:00"), "Southwest");
                AirplanePlaceholder a2 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:30"), "Southwest");
                AirplanePlaceholder a3 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:40"), "Southwest");
                AirplanePlaceholder a4 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:50"), "Southwest");
                AirplanePlaceholder a5 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:55"), "Southwest");
                AirplanePlaceholder a6 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("5:21"), "Southwest");
                AirplanePlaceholder a7 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("11:46"), "Southwest");
                AirplanePlaceholder a8 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("12:59"), "Southwest");
                AirplanePlaceholder a9 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:42"), "Southwest");
                AirplanePlaceholder a10 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:45"), "Southwest");
                AirplanePlaceholder a11 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:21"), "Southwest");
                AirplanePlaceholder a12 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:31"), "Southwest");
                AirplanePlaceholder a13 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("20:49"), "Southwest");
                AirplanePlaceholder a14 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("22:54"), "Southwest");
                AirplanePlaceholder a15 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("23:02"), "Southwest");

                departures.insert(a8);
                departures.insert(a10);
                departures.insert(a4);
                departures.insert(a5);
                departures.insert(a2);
                departures.insert(a9);
                departures.insert(a14);
                departures.insert(a6);
                departures.insert(a13);
                departures.insert(a1);
                departures.insert(a15);
                departures.insert(a3);
                departures.insert(a7);
                departures.insert(a11);
                departures.insert(a12);

                ;
                // Starting off with 2 rotations
                departures.remove(a10);

                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	2:50, Southwest Flight: 111111 Departing: ORD	22:54, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	19:31, Southwest Flight: 111111 Departing: ORD	23:2, Southwest Flight: 111111 Departing: ORD	1:00, Southwest Flight: 111111 Departing: ORD	2:40, Southwest Flight: 111111 Departing: ORD	2:55, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	17:42, Southwest Flight: 111111 Departing: ORD	20:49, Southwest Flight: 111111 Departing: ORD	19:21 ]",
                                departures.toLevelOrderString());
                departures.remove(a1); // A bunch of red nodes
                departures.remove(a3);
                departures.remove(a5);
                departures.remove(a7);

                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	2:50, Southwest Flight: 111111 Departing: ORD	22:54, Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	19:31, Southwest Flight: 111111 Departing: ORD	23:2, Southwest Flight: 111111 Departing: ORD	17:42, Southwest Flight: 111111 Departing: ORD	20:49, Southwest Flight: 111111 Departing: ORD	19:21 ]",
                                departures.toLevelOrderString());

                departures.remove(a2); // Recursive double black call

                Assertions.assertEquals(
                                "[ Southwest Flight: 111111 Departing: ORD	19:31, Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	22:54, Southwest Flight: 111111 Departing: ORD	2:50, Southwest Flight: 111111 Departing: ORD	17:42, Southwest Flight: 111111 Departing: ORD	20:49, Southwest Flight: 111111 Departing: ORD	23:2, Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	19:21 ]",
                                departures.toLevelOrderString());

        }

        /**
         * Testing searchByPeriod() in AirplaneRBT
         * 
         * @return true for an expected level-order output, false for an unexpected
         *         level-order output
         */
        @Test
        public void test5() {
                // createAirplanes();
                // String airport = "ORD"
                String airport = "ORD";
                AirplaneRBT departures = new AirplaneRBT();

                AirplanePlaceholder a1 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:00"), "Southwest");
                AirplanePlaceholder a2 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("1:30"), "Southwest");
                AirplanePlaceholder a3 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:40"), "Southwest");
                AirplanePlaceholder a4 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:50"), "Southwest");
                AirplanePlaceholder a5 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("2:55"), "Southwest");
                AirplanePlaceholder a6 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("5:21"), "Southwest");
                AirplanePlaceholder a7 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("11:46"), "Southwest");
                AirplanePlaceholder a8 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("12:59"), "Southwest");
                AirplanePlaceholder a9 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:42"), "Southwest");
                AirplanePlaceholder a10 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("17:45"), "Southwest");
                AirplanePlaceholder a11 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:21"), "Southwest");
                AirplanePlaceholder a12 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("19:31"), "Southwest");
                AirplanePlaceholder a13 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("20:49"), "Southwest");
                AirplanePlaceholder a14 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("22:54"), "Southwest");
                AirplanePlaceholder a15 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
                                new Time("23:02"), "Southwest");

                departures.insert(a1);
                departures.insert(a2);
                departures.insert(a3);
                departures.insert(a4);
                departures.insert(a5);
                departures.insert(a6);
                departures.insert(a7);
                departures.insert(a8);
                departures.insert(a9);
                departures.insert(a10);

                ArrayList<IAirplane> byTime = departures.searchByPeriod(new Time("1:30"), new Time("17:42"));
                Assertions.assertEquals(
                                "[Southwest Flight: 111111 Departing: ORD	1:30, Southwest Flight: 111111 Departing: ORD	2:40, Southwest Flight: 111111 Departing: ORD	2:50, Southwest Flight: 111111 Departing: ORD	2:55, Southwest Flight: 111111 Departing: ORD	5:21, Southwest Flight: 111111 Departing: ORD	11:46, Southwest Flight: 111111 Departing: ORD	12:59, Southwest Flight: 111111 Departing: ORD	17:42]",
                                byTime.toString());
        }

        /**
         * Testing remove() in unison with the Airplane class
         */
        @Test
        public void test6() {
                String airport = "ORD";
                AirplaneRBT departures = new AirplaneRBT();

                Airplane a1 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("1:00"), "Southwest");
                Airplane a2 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("1:30"), "Southwest");
                Airplane a3 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("2:40"), "Southwest");
                Airplane a4 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("2:50"), "Southwest");
                Airplane a5 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("2:55"), "Southwest");
                Airplane a6 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("5:21"), "Southwest");
                Airplane a7 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("11:46"), "Southwest");
                Airplane a8 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("12:59"), "Southwest");
                Airplane a9 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("17:42"), "Southwest");
                Airplane a10 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("17:45"), "Southwest");
                Airplane a11 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("19:21"), "Southwest");
                Airplane a12 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("19:31"), "Southwest");
                Airplane a13 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("20:49"), "Southwest");
                Airplane a14 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("22:54"), "Southwest");
                Airplane a15 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("23:02"), "Southwest");

                departures.insert(a8);
                departures.insert(a10);
                departures.insert(a4);
                departures.insert(a5);
                departures.insert(a2);
                departures.insert(a9);
                departures.insert(a14);
                departures.insert(a6);
                departures.insert(a13);
                departures.insert(a1);
                departures.insert(a15);
                departures.insert(a3);
                departures.insert(a7);
                departures.insert(a11);
                departures.insert(a12);

                ;
                // Starting off with 2 rotations
                departures.remove(a10);

                Assertions.assertEquals(
                                "[ Arrival: false, Flight ID: 111111, Airport: ORD, Time: 12:59, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:50, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 22:54, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 1:30, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 5:21, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 19:31, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 23:2, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 1:00, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:40, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:55, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 11:46, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 17:42, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 20:49, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 19:21, Company: Southwest ]",
                                departures.toLevelOrderString());
                departures.remove(a1); // A bunch of red nodes
                departures.remove(a3);
                departures.remove(a5);
                departures.remove(a7);

                Assertions.assertEquals(
                                "[ Arrival: false, Flight ID: 111111, Airport: ORD, Time: 12:59, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:50, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 22:54, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 1:30, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 5:21, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 19:31, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 23:2, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 17:42, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 20:49, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 19:21, Company: Southwest ]",
                                departures.toLevelOrderString());

                departures.remove(a2); // Recursive double black call

                Assertions.assertEquals(
                                "[ Arrival: false, Flight ID: 111111, Airport: ORD, Time: 19:31, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 12:59, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 22:54, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:50, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 17:42, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 20:49, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 23:2, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 5:21, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 19:21, Company: Southwest ]",
                                departures.toLevelOrderString());
        }

        /**
         * Testing searchByPeriod() in unison with the Airplane class
         */
        @Test
        public void test7() {
                String airport = "ORD";
                AirplaneRBT departures = new AirplaneRBT();

                Airplane a1 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("1:00"), "Southwest");
                Airplane a2 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("1:30"), "Southwest");
                Airplane a3 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("2:40"), "Southwest");
                Airplane a4 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("2:50"), "Southwest");
                Airplane a5 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("2:55"), "Southwest");
                Airplane a6 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("5:21"), "Southwest");
                Airplane a7 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("11:46"), "Southwest");
                Airplane a8 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("12:59"), "Southwest");
                Airplane a9 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("17:42"), "Southwest");
                Airplane a10 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("17:45"), "eouthwest");
                Airplane a11 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("19:21"), "Southwest");
                Airplane a12 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("19:31"), "Southwest");
                Airplane a13 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("20:49"), "Southwest");
                Airplane a14 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("22:54"), "Southwest");
                Airplane a15 = new Airplane(false, Integer.toString(111111), airport,
                                new Time("23:02"), "Southwest");

                departures.insert(a1);
                departures.insert(a2);
                departures.insert(a3);
                departures.insert(a4);
                departures.insert(a5);
                departures.insert(a6);
                departures.insert(a7);
                departures.insert(a8);
                departures.insert(a9);
                departures.insert(a10);

                ArrayList<IAirplane> byTime = departures.searchByPeriod(new Time("1:30"), new Time("17:42"));
                Assertions.assertEquals(
                                "[Arrival: false, Flight ID: 111111, Airport: ORD, Time: 1:30, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:40, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:50, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 2:55, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 5:21, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 11:46, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 12:59, Company: Southwest, Arrival: false, Flight ID: 111111, Airport: ORD, Time: 17:42, Company: Southwest]",
                                byTime.toString());
        }
}
